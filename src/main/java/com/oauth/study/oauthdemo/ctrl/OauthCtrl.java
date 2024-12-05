package com.oauth.study.oauthdemo.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class OauthCtrl {

    @GetMapping("/login")
    public String form() {
        System.out.println("debug >>> ctrl form");
        return "login";
    }
    
    @GetMapping("/")
    public String comfirm() {
        return "confirm";
    }
        

}
