package com.fletes.service;

import com.fletes.domain.Camion;
import com.fletes.dto.CamionRequestDTO;
import com.fletes.dto.CamionResponseDTO;
import com.fletes.exception.EntityNotFoundException;
import com.fletes.repository.CamionRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CamionServiceImpl implements CamionService {

    @Inject
    CamionRepository repository;

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
    public List<CamionResponseDTO> getAll() {
        return repository.findAllActivos().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
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
