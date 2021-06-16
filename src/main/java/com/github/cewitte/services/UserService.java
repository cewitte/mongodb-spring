package com.github.cewitte.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.cewitte.domain.User;
import com.github.cewitte.repository.UserRepository;
import com.github.cewitte.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	public List<User> findAll() {
		return repo.findAll();
	}

	public User findById(String id) {
		User user;

		try {
			user = repo.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new ObjectNotFoundException("User object not found");
		}

//		if (user == null) {
//			throw new ObjectNotFoundException("User object not found");
//		}

		return user;
	}

}
