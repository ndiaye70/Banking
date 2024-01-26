package Reporting.AFA.controller;

import Reporting.AFA.Entity.AppUser;
import Reporting.AFA.Security.Services.AccountService;
import Reporting.AFA.dto.AppUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class AppUserController {

    private final AccountService accountService;

    @Autowired
    public AppUserController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/new")
    public String profile(Model model) {
        model.addAttribute("user", new AppUserDto());
        return "registration";
    }

    @PostMapping("/new")
    public String profilePost(@ModelAttribute("user") AppUserDto appUserDto) {
        try {
            accountService.save(appUserDto);
            return "redirect:/login";
        } catch (Exception e) {
            return "error";
        }
    }
}
