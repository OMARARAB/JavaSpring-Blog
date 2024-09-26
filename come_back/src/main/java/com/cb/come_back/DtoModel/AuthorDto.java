package com.cb.come_back.DtoModel;

import com.cb.come_back.Entity.Blog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDto {
    private Long id;
    private String name;
    private String email;
    private List<Blog> blogs;
}
