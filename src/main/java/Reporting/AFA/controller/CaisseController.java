package Reporting.AFA.controller;

import Reporting.AFA.Entity.*;
import Reporting.AFA.Security.Services.AccountServiceImpl;
import Reporting.AFA.dto.*;
import Reporting.AFA.services.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/caisses")
public class CaisseController {

    private final AccountServiceImpl userService;
    private final AgentService agentService;
    private final CaisseService caisseService;
    private final EuroService euroService;
    private final DollarsService dollarsService;
    private final XOFService xofService;

    public CaisseController(AccountServiceImpl userService, AgentService agentService, CaisseService caisseService, EuroService euroService, DollarsService dollarsService, XOFService xofService) {
        this.userService = userService;
        this.agentService = agentService;
        this.caisseService = caisseService;
        this.euroService = euroService;
        this.dollarsService = dollarsService;
        this.xofService = xofService;
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
        model.addAttribute("natureCaisse", "");
        model.addAttribute("dateHeure", dateHeure);
        model.addAttribute("caisseForm", new CaisseForm());

        return "billetage";
    }
    @GetMapping("/ouverture")
    public String showBilletageForm1(Model model, Principal principal) {
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
        model.addAttribute("caisseForm", new CaisseForm());

        return "ouverture_caisse";
    }
    @GetMapping("/fermeture")
    public String showBilletageForm2(Model model, Principal principal) {
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
        model.addAttribute("caisseForm", new CaisseForm());

        return "fermeture_caisse";
    }
    @PostMapping("/save")
    public String handleBilletageForm(@ModelAttribute("caisseForm") CaisseForm caisseForm,
                                      @RequestParam("natureCaisse") String natureCaisse,
                                      Principal principal) {
        // Récupérer les informations sur l'agent connecté
        String username = principal.getName();
        AppUser appUser = userService.loadUserByUsername(username);

        // Utiliser l'ID de l'utilisateur pour obtenir l'agent correspondant
        Agent agent = agentService.findByUser(appUser);
        Euro euro=euroService.toEntity(caisseForm.getEuroDto());
        euroService.saveEuro(euro);
        Dollars dollars=dollarsService.toEntity(caisseForm.getDollarsDto());
        XOF xof=xofService.toEntity(caisseForm.getXofDto());
        dollarsService.saveDollars(dollars);
        xofService.saveXOF(xof);

        // Saisir la caisse avec les informations du formulaire et de l'agent
        Caisse caisse=caisseService.saisirCaisse(euro, dollars,xof,agent,natureCaisse);

        // Rediriger vers la liste des caisses après la saisie
        return "redirect:/caisses/"+caisse.getId()+"/detail";
    }

    @GetMapping("/list")
    public String showAllAgents(Model model) {
        List<Caisse> caisses =caisseService.getAllCaisse();
        model.addAttribute("list_caisses", caisses);
        return "list_caisses";
    }

    @GetMapping("/{caisseId}/edit")
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
        XOF xof=caisse.getXof();
        Dollars dollars=caisse.getDollars();
        CaisseForms caisseForms=new CaisseForms();
        caisseForms.setDollarsDto(dollars);
        caisseForms.setXofDto(xof);
        caisseForms.setEuroDto(euros);
        //model.addAttribute("euros", euros);
        Double Montant=euros.calculerMontantTotal();
        model.addAttribute("caisseForms", caisseForms);
        // Pré-remplir le modèle avec les informations de la caisse
        model.addAttribute("EuroDto",euros);
        model.addAttribute("XofDto",xof);
        model.addAttribute("DollarsDto",dollars);

        return "edit_caisse"; // Remplacez cela par le nom de votre modèle d'édition
    }

    @PostMapping("/{caisseId}/edit")
    public String handleEditCaisseForm(@PathVariable("caisseId")  Long caisseId, EuroDto euroDto,DollarsDto dollarsDto,XOFDto xofDto, Principal principal) {

        Euro euro = euroService.toEntity(euroDto);
        euroService.saveEuro(euro);
        Dollars dollars=dollarsService.toEntity(dollarsDto);
        dollarsService.saveDollars(dollars);
        XOF xof=xofService.toEntity(xofDto);
        xofService.saveXOF(xof);

        String username = principal.getName();
        AppUser appUser = userService.loadUserByUsername(username);
        Agent agent = agentService.findByUser(appUser);

        // Mettre à jour la caisse
        caisseService.updateCaisse(caisseId, euro,dollars,xof, agent);

        // Rediriger vers la liste des caisses après la mise à jour
        return "redirect:/euros/list";
    }


    @GetMapping("/JS/pdf-script.js")
    public String getJavaScript() {
        return "JS/pdf-script";
    }



    // Ajoutez d'autres méthodes si nécessaire pour gérer d'autres natures de caisse





    @GetMapping("/{caisseId}/delete")
    public String deleteCaisse(@PathVariable Long caisseId) {
        caisseService.deleteCaisse(caisseId);
        return "redirect:/caisses/list";
    }

    @GetMapping("/{caisseId}/detail")
    public String showCaisseDetail(@PathVariable Long caisseId, Model model) {
        Caisse caisse = caisseService.getCaisseById(caisseId);
        Euro euros=caisse.getEuros();
        Dollars dollars=caisse.getDollars();
        XOF xof=caisse.getXof();
        Agent agent=caisse.getAgent();
        Agence agence =agent.getAgence();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String dateHeure = now.format(formatter);
        model.addAttribute("dateHeure", dateHeure);
        model.addAttribute("euros", euros);
        model.addAttribute("dollars",dollars);
        model.addAttribute("xof",xof);
        model.addAttribute("agence", agence);
        model.addAttribute("agent", agent);
        AppUser user=agent.getUser();
        String nom=user.getNom();
        String prenom=user.getPrenom();
        String nomComplet = prenom.concat(" ").concat(nom);
        model.addAttribute("nomComplet", nomComplet);
        model.addAttribute("caisse", caisse);
        return "caisse_detail";
    }

@GetMapping("/{caisseId}")
    public Caisse getCaisseById(@PathVariable Long caisseId) {
        return caisseService.getCaisseById(caisseId);
    }
}