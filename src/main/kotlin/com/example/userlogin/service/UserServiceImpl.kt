package com.example.userlogin.service

import com.example.userlogin.model.Role
import com.example.userlogin.model.User
import com.example.userlogin.repository.RoleRepository
import com.example.userlogin.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(private val userRepository: UserRepository,
                      private val roleRepository: RoleRepository,
                      private val bCryptPasswordEncoder: BCryptPasswordEncoder): UserService {

    override fun saveUser(user: User){
        user.password = bCryptPasswordEncoder.encode(user.password)
        user.roles = HashSet<Role>(roleRepository.findAll())
        userRepository.save(user)
    }

    override fun findByUserName(username: String): User? = userRepository.findByUsername(username)
}