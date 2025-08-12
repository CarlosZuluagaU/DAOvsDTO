package com.DaoVsDTO.presentation.controller;

import com.DaoVsDTO.presentation.dto.UserDTO;
import com.DaoVsDTO.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users") // <-- 1. URL base mejorada (plural y con /api)
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * GET /api/users - Obtiene todos los usuarios.
     */
    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> users = this.userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * GET /api/users/{id} - Obtiene un usuario por su ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        UserDTO userDTO = this.userService.findById(id);
        if (userDTO != null) {
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * POST /api/users - Crea un nuevo usuario.
     */
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = this.userService.create(userDTO);
        // 2. Usar CREATED (201) para la creación de recursos.
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    /**
     * PUT /api/users/{id} - Actualiza un usuario existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        // Aquí podrías añadir lógica para devolver NOT_FOUND si el usuario a actualizar no existe.
        UserDTO updatedUser = this.userService.update(userDTO, id);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    /**
     * DELETE /api/users/{id} - Elimina un usuario.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) { // <-- 3. Tipo de retorno cambiado a Void.
        this.userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content es perfecto.
    }
}