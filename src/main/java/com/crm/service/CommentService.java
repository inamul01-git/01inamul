package com.crm.service;

import com.crm.entity.Comments;
import com.crm.entity.Posts;
import com.crm.repository.CommentRepository;
import com.crm.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {


  private PostRepository postRepository;
  private CommentRepository commentRepository;
  public CommentService(PostRepository postRepository,CommentRepository commentRepository){
      this.postRepository = postRepository;
      this.commentRepository=commentRepository;
  }

    public void getCommented(Comments comment, Long postId) {
      Posts post = postRepository.findById(postId).get();
      comment.setPost(post);
      commentRepository.save(comment);
    }
}
