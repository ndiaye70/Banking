package Reporting.AFA.controller;

import Reporting.AFA.dto.CaisseDto;
import Reporting.AFA.Entity.Caisse;
import Reporting.AFA.services.CaisseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/caisse")
public class CaisseController {

    private final CaisseService caisseService;

    @Autowired
    public CaisseController(CaisseService caisseService) {
        this.caisseService = caisseService;
    }

    @GetMapping("/Caisse")
    public String operations (Model model){
    List<Caisse> caisse= caisseService.getAllCaisse() ;
    model.addAttribute("listCaisse", caisse);
        return "allcaisse";
    }

    @PostMapping("/test-enregistrer")
    public ResponseEntity<String> testEnregistrerCaisse(@RequestBody CaisseDto testDto) {
        try {
            // Transformez le CaisseDto en objet Caisse et enregistrez-le dans la base de données
            Caisse caisse = caisseService.convertirDtoEnCaisse(testDto);
            caisseService.saveCaisse(caisse);

            return ResponseEntity.ok("Caisse enregistrée avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'enregistrement de la caisse");
        }
    }


    @GetMapping("/{caisseId}")
    public Caisse getCaisseById(@PathVariable Long caisseId) {
        return caisseService.getCaisseById(caisseId);
    }
}