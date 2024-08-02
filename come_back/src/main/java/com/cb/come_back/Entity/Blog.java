package com.cb.come_back.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "Blog")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long blogId;

    @Column(nullable = false,name = "title")
    private String title;

    @Column(nullable = false,name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "authorId", nullable = false)
    private Author author;

    @Column(nullable = false,name = "createdAt")
    private LocalDateTime createdAt;
}
