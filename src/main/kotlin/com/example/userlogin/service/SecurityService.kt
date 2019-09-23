package com.example.userlogin.service

interface SecurityService {
    fun findLoggedInUserName():String?
    fun autoLogin(username:String,password:String)
}