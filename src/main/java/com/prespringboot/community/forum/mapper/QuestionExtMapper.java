package com.prespringboot.community.forum.mapper;

import com.prespringboot.community.forum.model.Question;

public interface QuestionExtMapper {

    void incView(Question question);
}