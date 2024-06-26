package Reporting.AFA.controller;

import Reporting.AFA.Entity.Agence;
import Reporting.AFA.Entity.AppUser;
import Reporting.AFA.Security.Services.AccountServiceImpl;
import Reporting.AFA.Security.repo.UserRepository;
import Reporting.AFA.dto.AgentDto;
import Reporting.AFA.dto.ProfilDto;
import Reporting.AFA.services.AgenceService;
import Reporting.AFA.services.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/admin/agents")
public class WebAgentController {

    private final AgentService agentService;
    private final AgenceService agenceService;
    private final AccountServiceImpl accountService;
    private final AccountServiceImpl userService;
    private final UserRepository userRepository;

    @Autowired
    public WebAgentController(AgentService agentService, AgenceService agenceService, AccountServiceImpl accountService, AccountServiceImpl userService, UserRepository userRepository) {
        this.agentService = agentService;
        this.agenceService = agenceService;
        this.accountService = accountService;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/new")
    public String showNewAgentForm(Model model) {
        List<AppUser> utilisateurs = accountService.getAllUsers(); // Assurez-vous d'avoir une méthode getAllUsers() dans votre UserService
        List<Agence> agences = agenceService.getAllAgences(); // Assurez-vous d'avoir une méthode getAllAgences() dans votre AgenceService

        model.addAttribute("agentDto", new AgentDto());
        model.addAttribute("utilisateurs", utilisateurs);
        model.addAttribute("agences", agences);

        return "new";
    }

    @PostMapping("/new")
    public String createAgent(@ModelAttribute("agentDto") AgentDto agentDto) {
        agentService.createAgent(agentDto);
        return "redirect:/admin/agents/list";
    }

    @GetMapping("/list")
    public String showAllAgents(Model model) {
        model.addAttribute("agents", agentService.getAllAgents());
        return "list";
    }

    @GetMapping("/delete/{agentId}")
    public String deleteAgent(@PathVariable Long agentId) {
        agentService.deleteAgent(agentId);
        return "redirect:/admin/agents/list";
    }

    @GetMapping("/assign/{agentId}")
    public String showAssignAgentForm(@PathVariable Long agentId, Model model) {
        model.addAttribute("agentId", agentId);

        // Ajoutez la liste des agences au modèle
        List<Agence> agences = agenceService.getAllAgences();
        model.addAttribute("agences", agences);

        // Ajoutez un nouvel AgentDto au modèle
        model.addAttribute("agentDto", new AgentDto());

        return "assign";
    }

    @PostMapping("/assign/{agentId}")
    public String assignAgentToAnotherAgence(
            @PathVariable Long agentId,
            @ModelAttribute("agentDto") AgentDto agentDto) {
        agentService.assignAgentToAnotherAgence(agentId, agentDto.getAgenceNom());
        return "redirect:/admin/agents/list";
    }

    @GetMapping("/admin-page")
    public String showAdminPage(Model model) {
        model.addAttribute("adminData", accountService.getAdmin());
        return "admin-page"; // Assuming you have an HTML file named "admin-page.html" in your templates folder
    }

    @GetMapping("/add-admin")
    public String addAdmin(Model model) {
        List usernames = accountService.getUser();
        model.addAttribute("Username", usernames);
        return "add-admin";
    }

    @PostMapping("/add-admin")
    public String submitAdmin(@RequestParam("selectedUsername") String selectedUsername) {

        accountService.addRoleToUser(selectedUsername,"ADMIN");
        return "redirect:/admin/agents/admin-page"; // Redirection vers une page appropriée après avoir soumis les données
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
        return "redirect:/admin/agents/Profil";
    }

    @GetMapping("/Password")
    public String afficherPageMotDePasse() {
        return "motdepasse";
    }

    @PostMapping("/Password")
    public String traiterMotDePasse(@RequestParam("password") String password, Principal principal, Model model) {
        String username = principal.getName();
        AppUser appUser = userService.loadUserByUsername(username);

        String id = appUser.getId();
        String oldPassword = appUser.getPassword();

        if (Objects.equals(oldPassword, password)) {
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


