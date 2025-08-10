package com.DaoVsDTO.persistence.presentation.controller.dto;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String name;
    private String lastName;
    private  String email;
    private byte age;
}
