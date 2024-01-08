package Reporting.AFA.controller;

import Reporting.AFA.dto.OperationsBancairesDto;
import Reporting.AFA.Entity.OperationsBancaires;
import Reporting.AFA.services.OperationsBancairesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/operationsBancaires")
public class OperationsBancairesController {

    private final OperationsBancairesService operationsBancairesService;

    @Autowired
    public OperationsBancairesController(OperationsBancairesService operationsBancairesService) {
        this.operationsBancairesService = operationsBancairesService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveOperationsBancaires(@RequestBody OperationsBancairesDto operationsBancairesDto) {
        try {
            OperationsBancaires operationsBancaires = operationsBancairesService.saveOperationsBancaires(operationsBancairesDto);
            return ResponseEntity.ok("Opération bancaire enregistrée avec succès. ID: " + operationsBancaires.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'enregistrement de l'opération bancaire");
        }
    }

    @GetMapping("/getAll")
    public List<OperationsBancaires> getAllOperationsBancaires() {
        return operationsBancairesService.getAllOperationsBancaires();
    }

    @GetMapping("/{operationsBancairesId}")
    public ResponseEntity<OperationsBancaires> getOperationsBancairesById(@PathVariable String operationsBancairesId) {
        Optional<OperationsBancaires> operationsBancaires = operationsBancairesService.getOperationsBancairesById(operationsBancairesId);
        return operationsBancaires.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{operationsBancairesId}")
    public ResponseEntity<String> deleteOperationsBancairesById(@PathVariable String operationsBancairesId) {
        try {
            operationsBancairesService.deleteOperationsBancairesById(operationsBancairesId);
            return ResponseEntity.ok("Opération bancaire supprimée avec succès.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la suppression de l'opération bancaire");
        }
    }
}

