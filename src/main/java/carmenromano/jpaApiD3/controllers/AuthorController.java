package carmenromano.jpaApiD3.controllers;


import carmenromano.jpaApiD3.entities.Author;
import carmenromano.jpaApiD3.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping
    public Page<Author> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
        return this.authorService.getAllAuthors(page, size, sortBy);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Status Code 201
    private Author saveAuthor(@RequestBody Author body){
        return this.authorService.saveAuthor(body);
    }

    @GetMapping("/{authorId}")
    public Author findById(@PathVariable int authorId) {
        return this.authorService.findById(authorId);
    }


    @PutMapping("/{authorId}")
    private Author findAndUpdateBlog(@PathVariable int authorId, @RequestBody Author body){
        return this.authorService.findAndUpdate(authorId, body);
    }


    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable int authorId) {
        this.authorService.findAndDelete(authorId);
    }



  //  @GetMapping("/nome")
  //  public Author findByName(@RequestParam String nome){
  //      return this.authorService.findByName(nome);
  //  }

}
