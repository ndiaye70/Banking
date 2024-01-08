package Reporting.AFA.controller;

import Reporting.AFA.dto.EntrepriseDto;
import Reporting.AFA.Entity.Entreprise;
import Reporting.AFA.services.EntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/entreprises")
public class EntrepriseController {

    private final EntrepriseService entrepriseService;

    @Autowired
    public EntrepriseController(EntrepriseService entrepriseService) {
        this.entrepriseService = entrepriseService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveEntreprise(@RequestBody EntrepriseDto entrepriseDto) {
        try {
            // Enregistrez l'entreprise dans la base de données
            Entreprise entreprise = entrepriseService.saveEntreprise(entrepriseDto);
            return ResponseEntity.ok("Entreprise enregistrée avec succès. ID: " + entreprise.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'enregistrement de l'entreprise");
        }
    }

    @GetMapping("/getAll")
    public List<Entreprise> getAllEntreprises() {
        return entrepriseService.getAllEntreprises();
    }

    @GetMapping("/{entrepriseId}")
    public ResponseEntity<Entreprise> getEntrepriseById(@PathVariable String entrepriseId) {
        Optional<Entreprise> entreprise = entrepriseService.getEntrepriseById(entrepriseId);
        return entreprise.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{entrepriseId}")
    public ResponseEntity<String> deleteEntrepriseById(@PathVariable String entrepriseId) {
        try {
            // Supprimez l'entreprise de la base de données
            entrepriseService.deleteEntrepriseById(entrepriseId);
            return ResponseEntity.ok("Entreprise supprimée avec succès. ID: " + entrepriseId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la suppression de l'entreprise");
        }
    }

    // Ajoutez d'autres méthodes de mise à jour (PUT) si nécessaire

    // Exemple de méthode pour la mise à jour :
    // @PutMapping("/{entrepriseId}")
    // public ResponseEntity<String> updateEntreprise(@PathVariable String entrepriseId, @RequestBody EntrepriseDto entrepriseDto) {
    //     // Implémentez la mise à jour ici
    // }
}

