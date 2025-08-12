package com.DaoVsDTO.persistence.dao.implementation;

import com.DaoVsDTO.persistence.UserEntity;
import com.DaoVsDTO.persistence.dao.interfaces.IUserDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDAOImpl implements IUserDAO {

    @PersistenceContext
    private EntityManager em;


    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> findAll() {
        return this.em.createQuery("SELECT u FROM UserEntity u").getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserEntity> findById(Long id) {
        return Optional.ofNullable(this.em.find(UserEntity.class, id));
    }

    @Override
    @Transactional
    public void saveUser(UserEntity userEntity) {
        this.em.persist(userEntity);
        this.em.flush();
    }

    @Override
    @Transactional
    public void updateUser(UserEntity userEntity) {
       this.em.merge(userEntity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        UserEntity userToDelete = this.em.find(UserEntity.class, id);
        if (userToDelete != null) {
            this.em.remove(userToDelete);
        }
    }

    @Override
    @Transactional(readOnly = true) // Es una buena práctica para métodos de solo lectura
    public Optional<UserEntity> findByEmail(String email) {
        String jpqlQuery = "SELECT u FROM UserEntity u WHERE u.email = :email";

        try {
            // Creamos la consulta, le pasamos el tipo de resultado esperado (UserEntity.class)
            UserEntity user = em.createQuery(jpqlQuery, UserEntity.class)
                    .setParameter("email", email) // Asignamos el valor al parámetro :email
                    .getSingleResult(); // Esperamos un único resultado. Si no encuentra nada, lanza una excepción.

            return Optional.of(user); // Si lo encuentra, lo envolvemos en un Optional.

        } catch (jakarta.persistence.NoResultException e) {
            return Optional.empty();
        }
    }
}
