package com.DaoVsDTO.service.implementation;

import com.DaoVsDTO.persistence.UserEntity;
import com.DaoVsDTO.persistence.dao.interfaces.IUserDAO;
import com.DaoVsDTO.presentation.dto.UserDTO;
import com.DaoVsDTO.service.interfaces.IUserService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDAO userDAO;

    @Override
    public List<UserDTO> findAll() {

        ModelMapper modelMapper = new ModelMapper();

        return this.userDAO.findAll()
                .stream()
                .map(entity -> modelMapper.map(entity, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findById(Long id) {

        Optional<UserEntity> userEntity= this.userDAO.findById(id);

        if (userEntity.isPresent()){
            ModelMapper modelMapper= new ModelMapper();
            UserEntity currentUse = userEntity.get();
            return modelMapper.map(currentUse, UserDTO.class);
        }else {
            return new UserDTO();
        }

    }

    @Override
    public UserDTO create(UserDTO userDTO) {

        try {
            ModelMapper modelMapper= new ModelMapper();
            UserEntity userEntity= modelMapper.map(userDTO, UserEntity.class );
            this.userDAO.saveUser(userEntity);
            return userDTO;
        }catch (Exception e){
            throw new UnsupportedOperationException("Miss to save user");
        }
    }

    @Override
    public UserDTO update(UserDTO userDTO, Long id) {
        Optional<UserEntity> userEntity= this.userDAO.findById(id);

        if(userEntity.isPresent()){
            UserEntity currentUserEntity= userEntity.get();
            currentUserEntity.setName(userDTO.getName());
            currentUserEntity.setEmail(userDTO.getEmail());
            currentUserEntity.setAge(userDTO.getAge());
            currentUserEntity.setLastName(userDTO.getLastName());

            this.userDAO.updateUser(currentUserEntity);

            ModelMapper modelMapper= new ModelMapper();
            return modelMapper.map(currentUserEntity, UserDTO.class);
        }else {
            throw new IllegalArgumentException("Error saving a user");
        }
    }

    // El método ahora es 'void' porque su única función es ejecutar una acción, no devolver datos.
    @Override
    public UserDTO delete(Long id) {
        ModelMapper modelMapper= new ModelMapper();
        UserEntity userEntity = userDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not exist or not found"));

        userDAO.deleteById(id);

        return modelMapper.map(userEntity, UserDTO.class);
    }


}
