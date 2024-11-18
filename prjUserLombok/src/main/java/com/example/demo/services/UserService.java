package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User salvarUser(User user) {
		return userRepository.save(user);
	}

	public User buscarUserPorId(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	public List<User> buscarTodosUsers() {
		return userRepository.findAll();
	}

	public void excluirUser(Long id) {
		userRepository.deleteById(id);
	}

	public User alterarUser(Long id, User userAtualizado) {
		Optional<User> userExistente = userRepository.findById(id);
		if (userExistente.isPresent()) {
			userAtualizado.setId(id);
			return userRepository.save(userAtualizado);
		} else {
			return null;
		}
	}
	
	public boolean apagarUser (Long id) {
		Optional <User> existeUser = userRepository.findById(id);
		if (existeUser.isPresent ()) {
			userRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
