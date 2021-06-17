package com.github.cewitte.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.github.cewitte.domain.Post;
import com.github.cewitte.domain.User;
import com.github.cewitte.dto.AuthorDTO;
import com.github.cewitte.dto.CommentDTO;
import com.github.cewitte.repository.PostRepository;
import com.github.cewitte.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		userRepository.deleteAll();
		postRepository.deleteAll();

		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));

		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Go, go, go", "It will be the time of my life!", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("21/03/2018"), "Good morning", "I woke up happy today.", new AuthorDTO(maria));
		
		CommentDTO c1 = new CommentDTO("Good trip! Enjoy.", sdf.parse("21/03/2018"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Wow! Great!", sdf.parse("22/03/2018"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Have a great day!", sdf.parse("23/03/2018"), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));

		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPost().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
	}

}
