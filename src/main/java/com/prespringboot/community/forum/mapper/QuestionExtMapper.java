package com.prespringboot.community.forum.mapper;

import com.prespringboot.community.forum.model.Question;

public interface QuestionExtMapper {
    //浏览数
    void incView(Question question);
    //评论数
    void incCommentCount(Question question);
}