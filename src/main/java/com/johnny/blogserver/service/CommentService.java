package com.johnny.blogserver.service;

import com.johnny.blogserver.model.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);
}
