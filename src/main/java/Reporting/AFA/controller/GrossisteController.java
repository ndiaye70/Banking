package Reporting.AFA.controller;

import Reporting.AFA.Entity.Agent;
import Reporting.AFA.Entity.AppUser;
import Reporting.AFA.Entity.Operations;
import Reporting.AFA.Security.Services.AccountServiceImpl;
import Reporting.AFA.dto.CustomGrossisteResult;
import Reporting.AFA.dto.CustomOperationResult;
import Reporting.AFA.dto.GrossisteDto;
import Reporting.AFA.Entity.Grossiste;
import Reporting.AFA.dto.OperationsDto;
import Reporting.AFA.services.AgentService;
import Reporting.AFA.services.GrossisteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/grossistes")
public class GrossisteController {

    private final GrossisteService grossisteService;
    private final AccountServiceImpl userService;
    private final AgentService agentService;

    @Autowired
    public GrossisteController(GrossisteService grossisteService,AccountServiceImpl userService,AgentService agentService) {
        this.grossisteService = grossisteService;
        this.agentService=agentService;
        this.userService=userService;
    }
    @GetMapping("/save")
    public String showCreateGrossisteForm(Model model){
        model.addAttribute("grossisteDto",new GrossisteDto());
        return "createGrossiste";
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveGrossiste(@ModelAttribute("grossisteDto") GrossisteDto grossisteDto,Model model ,Principal principal) {
        String username = principal.getName();

        AppUser appUser = userService.loadUserByUsername(username);

        // Utiliser l'ID de l'utilisateur pour obtenir l'agent correspondant
        Agent agent = agentService.findAgentByUserId(appUser.getId());
        try {
            // Enregistrez le grossiste dans la base de données
            Grossiste grossiste = grossisteService.saveGrossiste(grossisteDto,agent);
            return ResponseEntity.ok("Grossiste enregistré avec succès. ID: " + grossiste.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'enregistrement du grossiste");
        }
    }

    @GetMapping("/list")
    public String allgrossiste (Model model){
        List<CustomGrossisteResult> customGrossiste = grossisteService.getCustomGrossiste();
        model.addAttribute("customGrossiste", customGrossiste);
        return "listGrossiste";
    }


    @GetMapping("/{grossisteId}")
    public ResponseEntity<Grossiste> getGrossisteById(@PathVariable String grossisteId) {
        Optional<Grossiste> grossiste = grossisteService.getGrossisteById(grossisteId);
        return grossiste.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/{grossisteId}/edit")
    public String showUpdateForm(@PathVariable String grossisteId, Model model) {
        Optional<Grossiste> grossisteOptional = grossisteService.getGrossisteById(grossisteId);

        if (grossisteOptional.isPresent()) {
            Grossiste grossiste = grossisteOptional.get();
            model.addAttribute("grossisteDto", grossiste);
            return "editGrossiste";
        } else {
            // Gérer le cas où les opérations avec l'ID donné ne sont pas trouvées
            return "error";
        }
    }

    @PostMapping("/{grossisteId}/edit")
    public String updateGrossiste(@PathVariable("grossisteId") String grossisteId, GrossisteDto updatedgrossisteDto, Principal principal) {

        Grossiste grossiste=updatedgrossisteDto.toEntity();

        String username = principal.getName();

        AppUser appUser = userService.loadUserByUsername(username);

        // Utiliser l'ID de l'utilisateur pour obtenir l'agent correspondant
        Agent agent = agentService.findAgentByUserId(appUser.getId());
        grossiste.setAgent(agent);
        LocalDateTime now = LocalDateTime.now();

        // Formater la date avec suppression des fractions de seconde
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        grossiste.setDate(now.format(formatter));
        grossisteService.updateGrossiste(grossisteId, grossiste);
        return "redirect:/grossistes/list";

    }

    // Ajoutez les méthodes pour la mise à jour (PUT) et la suppression (DELETE) ici



    // Exemple de méthode pour la suppression :
     @GetMapping("/{grossisteId}/delete")
     public ResponseEntity<String> deleteGrossiste(@PathVariable String grossisteId) {
         try {
             grossisteService.deleteGrossisteById(grossisteId);
             return ResponseEntity.ok("Grossiste supprimée avec succès");
         } catch (Exception e) {
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la suppression du Grossiste");
         }
     }
    //      Implémentez la suppression ici

}