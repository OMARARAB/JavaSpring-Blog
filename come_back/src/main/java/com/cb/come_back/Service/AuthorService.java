package com.cb.come_back.Service;

import com.cb.come_back.DtoModel.AuthorDto;
import com.cb.come_back.Entity.Author;
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
    public void saveAuthor(AuthorDto authorDto) {
        if (authorDto == null){
            throw new IllegalArgumentException("Author cannot be null");
        }
        Author author = AuthorMapper.toEntity(authorDto);
        authorRepository.save(author);
    }

    @Transactional(readOnly = true)
    public Optional<AuthorDto> getAuthor(long id){
        Author author = authorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Author not found"));
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
            throw new IllegalArgumentException("Author does not exist");
        }
        authorRepository.deleteById(id);
    }

    @Transactional
    public AuthorDto updateAuthor(long id, AuthorDto authorDtoDetails) {
        if (authorDtoDetails == null) {
            throw new IllegalArgumentException("Author details cannot be null");
        }
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Author not found"));

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
