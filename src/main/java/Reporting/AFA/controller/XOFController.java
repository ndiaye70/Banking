package Reporting.AFA.controller;

import Reporting.AFA.dto.XOFDto;
import Reporting.AFA.Entity.CaisseXOF;
import Reporting.AFA.Entity.XOF;
import Reporting.AFA.services.CaisseXOFService;
import Reporting.AFA.services.XOFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/xof")
public class XOFController {

    private final XOFService xofService;
    private final CaisseXOFService caisseXOFService;

    @Autowired
    public XOFController(XOFService xofService, CaisseXOFService caisseXOFService) {
        this.xofService = xofService;
        this.caisseXOFService = caisseXOFService;
    }

    @PostMapping("/convertir-dto")
    public ResponseEntity<String> convertir(@RequestBody XOFDto xofDto) {
        try {
            // Transformez le CaisseXOFDto en objet CaisseXOF et enregistrez-le dans la base de données
            XOF xof = xofService.toEntity(xofDto);
            xofService.saveXOF(xof);
            CaisseXOF caisseXOF =new CaisseXOF(xof);
            caisseXOFService.saveCaisseXOF(caisseXOF);

            return ResponseEntity.ok("Caisse XOF enregistrée avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'enregistrement de la caisse XOF");
        }
    }

    @GetMapping("/xof")
    public List<XOF> getAllXOF() {
        return xofService.getAllXOF();
    }

    @GetMapping("/xof/{xofId}")
    public XOF getXOFById(@PathVariable Long xofId) {
        return xofService.getXOFById(xofId);
    }
}
