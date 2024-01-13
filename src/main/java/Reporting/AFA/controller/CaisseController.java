package Reporting.AFA.controller;

import Reporting.AFA.Entity.*;
import Reporting.AFA.Security.Services.AccountServiceImpl;
import Reporting.AFA.dto.CaisseDto;
import Reporting.AFA.dto.EuroDto;
import Reporting.AFA.services.AgentService;
import Reporting.AFA.services.CaisseService;
import Reporting.AFA.services.EuroService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Controller
public class CaisseController {

    private final AccountServiceImpl userService;
    private final AgentService agentService;
    private final CaisseService caisseService;
    private final EuroService euroService;

    public CaisseController(AccountServiceImpl userService, AgentService agentService, CaisseService caisseService, EuroService euroService) {
        this.userService = userService;
        this.agentService = agentService;
        this.caisseService = caisseService;
        this.euroService = euroService;
    }

    @GetMapping("/billetage")
    public String showBilletageForm(Model model, Principal principal) {
        // Récupérer les informations sur l'agent connecté
        String username = principal.getName();

        AppUser appUser = userService.loadUserByUsername(username);

        // Utiliser l'ID de l'utilisateur pour obtenir l'agent correspondant
        Agent agent = agentService.findAgentByUserId(appUser.getId());

        // Préparer la date et l'heure actuelles
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String dateHeure = now.format(formatter);

        // Pré-remplir le modèle avec les informations de l'agent et de la caisse
        model.addAttribute("agent", agent);
        model.addAttribute("appuser", appUser);
        String nom=appUser.getNom();
        String prenom=appUser.getPrenom();
        String nomComplet = prenom.concat(" ").concat(nom);
        Agence agence=agent.getAgence();
        model.addAttribute("nomComplet", nomComplet);
        model.addAttribute("prenom", prenom);
        model.addAttribute("agence", agence);
        model.addAttribute("natureCaisse", "billetage");
        model.addAttribute("dateHeure", dateHeure);
        model.addAttribute("EuroDto",new EuroDto());

        return "add_caisse";
    }

    @PostMapping("/billetage")
    public String handleBilletageForm(EuroDto euroDto, Principal principal) {
        // Récupérer les informations sur l'agent connecté
        String username = principal.getName();
        AppUser appUser = userService.loadUserByUsername(username);

        // Utiliser l'ID de l'utilisateur pour obtenir l'agent correspondant
        Agent agent = agentService.findByUser(appUser);
        Euro euro=euroService.toEntity(euroDto);
        euroService.saveEuro(euro);

        // Saisir la caisse avec les informations du formulaire et de l'agent
        caisseService.saisirCaisse(euro, agent);

        // Rediriger vers la liste des caisses après la saisie
        return "redirect:/list";
    }

    @GetMapping("/list")
    public String showAllAgents(Model model) {
        model.addAttribute("caisses", caisseService.getAllCaisse());
        return "list_caisses";
    }

    // Ajoutez d'autres méthodes si nécessaire pour gérer d'autres natures de caisse





@GetMapping("/{caisseId}")
    public Caisse getCaisseById(@PathVariable Long caisseId) {
        return caisseService.getCaisseById(caisseId);
    }
}