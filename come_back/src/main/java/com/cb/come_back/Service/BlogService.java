package com.cb.come_back.Service;

import com.cb.come_back.Entity.Blog;
import com.cb.come_back.Repository.BlogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;

    @Transactional
    public void saveBlog(Blog blog) {
        if (blog == null){
            throw new IllegalArgumentException("Blog cannot be null");
        }
        blogRepository.save(blog);
    }

    @Transactional(readOnly = true)
    public Optional<Blog> getBlog(long id){
        return blogRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Iterable<Blog> getAllBlogs(){
        return blogRepository.findAll();
    }

    @Transactional
    public void deleteBlog(long id){
        if(!blogRepository.existsById(id)){
            throw new IllegalArgumentException("Blog does not exist");
        }
        blogRepository.deleteById(id);
    }

    @Transactional
    public Blog updateBlog(long id ,Blog blogDetails){
        if(blogDetails == null){
            throw new IllegalArgumentException("Blog details cannot be null");
        }
        Blog blog = blogRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Blog not found"));

        if (blogDetails.getTitle() != null) {
            blog.setTitle(blogDetails.getTitle());
        }
        if (blogDetails.getContent() != null) {
            blog.setContent(blogDetails.getContent());
        }
        if (blogDetails.getAuthor() != null) {
            blog.setAuthor(blogDetails.getAuthor());
        }

        return blogRepository.save(blog);
    }
}
