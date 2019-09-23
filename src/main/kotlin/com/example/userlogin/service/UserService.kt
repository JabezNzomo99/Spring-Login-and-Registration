package com.example.userlogin.service

import com.example.userlogin.model.User

interface UserService {
    fun saveUser(user:User)
    fun findByUserName(username:String):User?
}