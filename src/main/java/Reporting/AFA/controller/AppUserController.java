package Reporting.AFA.controller;

import Reporting.AFA.Entity.AppUser;
import Reporting.AFA.Security.Services.AccountService;
import Reporting.AFA.dto.AppUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class AppUserController {
    @Autowired
    private final AccountService accountService;

    @Autowired
    public AppUserController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/new")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userDto", new AppUserDto());
        return "registration";
    }

    @PostMapping("/new")
    public String registerUser(@ModelAttribute("userDto") @Valid AppUserDto userDto, BindingResult result) {
        if (result.hasErrors()) {
            return "registration";
        }

        try {
            accountService.NewUser(userDto);
            return "redirect:/login";
        } catch (RuntimeException e) {
            // Gérer l'exception (par exemple, afficher un message d'erreur)
            return "registration";
        }
    }
}
