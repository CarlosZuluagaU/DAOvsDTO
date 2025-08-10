package com.DaoVsDTO.service.interfaces;


import com.DaoVsDTO.presentation.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface IUserService {
    List<UserDTO> findAll();
    UserDTO findById(Long id);
    UserDTO create(UserDTO userDTO);
    UserDTO update(UserDTO userDTO, Long id);
    UserDTO delete(Long id);
}
