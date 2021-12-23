package com.example.autosalon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class MainController {



    @PostMapping("/admin")
    public @ResponseBody String getRevenue(Model model, @RequestParam("start") Date start, @RequestParam("start") Date end) {

        return "index";
    }
}
