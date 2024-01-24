package Reporting.AFA.controller;

import Reporting.AFA.Entity.Agent;
import Reporting.AFA.Entity.AppUser;
import Reporting.AFA.Security.Services.AccountServiceImpl;
import Reporting.AFA.dto.CustomOperationResult;
import Reporting.AFA.dto.OperationsDto;
import Reporting.AFA.Entity.Operations;
import Reporting.AFA.services.AgentService;
import Reporting.AFA.services.OperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/operations")
public class OperationsController {

    private final OperationsService operationsService;
    private final AccountServiceImpl userService;
    private final AgentService agentService;

    @Autowired
    public OperationsController(OperationsService operationsService,AccountServiceImpl userService,AgentService agentService) {
        this.operationsService = operationsService;
        this.agentService=agentService;
        this.userService=userService;
    }
    @GetMapping("/createOperations")
    public String showCreateOperationsForm(Model model) {
        model.addAttribute("operationsDto", new OperationsDto());
        return "createOperations";
    }


    @PostMapping("/save")
    public String saveOperations(@ModelAttribute("operationsDto") OperationsDto operationsDto, Model model, Principal principal) {

        String username = principal.getName();

        AppUser appUser = userService.loadUserByUsername(username);

        // Utiliser l'ID de l'utilisateur pour obtenir l'agent correspondant
        Agent agent = agentService.findAgentByUserId(appUser.getId());
        try {
            // Enregistrez l'opération dans la base de données
            Operations operations = operationsService.saveOperations(operationsDto,agent);
            System.out.println("Saved Operation: " + operations.toString()); // Vérifiez les données après l'enregistrement
            model.addAttribute("successMessage", "Opération enregistrée avec succès. ID: " + operations.getId());
            return "redirect:/operations/list"; // Vous pouvez créer une vue "success.html" pour afficher un message de succès
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Erreur lors de l'enregistrement de l'opération");
            return "error"; // Vous pouvez créer une vue "error.html" pour afficher un message d'erreur
        }
    }


    @GetMapping("/list")
    public String customOperations(Model model) {
        List<CustomOperationResult> customOperations = operationsService.getCustomOperations();
        model.addAttribute("customOperations", customOperations);
        return "operations";
    }


    @GetMapping("/{operationsId}")
    public ResponseEntity<Operations> getOperationsById(@PathVariable String operationsId) {
        Optional<Operations> operations = operationsService.getOperationsById(operationsId);
        return operations.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }



    @GetMapping("/{operationsId}/edit")
    public String showUpdateForm(@PathVariable String operationsId, Model model) {
        Optional<Operations> operationsOptional = operationsService.getOperationsById(operationsId);

        if (operationsOptional.isPresent()) {
            Operations operations = operationsOptional.get();
            model.addAttribute("operationsDto", operations);
            return "editOperations";
        } else {
            // Gérer le cas où les opérations avec l'ID donné ne sont pas trouvées
            return "error";
        }
    }

    @PostMapping("/{operationsId}/edit")
    public String updateOperations(@PathVariable("operationsId") String operationsId, OperationsDto updatedOperationsDto, Principal principal) {

        Operations operations=updatedOperationsDto.convertDtoToEntity();

        String username = principal.getName();

        AppUser appUser = userService.loadUserByUsername(username);

        // Utiliser l'ID de l'utilisateur pour obtenir l'agent correspondant
        Agent agent = agentService.findAgentByUserId(appUser.getId());
        operations.setAgent(agent);
        LocalDateTime now = LocalDateTime.now();

        // Formater la date avec suppression des fractions de seconde
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        operations.setDate(now.format(formatter));
        operationsService.updateOperations(operationsId, operations);
        return "redirect:/operations/list";

        }



    // Exemple de méthode pour la suppression :
    @GetMapping("/{operationsId}/delete")
    public ResponseEntity<String> deleteOperations(@PathVariable String operationsId) {
        try {
            operationsService.deleteOperationsById(operationsId);
            return ResponseEntity.ok("Opération supprimée avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la suppression de l'opération");
        }
    }
    //     // Implémentez la suppression ici
    // }
}
