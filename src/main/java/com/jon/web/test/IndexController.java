package com.jon.web.test;

import com.jon.web.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController extends BaseController {

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/reserve")
    public String reserveCommodity(){
        return "reserveCommodity";
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