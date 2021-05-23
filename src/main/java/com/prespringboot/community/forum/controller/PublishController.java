package com.prespringboot.community.forum.controller;

import com.prespringboot.community.forum.dto.QuestionDTO;
import com.prespringboot.community.forum.mapper.QuestionMapper;
import com.prespringboot.community.forum.model.Question;
import com.prespringboot.community.forum.model.User;
import com.prespringboot.community.forum.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private QuestionService questionService;

    //编辑已提问问题
    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Integer id,Model model) {
        QuestionDTO question = questionService.getId(id);
        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());
        return "publish";
    }

    //跳转到提交问题页面
    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    //添加一个新问题
    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            @RequestParam(value = "id",required = false) Integer id,
            HttpServletRequest request,
            Model model) {

        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);

        if (title == null || title.trim().isEmpty()) {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (description == null || description.trim().isEmpty()) {
            model.addAttribute("error", "内容不能为空");
            return "publish";
        }
        if (tag == null || tag.trim().isEmpty()) {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }
        Question question = new Question();
        question.setId(id);
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        questionService.createOrUpdate(question);
        return "redirect:/";
    }
}
