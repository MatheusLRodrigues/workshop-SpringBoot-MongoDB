package com.matheuslima.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.matheuslima.workshopmongo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
