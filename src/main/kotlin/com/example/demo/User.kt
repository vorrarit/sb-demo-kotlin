package com.example.demo

import java.util.Date
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.Past
import javax.validation.constraints.Size

@Entity
data class User(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id:Int=0,
        @get: Size(min = 2, message = "name at least 2 characters") val name:String="",
        @get: Past(message = "birth date should be in the past") val birthDate:Date?=null
)