package com.example.demo

import java.util.Date

import org.springframework.stereotype.Component

@Component
class UserDao {

	companion object {
		@JvmField
		var users: MutableList<User> = mutableListOf(
			User(1, "Sam", Date()),
			User(2, "Ted", Date())
		)
	}

	fun findAll(): List<User> {
		return users.toList()
	}
}