package com.serviexpress.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.serviexpress.apirest.entity.UserRole;



@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {


    public abstract UserRole findByRoleid(Long id);
}