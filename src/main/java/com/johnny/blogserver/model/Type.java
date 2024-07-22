package com.johnny.blogserver.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data //包含@Getter、@Setter、@EqualsAndHashCode、@ToString
@Entity
@Table(name = "t_type")
public class Type {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    //mappedBy : 哪個欄位維護
    @OneToMany(mappedBy = "type")
    private List<Blog> blogs = new ArrayList<>();

}
