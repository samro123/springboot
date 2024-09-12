package com.sam.hello_spring_boot.repository;

import com.sam.hello_spring_boot.entity.InvalidatedToken;
import com.sam.hello_spring_boot.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvalidatedRepository extends JpaRepository<InvalidatedToken, String> {

}
