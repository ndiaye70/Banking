package Reporting.AFA.controller;

import Reporting.AFA.dto.OuvertureCpteEntrepriseDto;
import Reporting.AFA.Entity.OuvertureCpteEntreprise;
import Reporting.AFA.services.OuvertureCpteEntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ouvertureCpteEntreprise")
public class OuvertureCpteEntrepriseController {

    private final OuvertureCpteEntrepriseService ouvertureCpteEntrepriseService;

    @Autowired
    public OuvertureCpteEntrepriseController(OuvertureCpteEntrepriseService ouvertureCpteEntrepriseService) {
        this.ouvertureCpteEntrepriseService = ouvertureCpteEntrepriseService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveOuvertureCpteEntreprise(@RequestBody OuvertureCpteEntrepriseDto ouvertureCpteEntrepriseDto) {
        try {
            // Enregistrez l'ouverture de compte dans la base de données
            OuvertureCpteEntreprise ouvertureCpteEntreprise = ouvertureCpteEntrepriseService.saveOuvertureCpteEntreprise(ouvertureCpteEntrepriseDto);
            return ResponseEntity.ok("Ouverture de compte enregistrée avec succès. ID: " + ouvertureCpteEntreprise.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'enregistrement de l'ouverture de compte");
        }
    }

    @GetMapping("/getAll")
    public List<OuvertureCpteEntreprise> getAllOuvertureCpteEntreprises() {
        return ouvertureCpteEntrepriseService.getAllOuvertureCpteEntreprises();
    }

    @GetMapping("/{ouvertureCpteEntrepriseId}")
    public ResponseEntity<OuvertureCpteEntreprise> getOuvertureCpteEntrepriseById(@PathVariable String ouvertureCpteEntrepriseId) {
        Optional<OuvertureCpteEntreprise> ouvertureCpteEntreprise = ouvertureCpteEntrepriseService.getOuvertureCpteEntrepriseById(ouvertureCpteEntrepriseId);
        return ouvertureCpteEntreprise.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{ouvertureCpteEntrepriseId}")
    public ResponseEntity<String> deleteOuvertureCpteEntrepriseById(@PathVariable String ouvertureCpteEntrepriseId) {
        try {
            // Supprimez l'ouverture de compte de la base de données
            ouvertureCpteEntrepriseService.deleteOuvertureCpteEntrepriseById(ouvertureCpteEntrepriseId);
            return ResponseEntity.ok("Ouverture de compte supprimée avec succès. ID: " + ouvertureCpteEntrepriseId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la suppression de l'ouverture de compte");
        }
    }
}

