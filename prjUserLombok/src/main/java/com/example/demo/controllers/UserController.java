package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.User;
import com.example.demo.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/User")
public class UserController {
	
	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/")
	public ResponseEntity <User> salvaUsersControl(@RequestBody @Valid User User){
		User salvarUser = userService.salvarUser(User);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvarUser);
	}
	
	@GetMapping("/")
	public ResponseEntity <List<User>> buscarTodos(){
		List<User> Users = userService.buscarTodosUsers();
		return ResponseEntity.ok(Users);
	}
	
	@GetMapping("/(id)")
	public ResponseEntity<User> buscarPorId(@PathVariable Long id) {
		User user = userService.buscarUserPorId(id);
		if(user != null) {
			return ResponseEntity.ok(user);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/(id)")
	public void deletarUser(@PathVariable Long id) {
		userService.excluirUser(id);
	}
	
	@PutMapping ("/{id}")
	public ResponseEntity<User> alteraUserControl(@PathVariable Long id, @RequestBody @Valid User User) {
		User alteraUser = userService.alterarUser(id, User);
		if(alteraUser != null) {
			return ResponseEntity.ok(User);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	@DeleteMapping ("/{id}")
	public ResponseEntity<String> apagaUserControl (@PathVariable Long id) {
		boolean apagar = userService.apagarUser(id);
		if (apagar) {
			return ResponseEntity.ok().body("O ususario foi excluido com sucesso");
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	

}
