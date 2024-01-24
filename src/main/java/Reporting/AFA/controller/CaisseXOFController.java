package Reporting.AFA.controller;

import Reporting.AFA.Entity.*;
import Reporting.AFA.Security.Services.AccountServiceImpl;
import Reporting.AFA.dto.DollarsDto;
import Reporting.AFA.dto.XOFDto;
import Reporting.AFA.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/xof")
public class CaisseXOFController {

    private final AccountServiceImpl userService;
    private final AgentService agentService;
    private final CaisseXOFService caisseXOFService;
    private final XOFService xofService;


    public CaisseXOFController(AccountServiceImpl userService, AgentService agentService, CaisseXOFService caisseXOFService, XOFService xofService) {
        this.userService = userService;
        this.agentService = agentService;
        this.caisseXOFService=caisseXOFService;
        this.xofService=xofService;
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
        model.addAttribute("XOF",new XOFDto());

        return "add_caisseXof";
    }

    @PostMapping("/caisse")
    public String handleBilletageForm(XOFDto xofDto, Principal principal) {
        // Récupérer les informations sur l'agent connecté
        String username = principal.getName();
        AppUser appUser = userService.loadUserByUsername(username);

        // Utiliser l'ID de l'utilisateur pour obtenir l'agent correspondant
        Agent agent = agentService.findByUser(appUser);
        XOF xof=xofService.toEntity(xofDto);
        xofService.saveXOF(xof);

        // Saisir la caisse avec les informations du formulaire et de l'agent
        caisseXOFService.saisirCaisse(xof, agent,"Caisse");

        // Rediriger vers la liste des caisses après la saisie
        return "redirect:/listXof";
    }

    @GetMapping("/listXof")
    public String showAllAgents(Model model) {
        List<CaisseXOF> caisses =caisseXOFService.getAllCaisse();
        model.addAttribute("list_caisses", caisses);
        return "list_caissesxof";
    }

    @GetMapping("/caisse/{caisseId}/edit")
    public String showEditCaisseForm(@PathVariable Long caisseId, Model model, Principal principal) {
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
        model.addAttribute("agence", agence);
        model.addAttribute("dateHeure", dateHeure);
        CaisseXOF caisseXOF=caisseXOFService.getCaisseById(caisseId);
        XOF xof=caisseXOF.getXof();
        // Pré-remplir le modèle avec les informations de la caisse
        model.addAttribute("XOFDTO", xof);

        return "edit_caisseXof"; // Remplacez cela par le nom de votre modèle d'édition
    }

    @PostMapping("/caisse/{caisseId}/edit")
    public String handleEditCaisseForm(@PathVariable("caisseId")  Long caisseId, XOFDto xofDto, Principal principal) {

        XOF xof=xofService.toEntity(xofDto);
        xofService.saveXOF(xof);
        String username = principal.getName();
        AppUser appUser = userService.loadUserByUsername(username);
        Agent agent = agentService.findByUser(appUser);

        // Mettre à jour la caisse
        caisseXOFService.updateCaisse(caisseId, xof, agent);

        // Rediriger vers la liste des caisses après la mise à jour
        return "redirect:/listXof";
    }



    // Ajoutez d'autres méthodes si nécessaire pour gérer d'autres natures de caisse





    @GetMapping("/caisse/{caisseId}/delete")
    public String deleteCaisse(@PathVariable Long caisseId) {
        caisseXOFService.deleteCaisse(caisseId);
        return "redirect:/listXof";
    }

    @GetMapping("/caisse/{caisseId}/detail")
    public String showCaisseDetail(@PathVariable Long caisseId, Model model) {
        CaisseXOF caisse = caisseXOFService.getCaisseById(caisseId);
        XOF xof=caisse.getXof();
        Agent agent=caisse.getAgent();
        Agence agence =agent.getAgence();
        model.addAttribute("xof", xof);
        model.addAttribute("agence", agence);
        model.addAttribute("agent", agent);
        AppUser user=agent.getUser();
        model.addAttribute("user", user);
        model.addAttribute("caisse", caisse);
        return "caisseXOf_detail";
    }

    @GetMapping("/{caisseId}")
    public CaisseXOF getCaisseById(@PathVariable Long caisseId) {
        return caisseXOFService.getCaisseById(caisseId);
    }
}