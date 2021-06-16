package com.github.cewitte.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.github.cewitte.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{

}
