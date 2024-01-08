package Reporting.AFA.controller;

import Reporting.AFA.dto.EuroDto;
import Reporting.AFA.Entity.Caisse;
import Reporting.AFA.Entity.Euro;
import Reporting.AFA.services.CaisseService;
import Reporting.AFA.services.EuroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/euro")
public class EuroController {

    private final EuroService euroService;
    private final CaisseService caisseService;

    @Autowired
    public EuroController(EuroService euroService, CaisseService caisseService) {
        this.euroService = euroService;
        this.caisseService = caisseService;
    }

    @PostMapping("/convertir-dto")
    public ResponseEntity<String> convertir (@RequestBody EuroDto euroDto) {
        try {
            // Transformez le EuroDto en objet Euro et enregistrez-le dans la base de données
            Euro euro = euroService.toEntity(euroDto);
            euroService.saveEuro(euro);
            Caisse caisse= new Caisse(euro);
            caisseService.saveCaisse(caisse);


            return ResponseEntity.ok("Euro enregistré avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'enregistrement de l'euro");
        }
    }

    @GetMapping("/euro")
    public List<Euro> getAllEuro() {
        return euroService.getAllEuro();
    }

    @GetMapping("/euro/{euroId}")
    public Euro getEuroById(@PathVariable Long euroId) {
        return euroService.getEuroById(euroId);
    }
}
