package Reporting.AFA.controller;

import Reporting.AFA.Entity.Agent;
import Reporting.AFA.Entity.AppUser;
import Reporting.AFA.Entity.Grossiste;
import Reporting.AFA.Security.Services.AccountServiceImpl;
import Reporting.AFA.dto.CustomCompteResult;
import Reporting.AFA.dto.CustomEntrepriseResult;
import Reporting.AFA.dto.GrossisteDto;
import Reporting.AFA.dto.OuvertureCompteDto;
import Reporting.AFA.Entity.OuvertureCompte;
import Reporting.AFA.services.AgentService;
import Reporting.AFA.services.OuvertureCompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ouvertureComptes")
public class OuvertureCompteController {

    private final OuvertureCompteService ouvertureCompteService;
    private final AccountServiceImpl userService;
    private final AgentService agentService;


    @Autowired
    public OuvertureCompteController(OuvertureCompteService ouvertureCompteService,AccountServiceImpl userService,AgentService agentService) {
        this.ouvertureCompteService = ouvertureCompteService;
        this.agentService=agentService;
        this.userService=userService;
    }

    @GetMapping("/type")
    public String shoosetype(){
        return "fenetre";
    }

    @GetMapping("/save")
    public String showCreateCompteForm(Model model){
        model.addAttribute("compteDto",new OuvertureCompteDto());
        return "createCompte";
    }


    @PostMapping("/save")
    public String saveGrossiste(@ModelAttribute("compteDto") OuvertureCompteDto ouvertureCompteDto, Model model, Principal principal) {
        String username = principal.getName();
        AppUser appUser = userService.loadUserByUsername(username);
        Agent agent = agentService.findAgentByUserId(appUser.getId());

        try {
            OuvertureCompte compte = ouvertureCompteService.saveOuvertureCompte(ouvertureCompteDto, agent);
            model.addAttribute("successMessage", "Compte créé avec succès. ID: " + compte.getId());
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Erreur lors de la création du compte");
            return "redirect:/ouvertureComptes/save";
        }

        return "redirect:/ouvertureComptes/list";
    }


    @GetMapping("/list")
    public String getCustomCompte(Model model) {
        List<CustomCompteResult> customCompteResults=ouvertureCompteService.getCustomCompte();
        model.addAttribute("listCompte", customCompteResults);
        return "listCompte";
    }


    @GetMapping("/{ouvertureCompteId}")
    public ResponseEntity<OuvertureCompte> getOuvertureCompteById(@PathVariable String ouvertureCompteId) {
        Optional<OuvertureCompte> ouvertureCompte = ouvertureCompteService.getOuvertureCompteById(ouvertureCompteId);
        return ouvertureCompte.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{ouvertureCompteId}/edit")
    public String showUpdateForm(@PathVariable String ouvertureCompteId, Model model) {
        Optional<OuvertureCompte> compteOptional=ouvertureCompteService.getOuvertureCompteById(ouvertureCompteId);


        if (compteOptional.isPresent()) {
            OuvertureCompte ouvertureCompte=compteOptional.get();
            model.addAttribute("compteDto", ouvertureCompte);
            return "editCompte";
        } else {
            // Gérer le cas où les opérations avec l'ID donné ne sont pas trouvées
            return "error";
        }
    }

    @PostMapping("/{ouvertureCompteId}/edit")
    public String updateGrossiste(@PathVariable("ouvertureCompteId") String ouvertureCompteId, OuvertureCompteDto ouvertureCompteDto, Principal principal) {

        OuvertureCompte compte=ouvertureCompteDto.toEntity();

        String username = principal.getName();

        AppUser appUser = userService.loadUserByUsername(username);

        // Utiliser l'ID de l'utilisateur pour obtenir l'agent correspondant
        Agent agent = agentService.findAgentByUserId(appUser.getId());
        compte.setAgent(agent);
        LocalDateTime now = LocalDateTime.now();

        // Formater la date avec suppression des fractions de seconde
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        compte.setDate(now.format(formatter));
        ouvertureCompteService.updateCompte(ouvertureCompteId,compte);
        return "redirect:/ouvertureComptes/list";

    }


    @GetMapping("/{ouvertureCompteId}/delete")
    public ResponseEntity<String> deleteOuvertureCompte(@PathVariable String ouvertureCompteId) {
        try {
            ouvertureCompteService.deleteOuvertureCompteById(ouvertureCompteId);
            return ResponseEntity.ok("Compte supprimé avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la suppression du Compte");
        }
    }

}


