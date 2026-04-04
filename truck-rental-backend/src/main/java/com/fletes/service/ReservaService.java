package com.fletes.service;

import com.fletes.dto.ReservaRequestDTO;
import com.fletes.dto.ReservaResponseDTO;
import java.util.List;

public interface ReservaService {
    ReservaResponseDTO create(ReservaRequestDTO dto);
    List<ReservaResponseDTO> getAll();
    ReservaResponseDTO getById(Integer id);
    void delete(Integer id);
}