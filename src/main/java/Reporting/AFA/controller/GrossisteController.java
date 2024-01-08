package Reporting.AFA.controller;

import Reporting.AFA.dto.GrossisteDto;
import Reporting.AFA.Entity.Grossiste;
import Reporting.AFA.services.GrossisteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/grossistes")
public class GrossisteController {

    private final GrossisteService grossisteService;

    @Autowired
    public GrossisteController(GrossisteService grossisteService) {
        this.grossisteService = grossisteService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveGrossiste(@RequestBody GrossisteDto grossisteDto) {
        try {
            // Enregistrez le grossiste dans la base de données
            Grossiste grossiste = grossisteService.saveGrossiste(grossisteDto);
            return ResponseEntity.ok("Grossiste enregistré avec succès. ID: " + grossiste.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'enregistrement du grossiste");
        }
    }

    @GetMapping("/getAll")
    public List<Grossiste> getAllGrossistes() {
        return grossisteService.getAllGrossistes();
    }

    @GetMapping("/{grossisteId}")
    public ResponseEntity<Grossiste> getGrossisteById(@PathVariable String grossisteId) {
        Optional<Grossiste> grossiste = grossisteService.getGrossisteById(grossisteId);
        return grossiste.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Ajoutez les méthodes pour la mise à jour (PUT) et la suppression (DELETE) ici

    // Exemple de méthode pour la mise à jour :
    // @PutMapping("/{grossisteId}")
    // public ResponseEntity<String> updateGrossiste(@PathVariable String grossisteId, @RequestBody GrossisteDto grossisteDto) {
    //     // Implémentez la mise à jour ici
    // }

    // Exemple de méthode pour la suppression :
    // @DeleteMapping("/{grossisteId}")
    // public ResponseEntity<String> deleteGrossiste(@PathVariable String grossisteId) {
    //     // Implémentez la suppression ici
    // }
}