package Reporting.AFA.controller;

import Reporting.AFA.dto.OperationsDto;
import Reporting.AFA.Entity.Operations;
import Reporting.AFA.services.OperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/operations")
public class OperationsController {

    private final OperationsService operationsService;

    @Autowired
    public OperationsController(OperationsService operationsService) {
        this.operationsService = operationsService;
    }


    @PostMapping("/save")
    public String saveOperations(@ModelAttribute("operationsDto") OperationsDto operationsDto, Model model) {
        try {
            // Enregistrez l'opération dans la base de données
            Operations operations = operationsService.saveOperations(operationsDto);
            System.out.println("Saved Operation: " + operations.toString()); // Vérifiez les données après l'enregistrement
            model.addAttribute("successMessage", "Opération enregistrée avec succès. ID: " + operations.getId());
            return "redirect:/operations/index"; // Vous pouvez créer une vue "success.html" pour afficher un message de succès
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Erreur lors de l'enregistrement de l'opération");
            return "error"; // Vous pouvez créer une vue "error.html" pour afficher un message d'erreur
        }
    }

    @GetMapping("/index")
    public String operations(Model model) {
        List<Operations> operations = operationsService.getAllOperations();
        model.addAttribute("listOperations", operations);
        System.out.println("Number of operations: " + operations.size());
        for (Operations operation : operations) {
            System.out.println("Operation: " + operation.toString());
        }
        return "operations";
    }

    @GetMapping("/createOperations")
    public String showCreateOperationsForm(Model model) {
        model.addAttribute("operationsDto", new OperationsDto());
        return "createOperations";
    }

    @GetMapping("/{operationsId}")
    public ResponseEntity<Operations> getOperationsById(@PathVariable String operationsId) {
        Optional<Operations> operations = operationsService.getOperationsById(operationsId);
        return operations.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Ajoutez les méthodes pour la mise à jour (PUT) et la suppression (DELETE) ici

    // Exemple de méthode pour la mise à jour :
    // @PutMapping("/{operationsId}")
    // public ResponseEntity<String> updateOperations(@PathVariable String operationsId, @RequestBody OperationsDto operationsDto) {
    //     // Implémentez la mise à jour ici
    // }

    // Exemple de méthode pour la suppression :
    @DeleteMapping("/{operationsId}")
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
