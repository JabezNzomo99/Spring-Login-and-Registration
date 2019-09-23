package com.example.userlogin.model

import javax.persistence.*

@Entity
@Table(name = "user")
data class User(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id:Long?,
                val username:String?,
                var password:String?,
                @Transient val passwordConfirm:String?,
                @ManyToMany(fetch = FetchType.EAGER) var roles:Set<Role>?)