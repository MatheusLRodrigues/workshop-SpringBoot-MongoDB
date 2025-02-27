package com.matheuslima.workshopmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheuslima.workshopmongo.domain.Post;
import com.matheuslima.workshopmongo.repository.PostRepository;
import com.matheuslima.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepo;

	public Post findById(String id) {
		Optional<Post> post = postRepo.findById(id);
		return post.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	} 

	public List<Post> findByTitle(String text){
		return postRepo.searchTitle(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate){
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		return postRepo.fullSearch(text, minDate, maxDate);
	}
	
}
