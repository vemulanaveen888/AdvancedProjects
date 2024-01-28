package com.pratap.userservicejpa;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
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
public class UserJpaResource {

	@Autowired
	UserRepository userdao;

	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers() {
		return userdao.findAll();
	}
	
	@GetMapping("/jpa/users/{id}")
	public Resource<User> retrieveUser(@PathVariable int id) {
		Optional<User> user = userdao.findById(id);
		if (!user.isPresent())
			throw new UserNotFoundException("Id - 	" + id);

		Resource<User> resource = new Resource<User>(user.get());
		ControllerLinkBuilder linkTo = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
		resource.add(linkTo.withRel("all-users"));
		return resource;
	}
	
	
	@GetMapping("/jpa/users/{userid}/posts")
	public List<Post> retrieveAllUsers(@PathVariable Integer userid ) {
		Optional<User> userOptional = userdao.findById(userid);
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("id " +userid);
		}
		return userOptional.get().getPosts();
	}
	
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userdao.deleteById(id);
	}
	
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUserWithCreateStatusAndLocation(@Valid @RequestBody User user) {
		User savedUser = userdao.save(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(savedUser.getId())
					.toUri();
		return ResponseEntity.created(uri).build();
	}
}
