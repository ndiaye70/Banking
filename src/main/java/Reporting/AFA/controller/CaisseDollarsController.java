package Reporting.AFA.controller;

import Reporting.AFA.Entity.CaisseDollars;
import Reporting.AFA.services.CaisseDollarsService;
import Reporting.AFA.services.DollarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/caisse-dollars")
public class CaisseDollarsController {

    private final CaisseDollarsService caisseDollarsService;
    private final DollarsService dollarsService;

    @Autowired
    public CaisseDollarsController(CaisseDollarsService caisseDollarsService, DollarsService dollarsService) {
        this.caisseDollarsService = caisseDollarsService;
        this.dollarsService = dollarsService;
    }



    @GetMapping("/caisse-dollars")
    public List<CaisseDollars> getAllCaisseDollars() {
        return caisseDollarsService.getAllCaisseDollars();
    }

    @GetMapping("/caisse-dollars/{caisseDollarsId}")
    public CaisseDollars getCaisseDollarsById(@PathVariable Long caisseDollarsId) {
        return caisseDollarsService.getCaisseDollarsById(caisseDollarsId);
    }
}
