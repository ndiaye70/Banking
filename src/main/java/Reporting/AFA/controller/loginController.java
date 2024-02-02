package Reporting.AFA.controller;

import Reporting.AFA.Entity.AppUser;
import Reporting.AFA.Security.Services.AccountService;
import Reporting.AFA.Security.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
public class loginController {
    @Autowired
    private final AccountService accountService;
    @Autowired
    private  final UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public loginController(AccountService accountService, UserRepository userRepository) {
        this.accountService = accountService;
        this.userRepository = userRepository;
    }
    @GetMapping("/login")
        String login() {
            return "login";

        }

        @GetMapping("/index")
        String index() {
            return "index";
        }

    @GetMapping("/forgotPass")
    String loadForgotPasword(){
            return "auth-forgot-password";

    }
    @GetMapping("/ResetPass/{id}")
    public String loadPass(@PathVariable String id, Model model) {
        model.addAttribute("id", id);
        return "resetPassword";
    }
    @PostMapping("/ResetPass/{id}")
    String changepass(@RequestParam String psw, @RequestParam String id, RedirectAttributes redirectAttributes){
        try {
            // Appel de la méthode dans le service pour modifier le mot de passe
            accountService.changeUserPassword(id, psw);

            // Ajoute un attribut flash pour la redirection
            redirectAttributes.addFlashAttribute("successMessage", "Password changed successfully");

            // Redirige l'utilisateur vers une autre page
            return "redirect:/login";
        } catch (RuntimeException e) {
            // Gère le cas où l'utilisateur n'est pas trouvé
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/forgotPass";
        }
    }


    @PostMapping("/forgotPass")
    String forgotPass(@RequestParam String email, RedirectAttributes redirectAttributes){
        AppUser user=userRepository.findByEmail(email);
        if(user!=null){
            String id=user.getId();
            return "redirect:/ResetPass/" + id;
        }
        else {
            redirectAttributes.addFlashAttribute("errorMessage","email invalide");
            return  "redirect:/forgotPass";
        }
    }

}
