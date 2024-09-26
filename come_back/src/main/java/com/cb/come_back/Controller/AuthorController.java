package com.cb.come_back.Controller;

import com.cb.come_back.DtoModel.AuthorDto;
import com.cb.come_back.Entity.Author;
import com.cb.come_back.Service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/author")
@AllArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping("/save")
    public ResponseEntity<Void> saveAuthor(@RequestBody Author author) {
        if (author == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        authorService.saveAuthor(author);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/getAuthor/{id}")
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable Long id) {
        Optional<AuthorDto> authorDto = authorService.getAuthor(id);
        if (authorDto.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(authorDto.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/allAuthors")
    public ResponseEntity<List<AuthorDto>> getAllAuthors() {
        List<AuthorDto> authors = authorService.getAllAuthors();
        return ResponseEntity.status(HttpStatus.OK).body(authors);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAuthorById(@PathVariable Long id) {
        try{
            authorService.deleteAuthor(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable Long id, @RequestBody AuthorDto authorDto) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(authorService.updateAuthor(id, authorDto));
        }
        catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
