package com.johnny.blogserver.service;

import com.johnny.blogserver.model.Comment;

import java.util.List;

public interface CommentService {

    // 根據blogId查詢所有留言
    List<Comment> listCommentByBlogId(Long blogId);

    // 發佈留言
    Comment saveComment(Comment comment);
}
