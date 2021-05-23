package com.prespringboot.community.forum.service;

import com.prespringboot.community.forum.dto.PaginationDTO;
import com.prespringboot.community.forum.dto.QuestionDTO;
import com.prespringboot.community.forum.mapper.QuestionMapper;
import com.prespringboot.community.forum.mapper.UserMapper;
import com.prespringboot.community.forum.model.Question;
import com.prespringboot.community.forum.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;


    public PaginationDTO list(Integer page, Integer size) {
        Integer totalPage;

        PaginationDTO paginationDTO = new PaginationDTO();

        Integer totalCount = questionMapper.count();
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        paginationDTO.setPagination(totalPage,page);

        Integer offset = size * (page-1);
        List<Question> questionList = questionMapper.list(offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questionList) {
            User user = userMapper.foundById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setData(questionDTOList);
        return paginationDTO;
    }

    public PaginationDTO list(Integer userId, Integer page, Integer size) {
        Integer totalPage;

        PaginationDTO paginationDTO = new PaginationDTO();

        Integer totalCount = questionMapper.countByUseId(userId);
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        paginationDTO.setPagination(totalPage,page);

        Integer offset = size * (page-1);
        List<Question> questionList = questionMapper.listByUserId(userId,offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questionList) {
            User user = userMapper.foundById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setData(questionDTOList);
        return paginationDTO;
    }

    public QuestionDTO getId(Integer id) {
        Question question = questionMapper.getId(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user = userMapper.foundById(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if (question.getId() == null){
            //提交新问题
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.creat(question);
        }else {
            //更新问题
            question.setGmtModified(question.getGmtCreate());
            questionMapper.update(question);
        }
    }
}
