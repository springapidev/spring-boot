package com.coderbd;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class MyErrorController {

    @GetMapping(value = "/error")
    public String renderErrorPage(HttpServletRequest request, HttpServletResponse response, Model model) {
        int code = response.getStatus();
        System.out.println("code::::: "+code);
        String message = HttpStatus.valueOf(code).getReasonPhrase();
        model.addAttribute("code", code);
        model.addAttribute("message", message);
        if(code == 200){
            return "redirect:/dashboard";
        }
        return "pages/page-error";
    }

}
