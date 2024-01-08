package Reporting.AFA.controller;

import Reporting.AFA.Entity.CaisseXOF;
import Reporting.AFA.services.CaisseXOFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/caisse-xof")
public class CaisseXOFController {

    private final CaisseXOFService caisseXOFService;

    @Autowired
    public CaisseXOFController(CaisseXOFService caisseXOFService) {
        this.caisseXOFService = caisseXOFService;
    }



    @GetMapping("/caisse-xof")
    public List<CaisseXOF> getAllCaisseXOF() {
        return caisseXOFService.getAllCaisseXOF();
    }

    @GetMapping("/caisse-xof/{caisseXOFId}")
    public CaisseXOF getCaisseXOFById(@PathVariable Long caisseXOFId) {
        return caisseXOFService.getCaisseXOFById(caisseXOFId);
    }
}
