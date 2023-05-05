package com.example.sprintboottest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String homeApp(Model model, Model modelMessage)
    {
        model.addAttribute("message", "essa mensagem foi injetada via model.");
        modelMessage.addAttribute("subTitle", "testando o segundo model.");
        return "index";
    }
}
