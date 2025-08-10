package com.DaoVsDTO.presentation.controller;


import com.DaoVsDTO.presentation.dto.UserDTO;
import com.DaoVsDTO.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")


public class UserController  {

    @Autowired
    private IUserService userService;

    @GetMapping("/find")
    public ResponseEntity<List<UserDTO>> findAll(){
        return new ResponseEntity<>(this.userService.findAll(), HttpStatus.OK);

    }

    @GetMapping("/find/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        UserDTO userDTO = this.userService.findById(id); // O findById(id).orElse(null);
        if (userDTO != null) {
            return new ResponseEntity<>(userDTO, HttpStatus.OK); // 200 OK
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }

    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        return new ResponseEntity<>(this.userService.create(userDTO), HttpStatus.OK);

    }
    //Update User

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> updatedUser(@RequestBody UserDTO userDTO,@PathVariable Long id){
        return new ResponseEntity<>(this.userService.update(userDTO, id), HttpStatus.OK);

    }

    //Delete user
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        this.userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
