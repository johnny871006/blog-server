package com.johnny.blogserver.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data //包含@Getter、@Setter、@EqualsAndHashCode、@ToString
@Entity
@Table(name = "t_user")
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String nickName;
    private String userName;
    private String password;
    private String email;
    private String avatar;
    private Integer type;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @OneToMany(mappedBy = "user")
    private List<Blog> blogs = new ArrayList<>();
}
