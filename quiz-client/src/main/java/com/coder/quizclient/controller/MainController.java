package com.coder.quizclient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.unbescape.html.HtmlEscape;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Application home page and login.
 */
@SessionAttributes("loginResponse")
@Controller
public class MainController {
    @RequestMapping("/")
    public String root(Locale locale) {
        return "redirect:/index.do";
    }

    /** Home page. */
    @RequestMapping("/index.do")
    public String index() {
        return "index";
    }

    /** User zone index. */
    @RequestMapping("/user/index.do")
    public String userIndex() {
        return "user/index";
    }

    /** Administration zone index. */
    @RequestMapping("/super/index.do")
    public String superIndex() {
        return "super/index";
    }

    /** Administration zone index. */
    @RequestMapping("/admin/index.do")
    public String adminIndex() {
        return "admin/index";
    }

    /** Shared zone index. */
    @RequestMapping("/shared/index.do")
    public String sharedIndex() {
        return "shared/index";
    }

    /** Login form. */
    @RequestMapping("/login.do")
    public String login() {
        return "login";
    }

    /** Login form with error. */
    @RequestMapping("/login-error.do")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    /** Simulation of an exception. */
    @RequestMapping("/simulateError.do")
    public void simulateError() {
        throw new RuntimeException("This is a simulated error message");
    }

    /** Error page. */
    @RequestMapping("/error.do")
    public String error(HttpServletRequest request, Model model) {
        model.addAttribute("errorCode", "Error " + request.getAttribute("javax.servlet.error.status_code"));
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append("<ul>");
        while (throwable != null) {
            errorMessage.append("<li>").append(HtmlEscape.escapeHtml5(throwable.getMessage())).append("</li>");
            throwable = throwable.getCause();
        }
        errorMessage.append("</ul>");
        model.addAttribute("errorMessage", errorMessage.toString());
        return "error";
    }

    /** Error page. */
    @RequestMapping("/403.do")
    public String forbidden() {
        return "403";
    }


}
