package com.matheuslima.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheuslima.workshopmongo.domain.User;
import com.matheuslima.workshopmongo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	public List<User> findAll() {
		return userRepo.findAll();
	}
		
}
