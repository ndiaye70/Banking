package Reporting.AFA.controller;

import Reporting.AFA.dto.ApprovisionnementDto;
import Reporting.AFA.Entity.Approvisionnement;
import Reporting.AFA.services.ApprovisionnementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/approvisionnements")
public class ApprovisionnementController {

    private final ApprovisionnementService approvisionnementService;

    @Autowired
    public ApprovisionnementController(ApprovisionnementService approvisionnementService) {
        this.approvisionnementService = approvisionnementService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveApprovisionnement(@RequestBody ApprovisionnementDto approvisionnementDto) {
        try {
            Approvisionnement approvisionnement = approvisionnementService.saveApprovisionnement(approvisionnementDto);
            return ResponseEntity.ok("Approvisionnement enregistré avec succès. ID: " + approvisionnement.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'enregistrement de l'approvisionnement");
        }
    }


    @GetMapping("/{approvisionnementId}")
    public ResponseEntity<Approvisionnement> getApprovisionnementById(@PathVariable String approvisionnementId) {
        Optional<Approvisionnement> approvisionnement = approvisionnementService.getApprovisionnementById(approvisionnementId);
        return approvisionnement.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Ajoutez les méthodes pour la mise à jour (PUT) et la suppression (DELETE) ici
}

