package com.cb.come_back.Mapper;

import com.cb.come_back.DtoModel.AuthorDto;
import com.cb.come_back.Entity.Author;

import java.util.List;
import java.util.stream.Collectors;

public class AuthorMapper {

    public static AuthorDto toDto(Author author) {
        if (author == null) {
            return null;
        }
        AuthorDto dto = new AuthorDto();
        dto.setId(author.getAuthorId());
        dto.setName(author.getAuthorName());
        dto.setEmail(author.getEmail());
        return dto;
    }

    public static List<AuthorDto> toDto(List<Author> authors) {
        if (authors == null) {
            return null;
        }
        return authors.stream()
                .map(AuthorMapper::toDto)
                .collect(Collectors.toList());
    }

    public static Author toEntity(AuthorDto dto) {
        if (dto == null) {
            return null;
        }
        Author author = new Author();
        author.setAuthorId(dto.getId());
        author.setAuthorName(dto.getName());
        author.setEmail(dto.getEmail());
        return author;
    }
}

