package com.example.userlogin.repository

import com.example.userlogin.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User,Long>{
    fun findByUsername(username:String):User?
}