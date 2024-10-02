package com.alisonadamus.simple_blog.services;

import com.alisonadamus.simple_blog.entity.Post;
import com.alisonadamus.simple_blog.repositories.PostRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findAll(int page, int size) {
        return postRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    public void save(Post post) {
        postRepository.save(post);
    }

    public void update(Post post) {
        postRepository.save(post);
    }

    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    public long count() {
        return postRepository.count();
    }
}
