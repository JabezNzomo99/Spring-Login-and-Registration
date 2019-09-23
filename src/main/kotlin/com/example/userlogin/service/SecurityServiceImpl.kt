package com.example.userlogin.service

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class SecurityServiceImpl(private val userDetailsService:UserDetailsService): SecurityService {


    private val logger =LoggerFactory.getLogger(SecurityServiceImpl::class.java)

    override fun findLoggedInUserName(): String? {
        val userDetails = SecurityContextHolder.getContext().authentication.details
        return if (userDetails is UserDetails){
            (userDetails as UserDetails).username

        }else
            null

    }

    override fun autoLogin(username: String, password: String) {
        val userDetails = userDetailsService.loadUserByUsername(username)
        val userNamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(userDetails.username,userDetails.password,userDetails.authorities)
        if(userNamePasswordAuthenticationToken.isAuthenticated){
            SecurityContextHolder.getContext().authentication = userNamePasswordAuthenticationToken
            logger.debug("Auto login $username sucssesfully")
        }
    }
}