package com.sam.hello_spring_boot.repository;

import com.sam.hello_spring_boot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUsername(String username); // Jpa tu dong generate cau jquery tu dong kiem tra UserName da ton tai
}