package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class UserController {

	@Autowired
	lateinit private var userDao: UserDao

	@Autowired
	lateinit private var userRepository: UserRepository

	@RequestMapping("/")
	fun index() = "This is home"

	@GetMapping("/users")
	fun findAll() = userRepository.findAll()

	@GetMapping("/users/{id}")
	fun findById(@PathVariable id:Integer): User {
		val user = userRepository.findById(id)
		if (!user.isPresent()) {
			throw UserNotFoundException("id-$id")
		}
		return user.get()
	}
}
