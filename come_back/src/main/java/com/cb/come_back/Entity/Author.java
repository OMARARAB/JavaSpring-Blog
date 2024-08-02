package com.cb.come_back.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "Author")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long authorId;

    @Column(nullable = false,name = "authorname")
    private String authorName;

    @Column(nullable = false,name = "email")
    private String email;

    @Column(nullable = false,name = "password")
    private String password;

    @Column(nullable = false,name = "phone")
    private String phone;

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL ,orphanRemoval = true)
    private List<Blog> blogs;
}
