package com.johnny.blogserver.web;

import com.johnny.blogserver.model.Comment;
import com.johnny.blogserver.model.User;
import com.johnny.blogserver.service.BlogService;
import com.johnny.blogserver.service.CommentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @Value("${comment.avatar}")
    private String avatar;

    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model) {

        model.addAttribute("comments", commentService.listCommentByBlogId(blogId));

        return "blog :: commentList";
    }


    @PostMapping("/comments")
    public String post(Comment comment, HttpSession httpSession){

        Long blogId = comment.getBlog().getId();
        comment.setBlog(blogService.getBlog(blogId));

        User user = (User) httpSession.getAttribute("user");
        if(user != null){
            comment.setAvatar(user.getAvatar());
            comment.setOriginalPoster(true);
        }else {
            comment.setAvatar(avatar);
        }

        commentService.saveComment(comment);

        return "redirect:/comments/" + blogId;
    }
}
