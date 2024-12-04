package com.oauth.study.oauthdemo.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/login")
public class OauthCtrl {

    @GetMapping("/form")
    public String form() {
        System.out.println("debug >>> ctrl form");
        return "login";
    }
    
    

}
