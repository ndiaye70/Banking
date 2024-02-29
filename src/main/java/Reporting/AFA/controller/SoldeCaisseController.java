package Reporting.AFA.controller;

import Reporting.AFA.Entity.SoldeCaisse;
import Reporting.AFA.Repository.SoldeCaisseRepository;
import Reporting.AFA.dto.SoldeCaisseDto;
import Reporting.AFA.services.SoldeCaisseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/soldeCaisse")
public class SoldeCaisseController {

    @Autowired
    private SoldeCaisseRepository soldeCaisseRepository;

    @Autowired
    private final SoldeCaisseService soldeCaisseService;

    @Autowired
    public SoldeCaisseController(SoldeCaisseService soldeCaisseService) {
        this.soldeCaisseService = soldeCaisseService;
    }

    @GetMapping("/list")
    public String getSoldeCaisses(Model model) {
        List<SoldeCaisse> soldeCaisses = soldeCaisseService.getAllSoldeCaisses();
        model.addAttribute("soldeCaisses", soldeCaisses);
        return "listSoldeCaisse";
    }

    @GetMapping("/save")
    public String showCreateSoldeCaisseForm(Model model) {
        model.addAttribute("soldeCaisse", new SoldeCaisseDto());
        return "createSoldeCaisse";
    }

    @PostMapping("/save")
    public String saveSoldeCaisse(@ModelAttribute SoldeCaisseDto soldeCaisseDto, Model model) {
        try {
            soldeCaisseService.saveSoldeCaisse(soldeCaisseDto);
            model.addAttribute("success", "Solde de caisse enregistré avec succès");
            return "redirect:/recaps/soldes";
        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors de l'enregistrement du solde de caisse");
        }
        return "createSoldeCaisse";
    }

    @GetMapping("/{id}/edit")
    public String showUpdateSoldeCaisseForm(@PathVariable("id") long id, Model model) {
        Optional<SoldeCaisse> soldeCaisse = soldeCaisseService.getSoldeCaisseById(id);
        model.addAttribute("soldeCaisse", soldeCaisse);
        return "editSoldeCaisse";
    }

    @PostMapping("/{id}/edit")
    public String updateSoldeCaisse(@PathVariable("id") long id, @ModelAttribute SoldeCaisseDto soldeCaisseDto, Model model) {


        SoldeCaisse soldeCaisse=soldeCaisseDto.toEntity();
        try {

            soldeCaisseService.updateSoldeCaisse(id,soldeCaisse);
            model.addAttribute("success", "Solde de caisse mis à jour avec succès");

            return "redirect:/recaps/soldes";
        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors de la mise à jour du solde de caisse");
        }
        return "soldeCaisse/edit";
    }

    @GetMapping("/{id}/delete")
    public String deleteSoldeCaisse(@PathVariable("id") long id, Model model) {
        try {
            soldeCaisseService.deleteSoldeCaisseById(id);
            model.addAttribute("success", "Solde de caisse supprimé avec succès");
        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors de la suppression du solde de caisse");
        }
        return "redirect:/soldeCaisse/list";
    }





}



