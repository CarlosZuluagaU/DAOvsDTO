package com.DaoVsDTO.presentation.controller;

import com.DaoVsDTO.presentation.dto.UserDTO;
import com.DaoVsDTO.service.interfaces.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
// ✅ @Tag: Agrupa todos los endpoints de este controlador bajo una categoría.
@Tag(name = "Gestión de Usuarios", description = "API para las operaciones CRUD de Usuarios.")
public class UserController {

    @Autowired
    private IUserService userService;

    // --- OBTENER TODOS LOS USUARIOS ---
    @Operation(summary = "Obtener la lista de todos los usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtuvo la lista de usuarios correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> users = this.userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // --- OBTENER USUARIO POR ID ---
    @Operation(summary = "Obtener un usuario por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado para el ID proporcionado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(
            @Parameter(description = "ID del usuario que se desea buscar.", required = true)
            @PathVariable Long id) {
        UserDTO userDTO = this.userService.findById(id);
        if (userDTO != null) {
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // --- CREAR UN NUEVO USUARIO ---
    @Operation(summary = "Crear un nuevo usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "La solicitud es inválida o el cuerpo está mal formado")
    })
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = this.userService.create(userDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // --- ACTUALIZAR UN USUARIO ---
    @Operation(summary = "Actualizar un usuario existente por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Usuario a actualizar no encontrado"),
            @ApiResponse(responseCode = "400", description = "La solicitud es inválida")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(
            @Parameter(description = "ID del usuario a actualizar.", required = true)
            @PathVariable Long id,
            @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = this.userService.update(userDTO, id);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    // --- ELIMINAR UN USUARIO ---
    @Operation(summary = "Eliminar un usuario por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuario eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Usuario a eliminar no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "ID del usuario a eliminar.", required = true)
            @PathVariable Long id) {
        this.userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}