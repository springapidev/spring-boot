package com.coderbd.simplethymeleaftemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/user/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "list")
    public String getList(Model model){
       List<User> list = this.userRepository.findAll();
        model.addAttribute("list",list);
        return "users/list";
    }
    @GetMapping(value = "add")
    public String addView(Model model){
       model.addAttribute("user",new User());
       return "users/add";
    }
       @PostMapping(value = "add")
    public String add(Model model,@Valid User user){
       if(user == null){
           model.addAttribute("errorMsg","Something Wrong!");
       }else{
           this.userRepository.save(user);
           model.addAttribute("successMsg","User Saved Successfully");
       }
        return "users/add";
    }

    @GetMapping(value = "edit/{id}")
    public String editView(@PathVariable Long id,Model model){
        model.addAttribute("user",this.userRepository.getOne(id));
        return "users/edit";
    }

    @PostMapping(value = "edit/{id}")
    public String edit(Model model,@Valid User user,@PathVariable Long id){
        user.setId(id);
        if(user == null){
            model.addAttribute("errorMsg","Something Wrong!");
        }else {
            this.userRepository.save(user);
        }

        return "redirect:/user/list";
    }


    @GetMapping(value = "delete/{id}")
    public String delete(@PathVariable Long id){
        this.userRepository.deleteById(id);
        return "redirect:/user/list";
    }
}
