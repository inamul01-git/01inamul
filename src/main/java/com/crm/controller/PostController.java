package com.crm.controller;
import com.crm.entity.Posts;

import com.crm.service.CommentService;
import com.crm.service.PostService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/post")
public class PostController {

    private PostService postService;
    private CommentService commentService;

    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @PostMapping
    public String postDatabase(
            @RequestBody Posts post
    ){
        postService.postData(post);
        return  "created successfully";
    }
    @DeleteMapping
    public String deletedatabase(Long id){
        postService.deleteDataPost(id);
        return "deleted successfully";
    }

}
