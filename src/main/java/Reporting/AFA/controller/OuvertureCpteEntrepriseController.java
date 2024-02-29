package Reporting.AFA.controller;

import Reporting.AFA.Entity.*;
import Reporting.AFA.Security.Services.AccountServiceImpl;
import Reporting.AFA.dto.*;
import Reporting.AFA.services.AgentService;
import Reporting.AFA.services.OuvertureCompteService;
import Reporting.AFA.services.OuvertureCpteEntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ouvertureCpte")
public class OuvertureCpteEntrepriseController {

    private final OuvertureCpteEntrepriseService ouvertureCompteService;
    private final AccountServiceImpl userService;
    private final AgentService agentService;


    @Autowired
    public OuvertureCpteEntrepriseController(OuvertureCpteEntrepriseService ouvertureCompteService,AccountServiceImpl userService,AgentService agentService) {
        this.ouvertureCompteService = ouvertureCompteService;
        this.agentService=agentService;
        this.userService=userService;
    }

    @GetMapping("/save")
    public String showCreateCompteForm(Model model){
        model.addAttribute("ouvertureCompteDto",new OuvertureCpteEntrepriseDto());
        return "createCpte";
    }

    @PostMapping("/save")
    public String saveGrossiste(@ModelAttribute("ouvertureCompteDto") OuvertureCpteEntrepriseDto ouvertureCompteDto, Model model, Principal principal, RedirectAttributes redirectAttributes) {
        String username = principal.getName();

        AppUser appUser = userService.loadUserByUsername(username);

        // Utiliser l'ID de l'utilisateur pour obtenir l'agent correspondant
        Agent agent = agentService.findAgentByUserId(appUser.getId());
        try {
            // Enregistrez le grossiste dans la base de données
            OuvertureCpteEntreprise compte = ouvertureCompteService.saveOuvertureCpteEntreprise(ouvertureCompteDto, agent);
            redirectAttributes.addFlashAttribute("successMessage", "Compte entreprise enregistré avec succès. ID: " + compte.getId());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erreur lors de l'enregistrement du compte");
        }
        return "redirect:/ouvertureCpte/list";
    }


    @GetMapping("/list")
    public String getCustomCompte(Model model) {
        List<CustomCpteEntreprise> customCompteResults=ouvertureCompteService.getCustomCpteEntreprise();
        model.addAttribute("listCompte", customCompteResults);
        return "listCpte";
    }


    @GetMapping("/{ouvertureCompteId}")
    public ResponseEntity<OuvertureCpteEntreprise> getOuvertureCompteById(@PathVariable String ouvertureCompteId) {
        Optional<OuvertureCpteEntreprise> ouvertureCompte = ouvertureCompteService.getOuvertureCpteEntrepriseById(ouvertureCompteId);
        return ouvertureCompte.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{ouvertureCompteId}/edit")
    public String showUpdateForm(@PathVariable String ouvertureCompteId, Model model) {
        Optional<OuvertureCpteEntreprise> compteOptional=ouvertureCompteService.getOuvertureCpteEntrepriseById(ouvertureCompteId);


        if (compteOptional.isPresent()) {
            OuvertureCpteEntreprise ouvertureCpteEntreprise=compteOptional.get();
            model.addAttribute("compteDto",ouvertureCpteEntreprise );
            return "editCpte";
        } else {
            // Gérer le cas où les opérations avec l'ID donné ne sont pas trouvées
            return "error";
        }
    }

    @PostMapping("/{ouvertureCompteId}/edit")
    public String updateGrossiste(@PathVariable("ouvertureCompteId") String ouvertureCompteId, OuvertureCpteEntrepriseDto ouvertureCompteDto, Principal principal) {

        OuvertureCpteEntreprise compte=ouvertureCompteDto.convertDtoToEntity();

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
        return "redirect:/ouvertureCpte/list";

    }


    @GetMapping("/{ouvertureCompteId}/delete")
    public ResponseEntity<String> deleteOuvertureCompte(@PathVariable String ouvertureCompteId) {
        try {
            ouvertureCompteService.deleteOuvertureCpteEntrepriseById(ouvertureCompteId);
            return ResponseEntity.ok("Compte supprimé avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la suppression du Compte");
        }
    }
}


