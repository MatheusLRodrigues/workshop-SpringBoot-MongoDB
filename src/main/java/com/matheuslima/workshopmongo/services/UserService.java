package com.matheuslima.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheuslima.workshopmongo.domain.User;
import com.matheuslima.workshopmongo.dto.UserDTO;
import com.matheuslima.workshopmongo.repository.UserRepository;
import com.matheuslima.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	public List<User> findAll() {
		return userRepo.findAll();
	}

	public User findById(String id) {
		Optional<User> user = userRepo.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public User insert(User userIn) {
		return userRepo.insert(userIn);
	}

	public void delete(String id) {
		userRepo.deleteById(id);
	}

	public User update(User userUp) {
		User userDb = findById(userUp.getId());
		updateData(userDb, userUp);
		return userRepo.save(userDb);
	}

	private void updateData(User userDb, User userUp) {
		userDb.setName(userUp.getName());
		userDb.setEmail(userUp.getEmail());
	}

	public User fromDTO(UserDTO userDto) {
		return new User(userDto.getId(), userDto.getName(), userDto.getEmail());
	}

}
