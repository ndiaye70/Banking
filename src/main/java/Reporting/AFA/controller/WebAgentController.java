package Reporting.AFA.controller;

import Reporting.AFA.Entity.Agence;
import Reporting.AFA.Entity.AppUser;
import Reporting.AFA.Security.Services.AccountServiceImpl;
import Reporting.AFA.dto.AgentDto;
import Reporting.AFA.services.AgenceService;
import Reporting.AFA.services.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/agents")
public class WebAgentController {

    private final AgentService agentService;
    private final AgenceService agenceService;
    private final AccountServiceImpl accountService;

    @Autowired
    public WebAgentController(AgentService agentService, AgenceService agenceService, AccountServiceImpl accountService) {
        this.agentService = agentService;
        this.agenceService = agenceService;
        this.accountService = accountService;
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


}


