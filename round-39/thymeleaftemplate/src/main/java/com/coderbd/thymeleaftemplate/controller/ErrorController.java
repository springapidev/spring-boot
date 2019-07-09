package com.coderbd.thymeleaftemplate.controller;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ErrorController {

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String renderErrorPage(HttpServletRequest request, HttpServletResponse response, Model model) {
        int code = response.getStatus();
       // System.out.println("code::::: "+code);
        String message = HttpStatus.valueOf(code).getReasonPhrase();
        model.addAttribute("code", code);
        model.addAttribute("message", message);
        return "error";
    }


}
