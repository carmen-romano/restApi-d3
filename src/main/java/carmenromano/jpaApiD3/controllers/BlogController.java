package carmenromano.jpaApiD3.controllers;

import carmenromano.jpaApiD3.entities.Author;
import carmenromano.jpaApiD3.entities.Blog;
import carmenromano.jpaApiD3.payloads.BlogPostsPayload;
import carmenromano.jpaApiD3.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogPosts")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @GetMapping
    public Page<Blog> getAllBlogPosts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
        return this.blogService.getAllBlogPosts(page, size, sortBy);
    }

    @GetMapping("/{blogId}")
    private Blog findUserById(@PathVariable int blogId){
        return this.blogService.findById(blogId);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Status Code 201
    private Blog saveBlog(@RequestBody BlogPostsPayload body){
        return this.blogService.saveBlog(body);
    }


    @PutMapping("/{blogId}")
        private Blog findAndUpdateBlog(@PathVariable int blogId, @RequestBody Blog body){
        return this.blogService.findByIdAndUpdate(blogId, body);
    }


    @DeleteMapping("/{blogId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void findAndDeleteBlog(@PathVariable int blogId){
        this.blogService.findAndDelete(blogId);
    }


    ///TEST

   // @GetMapping("/readingTime")
   // public List<Blog> findByReadingTime(@RequestParam int readingTime){
   //     return this.blogService.findByReadingTime(readingTime);
   // }

}
