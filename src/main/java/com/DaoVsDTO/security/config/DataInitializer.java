package com.DaoVsDTO.security.config;

import com.DaoVsDTO.persistence.UserEntity;
import com.DaoVsDTO.persistence.dao.interfaces.IUserDAO;
import com.DaoVsDTO.persistence.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private IUserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // --- CREAR USUARIO NORMAL ---
        // Verifica si el usuario 'user' ya existe
        if (userDAO.findByEmail("user@mail.com").isEmpty()) {
            UserEntity user = UserEntity.builder()
                    .name("Usuario")
                    .lastName("Normal")
                    .email("user@mail.com")
                    .password(passwordEncoder.encode("password123")) // Contraseña original: password123
                    .role(Role.USER)
                    .build();
            userDAO.saveUser(user);
        }

        // --- CREAR USUARIO ADMINISTRADOR ---
        // Verifica si el usuario 'admin' ya existe
        if (userDAO.findByEmail("admin@mail.com").isEmpty()) {
            UserEntity admin = UserEntity.builder()
                    .name("Super")
                    .lastName("Admin")
                    .email("admin@mail.com")
                    .password(passwordEncoder.encode("adminpass")) // Contraseña original: adminpass
                    .role(Role.ADMIN)
                    .build();
            userDAO.saveUser(admin);
        }
    }
}