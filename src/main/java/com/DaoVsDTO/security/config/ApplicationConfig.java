package com.DaoVsDTO.security.config;

import com.DaoVsDTO.persistence.dao.interfaces.IUserDAO; // Asegúrate de importar tu DAO/Repositorio
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    // Inyecta tu DAO o Repositorio de usuarios.
    // Usaremos @Autowired aquí si no usas Lombok con @RequiredArgsConstructor.
    @Autowired
    private final IUserDAO userDAO; // O UserRepository

    @Bean
    public UserDetailsService userDetailsService() {
        // Le decimos a Spring Security cómo buscar un usuario por su email (o username).
        // Devuelve una implementación de UserDetails. Tu UserEntity debe implementarla.
        return username -> (UserDetails) userDAO.findByEmail(username) // Asumiendo que tienes un método findByEmail
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        // Este es el proveedor de autenticación que usa los dos beans de abajo.
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService()); // 1. Le dice cómo encontrar al usuario.
        authProvider.setPasswordEncoder(passwordEncoder());     // 2. Le dice cómo verificar la contraseña.
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Define el algoritmo para encriptar contraseñas.
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        // Obtiene el gestor de autenticación de Spring.
        return config.getAuthenticationManager();
    }
}