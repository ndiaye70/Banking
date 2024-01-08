package Reporting.AFA.controller;

import Reporting.AFA.dto.ChangeDto;
import Reporting.AFA.Entity.Change;
import Reporting.AFA.services.ChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/changes")
public class ChangeController {

    private final ChangeService changeService;

    @Autowired
    public ChangeController(ChangeService changeService) {
        this.changeService = changeService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveChange(@RequestBody ChangeDto changeDto) {
        try {
            // Enregistrez le changement dans la base de données
            Change change = changeService.saveChange(changeDto);
            return ResponseEntity.ok("Changement enregistré avec succès. ID: " + change.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'enregistrement du changement");
        }
    }

    @GetMapping("/getAll")
    public List<Change> getAllChanges() {
        return changeService.getAllChanges();
    }

    @GetMapping("/{changeId}")
    public ResponseEntity<Change> getChangeById(@PathVariable String changeId) {
        Optional<Change> change = changeService.getChangeById(changeId);
        return change.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Ajoutez les méthodes pour la mise à jour (PUT) et la suppression (DELETE) ici

    // Exemple de méthode pour la mise à jour :
    // @PutMapping("/{changeId}")
    // public ResponseEntity<String> updateChange(@PathVariable String changeId, @RequestBody ChangeDto changeDto) {
    //     // Implémentez la mise à jour ici
    // }

    // Exemple de méthode pour la suppression :
    // @DeleteMapping("/{changeId}")
    // public ResponseEntity<String> deleteChange(@PathVariable String changeId) {
    //     // Implémentez la suppression ici
    // }
}