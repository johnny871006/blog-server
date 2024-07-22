package com.johnny.blogserver.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data //包含@Getter、@Setter、@EqualsAndHashCode、@ToString
@Entity
@Table(name = "t_blog")
public class Blog {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "first_picture")
    private String firstPicture;

    @Column(name = "flag")
    private String flag;

    @Column(name = "views")
    private Integer views;

    @Column(name = "appreciation")
    private boolean appreciation;

    @Column(name = "share_statement")
    private boolean shareStatement;

    @Column(name = "commentable")
    private boolean commentable;

    @Column(name = "published")
    private boolean published;

    @Column(name = "recommend")
    private boolean recommend;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    private Date createTime;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time")
    private Date updateTime;

    @ManyToOne
    private Type type;

    //cascade : 新增tag也會保存到資料庫
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Tag> tags = new ArrayList<>();

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "blog")
    private List<Comment> comments = new ArrayList<>();
}
