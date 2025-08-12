package com.DaoVsDTO.persistence.dao.interfaces;

import com.DaoVsDTO.persistence.UserEntity;

import java.util.List;
import java.util.Optional;

public interface IUserDAO {

    List<UserEntity> findAll();

    Optional<UserEntity> findById(Long id);

    void saveUser(UserEntity userEntity);

    void updateUser(UserEntity userEntity);

    void deleteById(Long id);

    Optional<UserEntity> findByEmail(String email);
}
