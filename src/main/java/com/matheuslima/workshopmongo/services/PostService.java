package com.matheuslima.workshopmongo.services;

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
	
}
