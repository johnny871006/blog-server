package com.johnny.blogserver.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Data //包含@Getter、@Setter、@EqualsAndHashCode、@ToString
@Entity
@Table(name = "t_type")
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotBlank(message = "分類名稱不能為空!")
    private String name;

    //mappedBy : 哪個欄位維護
    @OneToMany(mappedBy = "type")
    private List<Blog> blogs = new ArrayList<>();

}
