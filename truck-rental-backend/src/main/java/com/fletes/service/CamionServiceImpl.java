package com.fletes.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import com.fletes.domain.Camion;
import com.fletes.dto.CamionRequestDTO;
import com.fletes.dto.CamionResponseDTO;
import com.fletes.dto.ReservaResponseDTO;
import com.fletes.exception.EntityNotFoundException;
import com.fletes.repository.CamionRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CamionServiceImpl implements CamionService {

    @Inject
    CamionRepository repository;

    @Inject
    ReservaService reservaService;

    @Override
    @Transactional
    public CamionResponseDTO create(CamionRequestDTO dto) {
        Camion camion = new Camion();
        camion.setNombre(dto.getNombre());
        camion.setCapacidadCargaKg(dto.getCapacidadCargaKg());
        camion.setEstado(true);

        repository.persist(camion);
        return toDTO(camion);
    }

    @Override
    public List<CamionResponseDTO> getAll(String fechaDesde, String fechaHasta, Float capacidadCargaKg) {
        List<Camion> filtrados = repository.findAllActivos();
        List<ReservaResponseDTO> reservas = reservaService.getAll();
        if (fechaDesde != null && fechaHasta != null) {
            Timestamp timeFechaDesde = Timestamp.valueOf(fechaDesde);
            Timestamp timeFechaHasta = Timestamp.valueOf(fechaHasta);
            if (timeFechaDesde.after(timeFechaHasta)) {
                throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha de fin");
            }
            if (timeFechaDesde.before(new Timestamp(System.currentTimeMillis()))) {
                throw new IllegalArgumentException("La fecha de inicio no puede ser anterior a la fecha actual");
            }
            filtrados = filtrados.stream().filter(dto -> 
                !(reservas.stream().anyMatch(r -> r.getIdCamion().equals(dto.getId()) &&
                    (((r.getFechaDesde().before(timeFechaDesde) || r.getFechaDesde().equals(timeFechaDesde))
                        && r.getFechaHasta().after(timeFechaDesde)) ||
                    (r.getFechaDesde().before(timeFechaHasta)
                        && (r.getFechaHasta().after(timeFechaHasta) || r.getFechaHasta().equals(timeFechaHasta))) ||
                    ((r.getFechaDesde().after(timeFechaDesde) || r.getFechaDesde().equals(timeFechaDesde))
                        && (r.getFechaHasta().before(timeFechaHasta) || r.getFechaHasta().equals(timeFechaHasta))))))
            ).collect(Collectors.toList());
        }
        if (capacidadCargaKg != null) {
            filtrados = filtrados.stream().filter(dto -> dto.getCapacidadCargaKg() >= capacidadCargaKg).collect(Collectors.toList());
        }
        List<CamionResponseDTO> resultado = filtrados.stream().map(this::toDTO).collect(Collectors.toList());
        if (resultado.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron camiones con los filtros aplicados");
        }
        return resultado;
    }

    @Override
    public CamionResponseDTO getById(Integer id) {
        Camion camion = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Camión no encontrado con ID " + id));
        return toDTO(camion);
    }

    @Override
    @Transactional
    public CamionResponseDTO update(Integer id, CamionRequestDTO dto) {
        Camion camion = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Camión no encontrado con ID " + id));

        camion.setNombre(dto.getNombre());
        camion.setCapacidadCargaKg(dto.getCapacidadCargaKg());
        repository.update(camion);

        return toDTO(camion);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Camion camion = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Camión no encontrado con ID " + id));
        camion.setEstado(false);
        repository.update(camion);
    }

    @Override
    @Transactional
    public void setEstado(Integer id, boolean estado) {
        Camion camion = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Camión no encontrado con ID " + id));
        camion.setEstado(estado);
        repository.update(camion);
    }

    @Override
    public boolean getEstado(Integer id) {
        Camion camion = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Camión no encontrado con ID " + id));
        return camion.getEstado();
    }

    private CamionResponseDTO toDTO(Camion camion) {
        CamionResponseDTO dto = new CamionResponseDTO();
        dto.setId(camion.getId());
        dto.setNombre(camion.getNombre());
        dto.setCapacidadCargaKg(camion.getCapacidadCargaKg());
        dto.setEstado(camion.getEstado());
        dto.setFechaAlta(camion.getFechaAlta());
        dto.setFechaUltimaModificacion(camion.getFechaUltimaModificacion());
        return dto;
    }
}

