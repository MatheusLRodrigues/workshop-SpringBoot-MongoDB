package com.matheuslima.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.matheuslima.workshopmongo.domain.Post;
import com.matheuslima.workshopmongo.domain.User;
import com.matheuslima.workshopmongo.dto.AuthorDTO;
import com.matheuslima.workshopmongo.dto.CommentDTO;
import com.matheuslima.workshopmongo.repository.PostRepository;
import com.matheuslima.workshopmongo.repository.UserRepository;

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

		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");

		userRepository.saveAll(Arrays.asList(maria, alex, bob));

		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu Viagem", "Vou viajar para São Paulo. Abraços!",
				new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));
		
		CommentDTO commentAlex = new CommentDTO("Boa viagem mano!", sdf.parse("21/03/2018"), new AuthorDTO(alex)); 
		CommentDTO commentBob = new CommentDTO("Aproveite", sdf.parse("22/03/2018"), new AuthorDTO(bob)); 
		CommentDTO commentAlex2 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("23/03/2018"), new AuthorDTO(alex)); 
		
		post1.getComments().addAll(Arrays.asList(commentAlex, commentBob));
		post2.getComments().addAll(Arrays.asList(commentAlex2));
		
		postRepository.saveAll(Arrays.asList(post1, post2));	
		
		
		maria.getUserPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
		
	}

}
