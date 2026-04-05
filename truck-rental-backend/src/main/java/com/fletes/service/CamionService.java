package com.fletes.service;

import java.util.List;

import com.fletes.dto.CamionRequestDTO;
import com.fletes.dto.CamionResponseDTO;

public interface CamionService {
    CamionResponseDTO create(CamionRequestDTO dto);
    List<CamionResponseDTO> getAll(String fechaDesde, String fechaHasta, Float capacidadCargaKg);
    CamionResponseDTO getById(Integer id);
    CamionResponseDTO update(Integer id, CamionRequestDTO dto);
    void setEstado(Integer id, boolean estado);
    boolean getEstado(Integer id);
    void delete(Integer id);
}