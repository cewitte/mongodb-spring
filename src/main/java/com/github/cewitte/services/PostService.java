package com.github.cewitte.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.cewitte.domain.Post;
import com.github.cewitte.repository.PostRepository;
import com.github.cewitte.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;

	public Post findById(String id) {
		Post post;

		try {
			post = repo.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new ObjectNotFoundException("User object not found");
		}

		return post;
	}
	
	public List<Post> findByTitle(String text){
		return repo.findByTitleContainingIgnoreCase(text);
	}

}
