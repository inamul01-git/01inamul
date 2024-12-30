package com.crm.service;


import com.crm.entity.Posts;
import com.crm.repository.CommentRepository;
import com.crm.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private PostRepository postRepository;
    private CommentRepository commentRepository;
    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
        this.commentRepository=commentRepository;
    }

    public void postData(Posts post) {
        postRepository.save(post);
    }

    public void deleteDataPost(Long id) {
        postRepository.deleteById(id);
    }
}
