package com.prespringboot.community.forum.controller;

import com.prespringboot.community.forum.dto.PaginationDTO;
import com.prespringboot.community.forum.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String greeting(Model model,
                           @RequestParam(name = "page",defaultValue = "1") Integer page,
                           @RequestParam(name = "size",defaultValue = "1") Integer size) {

        PaginationDTO pagination = questionService.list(page, size);
        model.addAttribute("pagination",pagination);
        return "index";
    }
}
