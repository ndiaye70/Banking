package Reporting.AFA.controller;

import Reporting.AFA.Entity.*;
import Reporting.AFA.Security.Services.AccountServiceImpl;
import Reporting.AFA.dto.CaisseDto;
import Reporting.AFA.dto.EuroDto;
import Reporting.AFA.services.AgentService;
import Reporting.AFA.services.CaisseService;
import Reporting.AFA.services.EuroService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.file.Files;
import org.springframework.http.MediaType;
import java.nio.file.Path;


@Controller
@RequestMapping("/euros")
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
        model.addAttribute("natureCaisse", "");
        model.addAttribute("dateHeure", dateHeure);
        model.addAttribute("EuroDto",new EuroDto());

        return "add_caisse";
    }

    @PostMapping("/caisse")
    public String handleBilletageForm(@RequestParam("natureCaisse") String natureCaisse,EuroDto euroDto, Principal principal) {
        // Récupérer les informations sur l'agent connecté
        String username = principal.getName();
        AppUser appUser = userService.loadUserByUsername(username);

        // Utiliser l'ID de l'utilisateur pour obtenir l'agent correspondant
        Agent agent = agentService.findByUser(appUser);
        Euro euro=euroService.toEntity(euroDto);
        euroService.saveEuro(euro);

        // Saisir la caisse avec les informations du formulaire et de l'agent
        caisseService.saisirCaisse(euro, agent,natureCaisse);

        // Rediriger vers la liste des caisses après la saisie
        return "redirect:/euros/list";
    }

    @GetMapping("/list")
    public String showAllAgents(Model model) {
        List<Caisse> caisses =caisseService.getAllCaisse();
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
        Caisse caisse=caisseService.getCaisseById(caisseId);
        Euro euros=caisse.getEuros();
        //model.addAttribute("euros", euros);
        Double Montant=euros.calculerMontantTotal();
        model.addAttribute("Montant", Montant);
        // Pré-remplir le modèle avec les informations de la caisse
        model.addAttribute("EuroDto",euros);

        return "edit_caisse"; // Remplacez cela par le nom de votre modèle d'édition
    }

    @PostMapping("/caisse/{caisseId}/edit")
    public String handleEditCaisseForm(@PathVariable("caisseId")  Long caisseId, EuroDto euroDto, Principal principal) {

        Euro euro = euroService.toEntity(euroDto);
        euroService.saveEuro(euro);
        String username = principal.getName();
        AppUser appUser = userService.loadUserByUsername(username);
        Agent agent = agentService.findByUser(appUser);

        // Mettre à jour la caisse
        caisseService.updateCaisse(caisseId, euro, agent);

        // Rediriger vers la liste des caisses après la mise à jour
        return "redirect:/euros/list";
    }


    @GetMapping("/JS/pdf-script.js")
    public String getJavaScript() {
        return "JS/pdf-script";
    }



    // Ajoutez d'autres méthodes si nécessaire pour gérer d'autres natures de caisse





    @GetMapping("/caisse/{caisseId}/delete")
    public String deleteCaisse(@PathVariable Long caisseId) {
        caisseService.deleteCaisse(caisseId);
        return "redirect:/euros/list";
    }

    @GetMapping("/caisse/{caisseId}/detail")
    public String showCaisseDetail(@PathVariable Long caisseId, Model model) {
        Caisse caisse = caisseService.getCaisseById(caisseId);
        Euro euros=caisse.getEuros();
        Agent agent=caisse.getAgent();
        Agence agence =agent.getAgence();
        model.addAttribute("euros", euros);
        model.addAttribute("agence", agence);
        model.addAttribute("agent", agent);
        AppUser user=agent.getUser();
        model.addAttribute("user", user);
        model.addAttribute("caisse", caisse);
        return "caisse_detail";
    }

@GetMapping("/{caisseId}")
    public Caisse getCaisseById(@PathVariable Long caisseId) {
        return caisseService.getCaisseById(caisseId);
    }
}