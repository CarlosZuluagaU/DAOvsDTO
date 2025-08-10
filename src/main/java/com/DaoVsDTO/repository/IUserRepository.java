package com.DaoVsDTO.repository;

import com.DaoVsDTO.persistence.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface IUserRepository extends CrudRepository<UserEntity, Long> {
}
