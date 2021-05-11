package com.prespringboot.community.forum.controller;

import com.prespringboot.community.forum.dto.QuestionDTO;
import com.prespringboot.community.forum.mapper.UserMapper;
import com.prespringboot.community.forum.model.User;
import com.prespringboot.community.forum.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;


    @GetMapping("/")
    public String greeting(HttpServletRequest request, Model model) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.foundByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        List<QuestionDTO> questionDTOList = questionService.list();
        model.addAttribute("questions",questionDTOList);
        return "index";
    }
}
