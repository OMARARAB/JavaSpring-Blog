package com.cb.come_back.Controller;

import com.cb.come_back.Entity.Blog;
import com.cb.come_back.Service.BlogService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/blog")
@AllArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @PostMapping("/save")
    public ResponseEntity<Void> saveBlog(@RequestBody Blog blog) {
        if (blog == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        blogService.saveBlog(blog);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/getBlog/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable Long id) {
        Optional<Blog> blog = blogService.getBlog(id);
        if (blog.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(blog.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/allBlogs")
    public ResponseEntity<List<Blog>> getAllBlogs() {
        List<Blog> authors = StreamSupport.stream(blogService.getAllBlogs().spliterator(), false)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(authors);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBlogById(@PathVariable Long id) {
        try{
            blogService.deleteBlog(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Blog> updateBlog(@PathVariable Long id, @RequestBody Blog blog) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(blogService.updateBlog(id, blog));
        }
        catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

