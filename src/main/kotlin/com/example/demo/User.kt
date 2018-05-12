package com.example.demo

import java.util.Date
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class User(@Id @GeneratedValue val id:Int=0, val name:String="", val birthDate:Date?=null)