package Reporting.AFA.controller;

import Reporting.AFA.Entity.*;
import Reporting.AFA.Security.Services.AccountServiceImpl;
import Reporting.AFA.dto.DollarsDto;
import Reporting.AFA.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/dollars")
public class CaisseDollarsController {

    private final AccountServiceImpl userService;
    private final AgentService agentService;
    private final CaisseDollarsService caisseService;
    private final DollarsService dollarsService;


    public CaisseDollarsController(AccountServiceImpl userService, AgentService agentService, CaisseDollarsService caisseService, DollarsService dollarsService) {
        this.userService = userService;
        this.agentService = agentService;
        this.caisseService = caisseService;
        this.dollarsService = dollarsService;
    }

    @GetMapping("/caisse")
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
        model.addAttribute("DollarsDto",new DollarsDto());

        return "add_caisseDollars";
    }

    @PostMapping("/caisse")
    public String handleBilletageForm(DollarsDto dollarsDto, Principal principal) {
        // Récupérer les informations sur l'agent connecté
        String username = principal.getName();
        AppUser appUser = userService.loadUserByUsername(username);

        // Utiliser l'ID de l'utilisateur pour obtenir l'agent correspondant
        Agent agent = agentService.findByUser(appUser);
        Dollars dollars=dollarsService.toEntity(dollarsDto);
        dollarsService.saveDollars((dollars));

        // Saisir la caisse avec les informations du formulaire et de l'agent
        caisseService.saisirCaisse(dollars, agent,"Caisse");

        // Rediriger vers la liste des caisses après la saisie
        return "redirect:/dollars/listDollars";
    }

    @GetMapping("/listDollars")
    public String showAllAgents(Model model) {
        List<CaisseDollars> caisses =caisseService.getAllCaisse();
        model.addAttribute("list_caisses", caisses);
        return "list_caisses";
    }

    @GetMapping("/caisse/{caisseId}/edit")
    public String showEditCaisseForm(@PathVariable Long caisseId, Model model, Principal principal) {
        String username = principal.getName();

        AppUser appUser = userService.loadUserByUsername(username);

        // Utiliser l'ID de l'utilisateur pour obtenir l'agent correspondant
        Agent agent = agentService.findAgentByUserId(appUser.getId());

        // Préparer la date et l'heure actuelles
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String dateHeure = now.format(formatter);


        // Pré-remplir le modèle avec les informations de l'agent et de la caisse
        model.addAttribute("agent", agent);
        model.addAttribute("appuser", appUser);
        String nom=appUser.getNom();
        String prenom=appUser.getPrenom();
        String nomComplet = prenom.concat(" ").concat(nom);
        Agence agence=agent.getAgence();
        model.addAttribute("nomComplet", nomComplet);
        model.addAttribute("agence", agence);
        model.addAttribute("dateHeure", dateHeure);
        CaisseDollars caisseDollars=caisseService.getCaisseById(caisseId);
        Dollars dollars=caisseDollars.getDollars();
        // Pré-remplir le modèle avec les informations de la caisse
        model.addAttribute("DollarsDto", dollars);

        return "edit_caisseDollars"; // Remplacez cela par le nom de votre modèle d'édition
    }

    @PostMapping("/caisse/{caisseId}/edit")
    public String handleEditCaisseForm(@PathVariable("caisseId")  Long caisseId, DollarsDto dollarsDto, Principal principal) {

        Dollars dollars=dollarsService.toEntity(dollarsDto);
        dollarsService.saveDollars(dollars);
        String username = principal.getName();
        AppUser appUser = userService.loadUserByUsername(username);
        Agent agent = agentService.findByUser(appUser);

        // Mettre à jour la caisse
        caisseService.updateCaisse(caisseId, dollars, agent);

        // Rediriger vers la liste des caisses après la mise à jour
        return "redirect:/dollars/listDollars";
    }



    // Ajoutez d'autres méthodes si nécessaire pour gérer d'autres natures de caisse





    @GetMapping("/caisse/{caisseId}/delete")
    public String deleteCaisse(@PathVariable Long caisseId) {
        caisseService.deleteCaisse(caisseId);
        return "redirect:/listDollars";
    }

    @GetMapping("/caisse/{caisseId}/detail")
    public String showCaisseDetail(@PathVariable Long caisseId, Model model) {
        CaisseDollars caisse = caisseService.getCaisseById(caisseId);
        Dollars dollars=caisse.getDollars();
        Agent agent=caisse.getAgent();
        Agence agence =agent.getAgence();
        model.addAttribute("dollars", dollars);
        model.addAttribute("agence", agence);
        model.addAttribute("agent", agent);
        AppUser user=agent.getUser();
        model.addAttribute("user", user);
        model.addAttribute("caisse", caisse);
        return "caisseDollars_detail";
    }

    @GetMapping("/{caisseId}")
    public CaisseDollars getCaisseById(@PathVariable Long caisseId) {
        return caisseService.getCaisseById(caisseId);
    }
}