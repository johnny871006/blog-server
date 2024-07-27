package com.johnny.blogserver.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data //包含@Getter、@Setter、@EqualsAndHashCode、@ToString
@Entity
@Table(name = "t_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "type")
    private Integer type;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    private Date createTime;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time")
    private Date updateTime;

    @OneToMany(mappedBy = "user")
    private List<Blog> blogs = new ArrayList<>();
}
