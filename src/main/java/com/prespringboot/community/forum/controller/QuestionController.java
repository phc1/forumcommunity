package com.prespringboot.community.forum.controller;

import com.prespringboot.community.forum.dto.QuestionDTO;
import com.prespringboot.community.forum.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    //问题详情+浏览数
    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id, Model model){
        QuestionDTO questionDTO = questionService.getId(id);
        questionService.incView(id);
        model.addAttribute("question",questionDTO);
        return "question";
    }
}
