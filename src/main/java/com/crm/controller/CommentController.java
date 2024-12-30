package com.crm.controller;
import com.crm.entity.Comments;
import com.crm.service.CommentService;
import com.crm.service.PostService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/comments")
public class CommentController {
    private CommentService commentService;
    private PostService postService;
    public CommentController( PostService postService,CommentService commentService) {
        this.postService =postService;
        this.commentService = commentService;
    }

    @PostMapping
    public String getComment(
            @RequestBody Comments comment,
            @RequestParam Long postId
    ){
        commentService.getCommented(comment,postId);
        return "comment created successfully";
    }

}
