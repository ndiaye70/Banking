package Reporting.AFA.controller;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import Reporting.AFA.Entity.Agent;
import Reporting.AFA.Entity.AppUser;
import Reporting.AFA.Entity.Grossiste;
import Reporting.AFA.Security.Services.AccountServiceImpl;
import Reporting.AFA.dto.ApprovisionnementDto;
import Reporting.AFA.Entity.Approvisionnement;
import Reporting.AFA.dto.CustomApprovisionnement;
import Reporting.AFA.dto.CustomGrossisteResult;
import Reporting.AFA.dto.GrossisteDto;
import Reporting.AFA.services.AgentService;
import Reporting.AFA.services.ApprovisionnementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
@RequestMapping("/approvisionnements")
public class ApprovisionnementController {

    private final ApprovisionnementService approvisionnementService;
    private final AccountServiceImpl userService;
    private final AgentService agentService;

    @Autowired
    public ApprovisionnementController(ApprovisionnementService approvisionnementService, AccountServiceImpl userService, AgentService agentService) {
        this.approvisionnementService = approvisionnementService;
        this.agentService = agentService;
        this.userService = userService;
    }

    @GetMapping("/save")
    public String showCreateApprovisionnementForm(Model model) {
        model.addAttribute("approvisionnementDto", new ApprovisionnementDto());
        return "createApprovisionnement";
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveApprovisionnement(@ModelAttribute("approvisionnementDto") ApprovisionnementDto approvisionnementDto, Principal principal) {

        String username = principal.getName();

        AppUser appUser = userService.loadUserByUsername(username);

        // Utiliser l'ID de l'utilisateur pour obtenir l'agent correspondant
        Agent agent = agentService.findAgentByUserId(appUser.getId());
        try {
            Approvisionnement approvisionnement = approvisionnementService.saveApprovisionnement(approvisionnementDto, agent);
            return ResponseEntity.ok("Approvisionnement enregistré avec succès. ID: " + approvisionnement.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'enregistrement de l'approvisionnement");
        }
    }

    @GetMapping("/list")
    public String allApprovisionnement(Model model) {
        List<CustomApprovisionnement> customApprovisionnements = approvisionnementService.getCustomApprovisionnement();
        model.addAttribute("customApprovisionnements", customApprovisionnements);
        return "listApprovisionnement";
    }


    @GetMapping("/{approvisionnementId}/edit")
    public String showUpdateForm(@PathVariable String approvisionnementId, Model model) {
        Optional<Approvisionnement> approvisionnement = approvisionnementService.getApprovisionnementById(approvisionnementId);

        if (approvisionnement.isPresent()) {
            Approvisionnement approvisionnement1 = approvisionnement.get();
            model.addAttribute("approvisionnement1", approvisionnement1);
            return "editApprovisionnement";
        } else {
            // Gérer le cas où les opérations avec l'ID donné ne sont pas trouvées
            return "error";
        }
    }

    @PostMapping("/{approvisionnementId}/edit")
    public String updateGrossiste(@PathVariable("approvisionnementId") String approvisionnementId, ApprovisionnementDto approvisionnementDto, Principal principal) {

        Approvisionnement approvisionnement1 = approvisionnementDto.toEntity();

        String username = principal.getName();

        AppUser appUser = userService.loadUserByUsername(username);

        // Utiliser l'ID de l'utilisateur pour obtenir l'agent correspondant
        Agent agent = agentService.findAgentByUserId(appUser.getId());
        approvisionnement1.setAgent(agent);
        LocalDateTime now = LocalDateTime.now();

        // Formater la date avec suppression des fractions de seconde
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        approvisionnement1.setDate(now.format(formatter));
        approvisionnementService.updateapprovisionnement(approvisionnementId, approvisionnement1);
        return "redirect:/approvisionnements/list";

    }




    @GetMapping("/{approvisionnementId}/delete")
    public String deleteApprovisionnement(@PathVariable String approvisionnementId, RedirectAttributes redirectAttributes) {
        try {
            approvisionnementService.deleteapprovisionnement(approvisionnementId);
            redirectAttributes.addFlashAttribute("successMessage", "Approvisionnement supprimée avec succès");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erreur lors de la suppression de l'approvisionnement");
        }
        return "redirect:/approvisionnements/list";
    }







    @GetMapping("/{approvisionnementId}")
    public ResponseEntity<Approvisionnement> getApprovisionnementById(@PathVariable String approvisionnementId) {
        Optional<Approvisionnement> approvisionnement = approvisionnementService.getApprovisionnementById(approvisionnementId);
        return approvisionnement.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Ajoutez les méthodes pour la mise à jour (PUT) et la suppression (DELETE) ici
}

