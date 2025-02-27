package com.matheuslima.workshopmongo.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.matheuslima.workshopmongo.domain.Post;
import com.matheuslima.workshopmongo.resources.util.URL;
import com.matheuslima.workshopmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	private PostService postServ;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id) {
		Post getPost = postServ.findById(id);

		return ResponseEntity.ok().body(getPost);
	}

	@GetMapping(value = "/titlesearch")
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(defaultValue = "") String text) {
		text = URL.decodeParam(text);

		List<Post> listPosts = postServ.findByTitle(text);

		return ResponseEntity.ok().body(listPosts);
	}

	@GetMapping("/fullsearch")
	public ResponseEntity<List<Post>> fullSearch(@RequestParam(defaultValue = "") String text,
			@RequestParam(defaultValue = "") String minDate, @RequestParam(defaultValue = "") String maxDate) {
		text = URL.decodeParam(text);
		Date min = URL.convertDate(minDate, new Date(0L));
		Date max = URL.convertDate(maxDate, new Date());
		List<Post> list = postServ.fullSearch(text, min, max);
		return ResponseEntity.ok().body(list);

	}
}
