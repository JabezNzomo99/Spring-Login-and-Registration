package com.example.userlogin.repository

import com.example.userlogin.model.Role
import org.springframework.data.jpa.repository.JpaRepository


interface RoleRepository:JpaRepository<Role,Long> {
}