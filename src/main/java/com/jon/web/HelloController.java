package com.jon.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HelloController extends BaseController{

    @RequestMapping("/")
    public String index(ModelMap map, HttpServletRequest request) {
        map.addAttribute("message", request.getLocalName()+":"+request.getLocalPort());
        return "hello";
    }

}