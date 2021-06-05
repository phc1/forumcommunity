package com.prespringboot.community.forum.service;

import com.prespringboot.community.forum.enums.CommentTypeEnum;
import com.prespringboot.community.forum.exception.CustomizeErrorCode;
import com.prespringboot.community.forum.exception.CustomizeException;
import com.prespringboot.community.forum.mapper.CommentMapper;
import com.prespringboot.community.forum.mapper.QuestionMapper;
import com.prespringboot.community.forum.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;

    public void insert(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() == 0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())){
            throw  new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }

        if (comment.getType() == CommentTypeEnum.COMMENT.getType()){
            //回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (dbComment == null){
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NO_FOUND);
            }
            commentMapper.insert(comment);
        } else {
            //回复问题
            
        }
    }
}
