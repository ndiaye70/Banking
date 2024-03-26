package Reporting.AFA.controller;

import Reporting.AFA.Entity.*;
import Reporting.AFA.Security.Services.AccountServiceImpl;
import Reporting.AFA.dto.CustomCarte;
import Reporting.AFA.dto.CarteDto;
import Reporting.AFA.dto.CustomGrossisteResult;
import Reporting.AFA.services.AgentService;
import Reporting.AFA.services.CarteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cartes")
public class CarteController {

    private final CarteService carteService;
    private final AccountServiceImpl userService;
    private final AgentService agentService;

    @Autowired
    public CarteController(CarteService carteService, AccountServiceImpl userService, AgentService agentService) {
        this.carteService = carteService;
        this.userService = userService;
        this.agentService = agentService;
    }

    @GetMapping("/save")
    public String showCreateCarteForm(Model model) {
        model.addAttribute("carteDto", new CarteDto());
        return "CreateCarte";
    }

    @PostMapping("/save")
    public String saveCarte(@ModelAttribute CarteDto carteDto, Principal principal, Model model) {
        String username = principal.getName();
        AppUser appUser = userService.loadUserByUsername(username);

        // Utiliser l'ID de l'utilisateur pour obtenir l'agent correspondant
        Agent agent = agentService.findAgentByUserId(appUser.getId());
        try {
            // Enregistrez la carte dans la base de données
            Carte carte = carteService.saveCarte(carteDto, agent);

            // Rediriger vers la page de détails de la carte nouvellement créée
            return "redirect:/cartes/list" +
                    "3";
        } catch (Exception e) {
            // Gérer les erreurs et afficher un message approprié
            model.addAttribute("error", "Erreur lors de l'enregistrement de la carte");
            return "CreateCarte"; // Vous pouvez également rediriger vers la page de création avec un message d'erreur
        }
    }

    @GetMapping("/{carteId}/details")
    public String getCarteDetails(@PathVariable String carteId, Model model) {
        Optional<Carte> carte = carteService.getCarteById(carteId);
        if (carte.isPresent()) {
            model.addAttribute("carteDto", convertToDto(carte.get()));
            // Ajoutez d'autres attributs nécessaires à votre vue
            return "detailCarte"; // Nom de la page HTML pour les détails de la carte
        } else {
            // Carte non trouvée, rediriger vers la liste ou afficher un message d'erreur
            return "redirect:/cartes/list";
        }
    }

    @GetMapping("/{carteId}/edit")
    public String showUpdateForm(@PathVariable String carteId, Model model) {
        Optional<Carte> carteOptional = carteService.getCarteById(carteId);

        if (carteOptional.isPresent()) {
            Carte carte = carteOptional.get();
            model.addAttribute("carteDto", carte);
            return "editCarte";
        } else {
            // Gérer le cas où la carte avec l'ID donné n'est pas trouvée
            return "error";
        }
    }

    @PostMapping("/{carteId}/edit")
    public String updateCarte(@PathVariable("carteId") String carteId, CarteDto carteDto, Principal principal) {
        // Obtenir l'utilisateur actuellement connecté
        String username = principal.getName();
        AppUser appUser = userService.loadUserByUsername(username);

        // Obtenir l'agent correspondant à l'utilisateur
        Agent agent = agentService.findAgentByUserId(appUser.getId());

        // Convertir le DTO en entité Carte
        Carte carte = carteDto.toEntity1();

        // Définir l'agent pour la carte
        carte.setAgent(agent);

        // Mise à jour de la date de création
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        carte.setDate(now.format(formatter));

        // Mettre à jour la carte dans la base de données
        carteService.updateCarte(carteId, carte);

        // Rediriger vers la page de détails de la carte mise à jour
        return "redirect:/cartes/list";
    }

    @GetMapping("/list")
    public String listcarte (Model model){
        List<CustomCarte> customCarteList=carteService.getCustomCartes();
        model.addAttribute("customCarte",customCarteList);
        return "listCarte";
    }

    @GetMapping("/{carteId}/delete")
    public String deleteCarte(@PathVariable String carteId) {
        carteService.deleteCarteById(carteId);
        return "redirect:/cartes/list";
    }

    private CarteDto convertToDto(Carte carte) {
        CarteDto carteDto = new CarteDto();
        // Convertir les attributs de l'entité Carte en objet CarteDto
        return carteDto;
    }

    // Ajoutez d'autres méthodes de contrôleur si nécessaire
}