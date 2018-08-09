package com.jon.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController extends BaseController{

    @RequestMapping("/index")
    public String index() {
        return "index";
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