package com.example.springapp.repo;

import com.example.springapp.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {}
