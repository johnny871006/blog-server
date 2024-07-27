package com.johnny.blogserver.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data //包含@Getter、@Setter、@EqualsAndHashCode、@ToString
@Entity
@Table(name = "t_tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "tags")
    private List<Blog> blogs = new ArrayList<>();

}
