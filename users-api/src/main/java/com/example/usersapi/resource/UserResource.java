package com.example.usersapi.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.usersapi.model.User;
import com.example.usersapi.repository.UsersRepository;

@RestController
@RequestMapping("/user")
public class UserResource {

	@Autowired
	UsersRepository repositorio;

	@GetMapping
	public List<User> listarUsers() {
		    return repositorio.findAll();

	}

	@GetMapping("{id}")
	public Optional<User>  buscarUserPeloId(@PathVariable Long id) {
		    return repositorio.findById(id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarUserPeloId(@PathVariable Long id) {
		     repositorio.deleteById(id);
	}
  
    @PostMapping("/{id}")
	public ResponseEntity<Void> inserirUser(
			@PathVariable String id, @RequestBody User user) {

    	User userInsercao = repositorio.save(user);

		if (user == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
				"/{id}").buildAndExpand(userInsercao.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
 
}
