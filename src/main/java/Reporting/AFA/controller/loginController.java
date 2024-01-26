package Reporting.AFA.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class loginController {

        @GetMapping("/login")
        String login() {
            return "login";
        }


        @GetMapping("/index")
        String index() {
            return "index";
        }
}
