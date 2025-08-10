package com.DaoVsDTO.persistence;


import jakarta.persistence.*;
import lombok.*;
import lombok.extern.java.Log;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "User")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "last_name")
    private String lastName;
    private  String email;
    private byte age;
}
