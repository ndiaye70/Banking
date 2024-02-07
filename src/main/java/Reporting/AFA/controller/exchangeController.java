package Reporting.AFA.controller;

import Reporting.AFA.Entity.CourDuJour;
import Reporting.AFA.dto.CourDuJourDto;
import Reporting.AFA.dto.CustomGrossisteResult;
import Reporting.AFA.dto.Customchange;
import Reporting.AFA.services.CourDuJourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/exchange")
public class exchangeController {

    private final CourDuJourService courDuJourService;

    @Autowired
    public exchangeController(CourDuJourService courDuJourService) {
        this.courDuJourService = courDuJourService;
    }

    @GetMapping("/list")
    public String listCourDuJour(Model model) {
        List<CourDuJour> courDuJourList = courDuJourService.getAllCourDuJour();
        model.addAttribute("courDuJourList", courDuJourList);
        return "listCourDuJour";
    }
    @GetMapping("/new")
    public String showCourDuJour(Model model) {
        model.addAttribute("exchange", new CourDuJourDto());
        return "createExchange";
    }

    @PostMapping("/new")
    public String createCourDuJour(@ModelAttribute("exchange") CourDuJourDto courDuJourDto) {
        courDuJourService.saveCourDuJour(courDuJourDto);
        return "redirect:/exchange/list";
    }


    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Optional<CourDuJour> courDuJour = courDuJourService.getCourDuJourById(id);
        courDuJour.ifPresent(value -> model.addAttribute("courDuJour", value));
        return "editExchange";
    }

    @PostMapping("/edit/{id}")
    public String updateCourDuJour(@PathVariable Long id, @ModelAttribute CourDuJourDto courDuJourDto) {
        CourDuJour courDuJour=courDuJourDto.toEntity();

        courDuJourService.updateExchange(id,courDuJour);
        return "redirect:/exchange/list";
    }



    @GetMapping("/delete/{id}")
    public ResponseEntity<String> deleteCourDuJour(@PathVariable Long id) {
        try {
            courDuJourService.deleteCourDuJour(id);
            return ResponseEntity.ok("Cour du jour supprimée avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la suppression de la cour du jour");
        }
    }
}

