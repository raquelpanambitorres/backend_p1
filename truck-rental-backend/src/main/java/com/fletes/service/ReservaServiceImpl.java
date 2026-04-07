package com.fletes.service;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fletes.domain.Reserva;
import com.fletes.dto.CamionResponseDTO;
import com.fletes.dto.ReservaRequestDTO;
import com.fletes.dto.ReservaResponseDTO;
import com.fletes.exception.EntityNotFoundException;
import com.fletes.repository.CamionRepository;
import com.fletes.repository.ClienteRepository;
import com.fletes.repository.ReservaRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ReservaServiceImpl implements ReservaService {

    @Inject
    ReservaRepository repository;

    @Inject
    ClienteRepository clienteRepository;

    @Inject
    CamionRepository camionRepository;

    @Inject
    CamionService camionService;

    @Override
    @Transactional
    public ReservaResponseDTO create(ReservaRequestDTO dto) {
        Reserva reserva = new Reserva();
        List<CamionResponseDTO> camiones;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (dto.getFechaDesde().before(new Timestamp(System.currentTimeMillis()))) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser anterior a la fecha actual");
        }
        if (dto.getFechaDesde().after(dto.getFechaHasta())) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha de fin");
        }

        camiones = camionService.getFiltered(dto.getFechaDesde().toLocalDateTime().format(formatter), dto.getFechaHasta().toLocalDateTime().format(formatter), dto.getPesoCargaKg(), false);
        if (camiones.isEmpty()) {
            throw new EntityNotFoundException("No hay camiones disponibles con los criterios especificados en la fecha indicada");
        }

        if (dto.getIdCamion() != null) {
            Optional<CamionResponseDTO> camionOpt = camiones.stream()
                    .filter(c -> c.getId().equals(dto.getIdCamion()))
                    .findFirst();
            if (camionOpt.isEmpty()) {
                throw new EntityNotFoundException("El camión especificado no está disponible en el rango de fechas indicado o no posee la capacidad de carga requerida");
            }
            camiones = List.of(camionOpt.get());
        }

        // camionService.setEstado(camion.get().getId(), false);
        reserva.setIdCamion(camionRepository.findById(camiones.get(0).getId()).get());
        reserva.setIdCliente(clienteRepository.findById(dto.getIdCliente()).get());
        reserva.setFechaDesde(dto.getFechaDesde());
        reserva.setFechaHasta(dto.getFechaHasta());
        reserva.setLugarDesde(dto.getLugarDesde());
        reserva.setLugarHasta(dto.getLugarHasta());
        reserva.setPesoCargaKg(dto.getPesoCargaKg());

        repository.persist(reserva);
        return toDTO(reserva);
    }

    @Override
    public List<ReservaResponseDTO> getAll() {
        actualizarDisponibilidad();
        return repository.findAll().stream()
                .map(this::toDTO)
                .filter(dto -> dto.getEstado() == true)
                .collect(Collectors.toList());
    }

    @Override
    public ReservaResponseDTO getById(Integer id) {
        actualizarDisponibilidad();
        Reserva reserva = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva no encontrado con ID " + id));
        return toDTO(reserva);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Reserva reserva = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva no encontrado con ID " + id));
        CamionResponseDTO camion = camionService.getById(reserva.getIdCamion().getId());
        if (camion.getEstado() == false && reserva.getEstado() == true) {
            camionService.setEstado(camion.getId(), true);
        }
        repository.remove(reserva);
    }

    private ReservaResponseDTO toDTO(Reserva reserva) {
        ReservaResponseDTO dto = new ReservaResponseDTO();
        dto.setId(reserva.getId());
        dto.setIdCliente(reserva.getIdCliente().getId());
        dto.setIdCamion(reserva.getIdCamion().getId());
        dto.setFechaReserva(reserva.getFechaReserva());
        dto.setFechaDesde(reserva.getFechaDesde());
        dto.setFechaHasta(reserva.getFechaHasta());
        dto.setLugarDesde(reserva.getLugarDesde());
        dto.setLugarHasta(reserva.getLugarHasta());
        dto.setPesoCargaKg(reserva.getPesoCargaKg());
        dto.setEstado(reserva.getEstado());
        return dto;
    }

    @Transactional
    void actualizarDisponibilidad() {
        if (repository.count() > 0) {
            List<Reserva> reservas = repository.findAll().stream()
                    .filter(r -> r.getEstado() == true)
                    .collect(Collectors.toList());
            for (Reserva reserva : reservas) {
                if (reserva.getFechaHasta().before(new Timestamp(System.currentTimeMillis()))) {
                    reserva.setEstado(false);
                    repository.update(reserva);
                    camionService.setEstado(reserva.getIdCamion().getId(), true);
                } else if (reserva.getFechaDesde().before(new Timestamp(System.currentTimeMillis()))) {
                    camionService.setEstado(reserva.getIdCamion().getId(), false);
                }
            }
        }
    }

    @SuppressWarnings("unused")
    List<ReservaResponseDTO> getByRange(Timestamp fechaDesde, Timestamp fechaHasta) {
        List<ReservaResponseDTO> reservas = getAll();
        reservas = reservas.stream()
                .filter(r -> 
                    (((r.getFechaDesde().before(fechaDesde) || r.getFechaDesde().equals(fechaDesde))
                        && r.getFechaHasta().after(fechaDesde)) ||
                    (r.getFechaDesde().before(fechaHasta)
                        && (r.getFechaHasta().after(fechaHasta) || r.getFechaHasta().equals(fechaHasta))) ||
                    ((r.getFechaDesde().after(fechaDesde) || r.getFechaDesde().equals(fechaDesde))
                        && (r.getFechaHasta().before(fechaHasta) || r.getFechaHasta().equals(fechaHasta)))))
                .collect(Collectors.toList());
        if (reservas.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron reservas en el rango de fechas especificado");
        }
        return reservas;
    }
}