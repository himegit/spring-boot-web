package com.jon.web.test;

import com.jon.service.UserService;
import com.jon.web.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController extends BaseController {

    @Autowired
    private UserService userService;

    @GetMapping(value = {"/","/homePage"})
    public String index() {
//        userService.userSave();
        return "/homePage";
    }

    @GetMapping("/reserve")
    public String reserveCommodity(){
        return "reserveCommodity";
    }

    @GetMapping("/visittoonuch")
    public String visittoonuch(){
        return "error/visittoonuch";
    }

    @GetMapping("/login")
    public String login(){
        return "authorization/login";
    }

    @RequestMapping("/layout")
    public String layout() {
        return "layout";
    }


    @RequestMapping("/home")
    public String home() {
        return "home";
    }

}