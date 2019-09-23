package com.example.userlogin.model

import javax.persistence.*

@Entity
@Table(name = "role")
data class Role(@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
                val id:Long,
                val name:String,
                @ManyToMany(mappedBy = "roles") val users:Set<User>)
