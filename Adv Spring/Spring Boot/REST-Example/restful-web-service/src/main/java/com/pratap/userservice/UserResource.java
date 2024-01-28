package com.pratap.userservice;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pratap.exception.UserNotFoundException;

@RestController
public class UserResource {
	@Autowired
	UserDaoImpl dao;

	// GET All Users
	@GetMapping(path="/users",produces = {"application/json"})
	public List<User> retrieveAllUsers() {
		return dao.findAll();
	}

	// Get a Single User
	/*
	 * @GetMapping("/users/{id}") public User retrieveUser(@PathVariable("id") int
	 * id) { User user = dao.findOne(id); if( user == null) { throw new
	 * UserNotFoundException("id - "+id); } return user; }
	 */

	@GetMapping("/users/{id}")
	public Resource<User> retrieveUser(@PathVariable("id") int id) {
		User user = dao.findOne(id);
		if (user == null) {
			throw new UserNotFoundException("id - " + id);
		}
		
		
		Resource<User>  resource = new Resource<User>(user);
		ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
		resource.add(linkTo.withRel("all-users"));
		
		return resource;
	}

	// create a user
	/*
	 * { "name": "jones", "birthDate": "2019-08-17T15:43:40.160+0000" }
	 */

	/*
	 * @PostMapping("/users") public User createUser(@RequestBody User user) {
	 * return dao.save(user); }
	 */

	@PostMapping("/users")
	public ResponseEntity createUserWithCreatedStatusAndLocation(@Valid @RequestBody User user) {
		User savedUser = dao.save(user);

		// /users/{id}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).body(savedUser);
	}

	// Delete User
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		// WE ARE PLANING TO RETURN 200 OK STATUS ON SUCCESSFUL DELETE
		User user = dao.deleteById(id);
		if (user == null)
			throw new UserNotFoundException("Id - 	" + id);
	}
}
