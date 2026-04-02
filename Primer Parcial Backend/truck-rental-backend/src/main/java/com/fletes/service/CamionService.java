package com.fletes.service;

import com.fletes.dto.CamionRequestDTO;
import com.fletes.dto.CamionResponseDTO;
import java.util.List;

public interface CamionService {
    CamionResponseDTO create(CamionRequestDTO dto);
    List<CamionResponseDTO> getAll();
    CamionResponseDTO getById(Integer id);
    CamionResponseDTO update(Integer id, CamionRequestDTO dto);
    void delete(Integer id);
}
