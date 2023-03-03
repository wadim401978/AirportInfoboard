package by.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OperatorController {

    @RequestMapping("/admin.html")
    public String admin() {
        return "admin";
    }
}
