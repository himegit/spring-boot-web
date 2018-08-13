package com.jon.web.test;

import com.jon.web.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Locale;

@Controller
public class HelloController extends BaseController {
    @Autowired
    private MessageSource messageSource;

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

}