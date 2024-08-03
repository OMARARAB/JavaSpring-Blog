package com.cb.come_back.Service;

import com.cb.come_back.DtoModel.AuthorDto;
import com.cb.come_back.Entity.Author;
import com.cb.come_back.Exception_handling.AuthorCannotBeNull;
import com.cb.come_back.Exception_handling.AuthorNotFoundException;
import com.cb.come_back.Mapper.AuthorMapper;
import com.cb.come_back.Repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Transactional
    public void saveAuthor(Author author) {
        if (author == null){
            throw new AuthorCannotBeNull();
        }
        authorRepository.save(author);
    }

    @Transactional(readOnly = true)
    public Optional<AuthorDto> getAuthor(long id){
        Author author = authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException());
        return Optional.of(AuthorMapper.toDto(author));
    }

    @Transactional(readOnly = true)
    public List<AuthorDto> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return AuthorMapper.toDto(authors);
    }


    @Transactional
    public void deleteAuthor(long id){
        if(!authorRepository.existsById(id)){
            throw new AuthorNotFoundException();
        }
        authorRepository.deleteById(id);
    }

    @Transactional
    public AuthorDto updateAuthor(long id, AuthorDto authorDtoDetails) {
        if (authorDtoDetails == null) {
            throw new AuthorCannotBeNull();
        }
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException());

        if (authorDtoDetails.getName() != null) {
            author.setAuthorName(authorDtoDetails.getName());
        }
        if (authorDtoDetails.getEmail() != null) {
            author.setEmail(authorDtoDetails.getEmail());
        }

        Author updatedAuthor = authorRepository.save(author);

        return AuthorMapper.toDto(updatedAuthor);
    }

}
