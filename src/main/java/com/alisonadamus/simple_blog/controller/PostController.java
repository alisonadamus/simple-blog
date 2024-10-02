package com.alisonadamus.simple_blog.controller;

import com.alisonadamus.simple_blog.entity.Post;
import com.alisonadamus.simple_blog.services.PostService;
import com.alisonadamus.simple_blog.services.UserService;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final UserService userService;

    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }


    @GetMapping
    public String getPosts(Model model,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {
        List<Post> posts = postService.findAll(page, size);
        long totalPosts = postService.count();
        int totalPages = (int) Math.ceil((double) totalPosts / size);

        model.addAttribute("posts", posts);
        model.addAttribute("page", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("size", size);

        return "postList";
    }


    @GetMapping("/{id}")
    public String getPost(@PathVariable Long id, Model model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm:ss");
        Optional<Post> post = postService.findById(id);

        post.ifPresent(p -> {
                model.addAttribute("post", p);
                model.addAttribute("formattedCreatedAt", p.getCreatedAt().format(formatter));
                model.addAttribute("formattedUpdatedAt", p.getUpdatedAt().format(formatter));
            }
        );
        return "viewPost";
    }

    @GetMapping("/new")
    public String newPost(Model model) {
        Post post = new Post();
        post.setUserId(1L);
        model.addAttribute("post", post);
        return "formPost";
    }

    @PostMapping
    public String savePost(@ModelAttribute Post post) {
        postService.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/edit/{id}")
    public String editPost(@PathVariable Long id, Model model) {
        Optional<Post> post = postService.findById(id);
        post.ifPresent(p -> model.addAttribute("post", p));
        return "formPost";

    }

    @PostMapping("/{id}")
    public String updatePost(@PathVariable Long id, @ModelAttribute Post post) {
        postService.update(post);
        return "redirect:/posts";
    }

    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        postService.delete(id);
        return "redirect:/posts";
    }
}
