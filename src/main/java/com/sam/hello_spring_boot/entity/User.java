package com.sam.hello_spring_boot.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)  //sinh ra cac id ngau nhien va k trung lap
     String id;
     String username;
     String password;
     String firstName;
     String lastName;
     LocalDate dob;
     @ManyToMany
     Set<Role> roles;


}
