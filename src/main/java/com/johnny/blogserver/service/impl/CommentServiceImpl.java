package com.johnny.blogserver.service.impl;

import com.johnny.blogserver.dao.CommentRepository;
import com.johnny.blogserver.model.Comment;
import com.johnny.blogserver.service.CommentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        Sort sort = Sort.by(Sort.Direction.ASC,"createTime");
        List<Comment> comments = commentRepository.findByBlogIdAndParentCommentNull(blogId,sort);
        return eachComment(comments);
    }

    @Transactional
    @Override
    public Comment saveComment(Comment comment) {
        Long parentCommentId = comment.getParentComment().getId();
        if(parentCommentId != -1){
            comment.setParentComment(commentRepository.findById(parentCommentId).orElse(null));
        }else{
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        return commentRepository.save(comment);
    }

    //循環每個父節點(第一層)
    private List<Comment> eachComment(List<Comment> comments){
        List<Comment> commentsView = new ArrayList<>();
        for(Comment comment : comments){
            Comment c = new Comment();
            BeanUtils.copyProperties(comment,c);
            commentsView.add(c);
        }
        //合併留言的各層子到父集合中
        combineChildren(commentsView);
        return commentsView;
    }

    //合併所有子節點
    //comments 子節點，blog不為空的集合
    private void combineChildren(List<Comment> comments){
        for(Comment comment : comments){
            //子節點(第二層)
            List<Comment> replys1 = comment.getReplyComments();
            for(Comment reply1 : replys1){
                //找出子節點(第三層)，存放在tempReplys中
                recursively(reply1);
            }
            //修改父節點的reply集合為遞迴處理後的集合
            comment.setReplyComments(tempReplys);
            //清除臨時存放區
            tempReplys = new ArrayList<>();
        }
    }

    //存放所有子節點的集合(第二層含以下)
    private List<Comment> tempReplys = new ArrayList<>();

    //遞迴方式，把所有子節點(含子節點的子節點)存放在同一個地方
    private void recursively(Comment comment) {
        tempReplys.add(comment); //父節點新增到臨時存放區
        if(!comment.getReplyComments().isEmpty()) {
            List<Comment> replys = comment.getReplyComments();
            for(Comment reply : replys){
                tempReplys.add(reply);
                if(!reply.getReplyComments().isEmpty()){
                    recursively(reply);
                }
            }
        }
    }

}
