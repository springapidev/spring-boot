package com.coderbd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Test {
    @GetMapping(value = "/user")
    public String user(){
        return "user Area........";
    }

    @GetMapping(value = "/admin")
    public String admin(){
        return "admin Area........";
    }


    @GetMapping(value = "/read")
    public String read(){
        return "read Area........";
    }

    @GetMapping(value = "/write")
    public String write(){
        return "write Area........";
    }
    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/test")
    public String test(){
        return "Test Area........";
    }
    public static Map<String, String> map = new HashMap<>();
@Autowired
SecurityFilterChain filterChain;
    @GetMapping(value = "/yes/{url}")
    public void addRoute(@PathVariable("url") String url ) {
        map.put("/"+url,"USER");
        System.out.println("yes/ok Hit");
        filterChain.getFilters().forEach((f)->{
            System.out.println("URL: "+f.toString());
        });


    }




    @GetMapping(value = "/ok")
    public String ok(){
        return "Ok Area........";
    }
}
