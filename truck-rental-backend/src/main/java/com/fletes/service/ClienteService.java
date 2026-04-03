package com.fletes.service;

import com.fletes.dto.ClienteRequestDTO;
import com.fletes.dto.ClienteResponseDTO;
import java.util.List;

public interface ClienteService {
    ClienteResponseDTO create(ClienteRequestDTO dto);
    List<ClienteResponseDTO> getAll();
    ClienteResponseDTO getById(Integer id);
    ClienteResponseDTO update(Integer id, ClienteRequestDTO dto);
    void delete(Integer id);
}
