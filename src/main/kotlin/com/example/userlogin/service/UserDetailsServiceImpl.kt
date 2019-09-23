package com.example.userlogin.service

import com.example.userlogin.model.User
import com.example.userlogin.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(private val userRepository: UserRepository):UserDetailsService {

    private val logger = LoggerFactory.getLogger(UserDetailsServiceImpl::class.java)
    override fun loadUserByUsername(username: String): UserDetails {
            val user: User = userRepository.findByUsername(username) ?: throw UsernameNotFoundException(username)
            println(user.toString())
        val grantedAuthorities = HashSet<GrantedAuthority>()
            user.roles?.forEach { role->
                grantedAuthorities.add(SimpleGrantedAuthority(role.name))
            }

        return org.springframework.security.core.userdetails.User(user.username,user.password,grantedAuthorities)

    }
}