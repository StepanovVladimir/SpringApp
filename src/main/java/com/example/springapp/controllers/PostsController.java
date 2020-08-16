package com.example.springapp.controllers;

import com.example.springapp.models.Post;
import com.example.springapp.repo.PostRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/posts")
@Api(value = "Posts resources", description = "Crud operations")
public class PostsController
{
    @Autowired
    private PostRepository postRepository;

    @GetMapping
    @ApiOperation(value = "Return all posts")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List returned successfully")
    })
    public Iterable<Post> index()
    {
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Return post by id", response = Post.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Post returned successfully"),
            @ApiResponse(code = 404, message = "Invalid post id")
    })
    public ResponseEntity<Post> show(@PathVariable(value = "id") long id)
    {
        Optional<Post> post = postRepository.findById(id);

        if (post.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(post.get(), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Create post and return id of created post")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Post created and post id returned successfully")
    })
    public ResponseEntity<Long> create(@RequestBody Post post)
    {
        return new ResponseEntity<>(postRepository.save(post).getId(), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update post")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Post updated successfully"),
            @ApiResponse(code = 404, message = "Invalid post id")
    })
    public ResponseEntity<Void> update(@PathVariable(value = "id") long id, @RequestBody Post post)
    {
        Optional<Post> optionalPost = postRepository.findById(id);

        if (optionalPost.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Post storedPost = optionalPost.get();
        storedPost.setTitle(post.getTitle());
        storedPost.setAnons(post.getAnons());
        storedPost.setFullText(post.getFullText());
        postRepository.save(storedPost);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete post")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Post deleted successfully"),
            @ApiResponse(code = 404, message = "Invalid post id")
    })
    public ResponseEntity<Void> delete(@PathVariable(value = "id") long id)
    {
        Optional<Post> post = postRepository.findById(id);

        if (post.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        postRepository.delete(post.get());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
