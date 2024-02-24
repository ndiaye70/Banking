package Reporting.AFA.controller;

import Reporting.AFA.dto.RegularisationDto;
import Reporting.AFA.Entity.Regularisation;
import Reporting.AFA.services.RegularisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/regularisations")
public class RegularisationController {

    private final RegularisationService regularisationService;

    @Autowired
    public RegularisationController(RegularisationService regularisationService) {
        this.regularisationService = regularisationService;
    }

    @GetMapping("/save")
    public String showRegularisationForm(Model model) {
        model.addAttribute("regularisationDto", new RegularisationDto());
        return "regularisationForm";
    }

    @PostMapping("/save")
    public String saveRegularisation(@ModelAttribute("regularisationDto") RegularisationDto regularisationDto) {
        regularisationService.saveRegularisation(regularisationDto);
        return "redirect:/regularisations/list";
    }

    @GetMapping("/list")
    public String showAllRegularisations(Model model) {
        List<Regularisation> regularisation = regularisationService.getAllRegularisations();
        model.addAttribute("regularisation", regularisation);
        return "regularisationList";
    }

    @GetMapping("/{id}/edit")
    public String showEditRegularisationForm(@PathVariable String id, Model model) {
        Optional<Regularisation> regularisation = regularisationService.getRegularisationById(id);
        if (regularisation.isPresent()) {
            Regularisation regularisation1=regularisation.get();
            model.addAttribute("regularisation", regularisation1);
            return "editRegularisation";
        } else {
            return "redirect:/regularisations/list";
        }
    }

    @PostMapping("/{id}/edit")
    public String updateRegularisation(@PathVariable ("id") String id,  RegularisationDto regularisationDto) {
        Regularisation regularisation=regularisationDto.toEntity();
        LocalDateTime now = LocalDateTime.now();

        // Formater la date avec suppression des fractions de seconde
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        regularisation.setDate(now.format(formatter));

        regularisationService.updateRegularisation(id, regularisation);
        return "redirect:/regularisations/list";
    }

    @GetMapping("/{id}/delete")
    public String deleteRegularisation(@PathVariable String id) {
        regularisationService.deleteRegularisation(id);
        return "redirect:/regularisations/list";
    }
}
