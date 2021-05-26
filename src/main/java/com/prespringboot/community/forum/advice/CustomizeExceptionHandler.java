package com.prespringboot.community.forum.advice;

import com.prespringboot.community.forum.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handleControllerException(Throwable throwable, Model model) {
        if (throwable instanceof CustomizeException) {
            model.addAttribute("message", throwable.getMessage());
        } else {
            model.addAttribute("message", "服务器冒烟了，要不你稍后再试试");
        }
        return new ModelAndView("error");
    }
}
