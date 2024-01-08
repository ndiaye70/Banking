package Reporting.AFA.controller;

import Reporting.AFA.dto.DollarsDto;
import Reporting.AFA.Entity.CaisseDollars;
import Reporting.AFA.Entity.Dollars;
import Reporting.AFA.services.CaisseDollarsService;
import Reporting.AFA.services.DollarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dollars")
public class DollarsController {

    private final DollarsService dollarsService;
    private final CaisseDollarsService caisseDollarsService;

    @Autowired
    public DollarsController(DollarsService dollarsService, CaisseDollarsService caisseDollarsService) {
        this.dollarsService = dollarsService;
        this.caisseDollarsService = caisseDollarsService;
    }

    @PostMapping("/convertir-dto")
    public ResponseEntity<String> convertir(@RequestBody DollarsDto dollarsDto) {
        try {
            // Transformez le DollarsDto en objet Dollars et enregistrez-le dans la base de données
            Dollars dollars = dollarsService.toEntity(dollarsDto);
            dollarsService.saveDollars(dollars);
            CaisseDollars caisseDollars = new CaisseDollars(dollars);
            caisseDollarsService.saveCaisseDollars(caisseDollars);

            return ResponseEntity.ok("Dollars enregistrés avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'enregistrement des dollars");
        }
    }

    @GetMapping("/dollars")
    public List<Dollars> getAllDollars() {
        return dollarsService.getAllDollars();
    }

    @GetMapping("/dollars/{dollarsId}")
    public Dollars getDollarsById(@PathVariable Long dollarsId) {
        return dollarsService.getDollarsById(dollarsId);
    }
}
