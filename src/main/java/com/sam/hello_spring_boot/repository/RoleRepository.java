package com.sam.hello_spring_boot.repository;

import com.sam.hello_spring_boot.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
