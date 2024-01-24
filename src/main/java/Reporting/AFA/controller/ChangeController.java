package Reporting.AFA.controller;

import Reporting.AFA.Entity.Agent;
import Reporting.AFA.Entity.AppUser;
import Reporting.AFA.Security.Services.AccountServiceImpl;
import Reporting.AFA.dto.ChangeDto;
import Reporting.AFA.Entity.Change;
import Reporting.AFA.services.AgentService;
import Reporting.AFA.services.ChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/changes")
public class ChangeController {

    private final ChangeService changeService;
    private final AccountServiceImpl userService;
    private final AgentService agentService;

    @Autowired
    public ChangeController(ChangeService changeService, AccountServiceImpl userService, AgentService agentService) {
        this.changeService = changeService;
        this.agentService = agentService;
        this.userService = userService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveChange(@ModelAttribute("changeDto") ChangeDto changeDto, Model model, Principal principal) {

        String username = principal.getName();

        AppUser appUser = userService.loadUserByUsername(username);

        // Utiliser l'ID de l'utilisateur pour obtenir l'agent correspondant
        Agent agent = agentService.findAgentByUserId(appUser.getId());
        try {
            // Enregistrez le changement dans la base de données
            Change change = changeService.saveChange(changeDto, agent);
            return ResponseEntity.ok("Changement enregistré avec succès. ID: " + change.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'enregistrement du changement");
        }
    }

    @GetMapping("/save")
    public String showChangeForm(Model model) {
        model.addAttribute("changeDto", new ChangeDto());
        return "createChange";
    }

    @GetMapping("/list")
    public String getAllChanges(Model model) {

        List<Change> change = changeService.getAllChanges();
        model.addAttribute("list_change", change);
        return "list_change";
    }


    // Ajoutez les méthodes pour la mise à jour (PUT) et la suppression (DELETE) ici

    // Exemple de méthode pour la mise à jour :
    // @PutMapping("/{changeId}")
    // public ResponseEntity<String> updateChange(@PathVariable String changeId, @RequestBody ChangeDto changeDto) {
    //     // Implémentez la mise à jour ici
    // }


    @GetMapping("/{changeId}")
    public String deleteChange(@PathVariable String changeId) {
        changeService.deleteChangeById(changeId);
        return "redirect/changes/list";
        // Implémentez la suppression ici

    }
}