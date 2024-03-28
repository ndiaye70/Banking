package Reporting.AFA.controller;

import Reporting.AFA.Entity.AppUser;
import Reporting.AFA.Security.Services.AccountServiceImpl;
import Reporting.AFA.Security.repo.UserRepository;
import Reporting.AFA.dto.ProfilDto;
import Reporting.AFA.services.AgenceService;
import Reporting.AFA.services.AgentService;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
public class ProfilController {

    private final AgentService agentService;

    private final PasswordEncoder passwordEncoder;

    private final AgenceService agenceService;
    private final AccountServiceImpl accountService;
    private final AccountServiceImpl userService;
    private final UserRepository userRepository;

    @Autowired
    public ProfilController(AgentService agentService, PasswordEncoder passwordEncoder, AgenceService agenceService, AccountServiceImpl accountService, AccountServiceImpl userService, UserRepository userRepository) {
        this.agentService = agentService;
        this.passwordEncoder = passwordEncoder;
        this.agenceService = agenceService;
        this.accountService = accountService;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/Profil")
    public String showProfil(Model model, Principal principal){
        String username = principal.getName();

        AppUser appUser = userService.loadUserByUsername(username);
        Map<String, String> userDetails = new HashMap<>();

        userDetails.put("Nom", appUser.getNom());
        userDetails.put("Prenom", appUser.getPrenom());
        userDetails.put("Username", appUser.getUsername());
        userDetails.put("Email", appUser.getEmail());

        model.addAllAttributes(userDetails);


        return "profil";
    }

    @GetMapping("/editer")
    public String editerProfil(Model model, Principal principal) {
        String username = principal.getName();
        AppUser appUser = userService.loadUserByUsername(username);

        // Ajoutez les détails de l'utilisateur au modèle pour pré-remplir les champs du formulaire
        ProfilDto profileDTO = new ProfilDto();
        profileDTO.setNom(appUser.getNom());
        profileDTO.setPrenom(appUser.getPrenom());
        profileDTO.setUsername(appUser.getUsername());
        profileDTO.setEmail(appUser.getEmail());
        model.addAttribute("profileDTO", profileDTO);

        // Retournez le nom de la vue correspondant à la page d'édition de profil
        return "editProfil";
    }

    @PostMapping("/editer")
    public String sauvegarderProfil(@ModelAttribute("profileDTO") ProfilDto profileDTO, Principal principal) {
        String username = principal.getName();
        AppUser appUser = userService.loadUserByUsername(username);

        // Mettre à jour les détails de l'utilisateur avec les valeurs du DTO
        appUser.setNom(profileDTO.getNom());
        appUser.setPrenom(profileDTO.getPrenom());
        appUser.setUsername(profileDTO.getUsername());
        appUser.setEmail(profileDTO.getEmail());

        userRepository.save(appUser);



        // Sauvegarder les modifications dans la base de données ou effectuer d'autres opérations nécessaires

        // Rediriger vers une page de confirmation ou vers une autre page
        return "redirect:/Profil";
    }

    @GetMapping("/Password")
    public String afficherPageMotDePasse() {
        return "motdepasse";
    }

    @PostMapping("/Password")
    public String traiterMotDePasse(@RequestParam("password") String password, Principal principal, Model model) {

        System.out.println(password);
        String username = principal.getName();
        AppUser appUser = userService.loadUserByUsername(username);

        String id = appUser.getId();
        String oldPassword = appUser.getPassword();
        System.out.println(oldPassword);
        if (passwordEncoder.matches(password, oldPassword)) {
            // Ajouter l'ID de l'utilisateur au modèle
            model.addAttribute("id", id);
            // Renvoyer la page de réinitialisation de mot de passe
            return "resetPassword";
        } else {
            // Ajouter un message d'erreur au modèle
            model.addAttribute("errorMessage", "Mot de passe incorrect");
            // Renvoyer vers la page de saisie du mot de passe
            return "motdepasse"; // Retour à la page de saisie du mot de passe
        }
    }
}
