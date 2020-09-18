package me.duohui.macaronserver.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DaumApiController {


    @RequestMapping("/daumApi")
    public String daumApi( Model model) throws Exception{
        return "daum_api";
    }


}
