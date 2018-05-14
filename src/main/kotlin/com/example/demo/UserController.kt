package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import javax.validation.Valid

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
	fun findById(@PathVariable id:Int): User {
		val user = userRepository.findById(id)
		if (!user.isPresent()) {
			throw UserNotFoundException("id-$id")
		}
		return user.get()
	}

	@PostMapping("/users")
	fun save(@Valid @RequestBody user:User):ResponseEntity<Any> {
		val savedUser = userRepository.save(user)

		val location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.id)
				.toUri()
		return ResponseEntity.created(location).build()
	}
}
