package Reporting.AFA.controller;

        import Reporting.AFA.dto.OuvertureCompteDto;
        import Reporting.AFA.Entity.OuvertureCompte;
        import Reporting.AFA.services.OuvertureCompteService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;
        import java.util.Optional;

@RestController
@RequestMapping("/api/ouverture-comptes")
public class OuvertureCompteController {

    private final OuvertureCompteService ouvertureCompteService;

    @Autowired
    public OuvertureCompteController(OuvertureCompteService ouvertureCompteService) {
        this.ouvertureCompteService = ouvertureCompteService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveOuvertureCompte(@RequestBody OuvertureCompteDto ouvertureCompteDto) {
        try {
            OuvertureCompte ouvertureCompte = ouvertureCompteService.saveOuvertureCompte(ouvertureCompteDto);
            return ResponseEntity.ok("Ouverture de compte enregistrée avec succès. ID: " + ouvertureCompte.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'enregistrement de l'ouverture de compte");
        }
    }

    @GetMapping("/getAll")
    public List<OuvertureCompte> getAllOuvertureComptes() {
        return ouvertureCompteService.getAllOuvertureComptes();
    }

    @GetMapping("/{ouvertureCompteId}")
    public ResponseEntity<OuvertureCompte> getOuvertureCompteById(@PathVariable String ouvertureCompteId) {
        Optional<OuvertureCompte> ouvertureCompte = ouvertureCompteService.getOuvertureCompteById(ouvertureCompteId);
        return ouvertureCompte.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{ouvertureCompteId}")
    public ResponseEntity<String> deleteOuvertureCompte(@PathVariable String ouvertureCompteId) {
        try {
            ouvertureCompteService.deleteOuvertureCompteById(ouvertureCompteId);
            return ResponseEntity.ok("Ouverture de compte supprimée avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la suppression de l'ouverture de compte");
        }
    }
}

