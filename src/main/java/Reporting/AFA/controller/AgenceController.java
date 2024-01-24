package Reporting.AFA.controller;

import Reporting.AFA.Entity.Agence;
import Reporting.AFA.dto.AgenceDto;
import Reporting.AFA.dto.OperationsDto;
import Reporting.AFA.services.AgenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/agences")
public class AgenceController {

    private final AgenceService agenceService;

    @Autowired
    public AgenceController(AgenceService agenceService) {
        this.agenceService = agenceService;
    }

    @GetMapping("/new")
    public String showCreateAgenceForm(Model model) {
        model.addAttribute("agenceDto", new AgenceDto());
        return "createAgenceForm"; // Cela correspond au nom de votre fichier HTML (createAgenceForm.html)
    }

    @PostMapping("/save")
    public String createAgence(@ModelAttribute("agenceDto") AgenceDto agenceDto ,Model model ) {
        try {
            Agence agence = agenceService.createAgence(agenceDto);
            model.addAttribute("successMessage", "Opération enregistrée avec succès. ID: " + agence.getCode());
            return "redirect:/agences/list";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Erreur lors de l'enregistrement de l'agence");
            return "error";
        }
    }

    @GetMapping("/list")
    public String agence (Model model) {
        List<Agence> Agences = agenceService.getAllAgences();
        model.addAttribute("listAgences", Agences);
        return "listAgence";
    }

    @GetMapping("/{agenceId}/delete")
    public ResponseEntity<String> deleteAgence(@PathVariable Long agenceId) {
        try {
            agenceService.deleteAgence(agenceId);
            return ResponseEntity.ok("Agence supprimée avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la suppression de l'Agence");
        }
    }

    }

