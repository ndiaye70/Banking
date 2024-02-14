package Reporting.AFA.controller;

import Reporting.AFA.Entity.Agent;
import Reporting.AFA.Entity.AppUser;
import Reporting.AFA.Entity.Grossiste;
import Reporting.AFA.Security.Services.AccountServiceImpl;
import Reporting.AFA.dto.CustomOperationsBancaires;
import Reporting.AFA.dto.GrossisteDto;
import Reporting.AFA.dto.OperationsBancairesDto;
import Reporting.AFA.Entity.OperationsBancaires;import Reporting.AFA.dto.OperationsDto;
import Reporting.AFA.services.AgentService;
import Reporting.AFA.services.OperationsBancairesService;
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
@RequestMapping("/operationsBancaires")
public class OperationsBancairesController {

    private final OperationsBancairesService operationsBancairesService;
    private final AccountServiceImpl userService;
    private final AgentService agentService;


    @Autowired
    public OperationsBancairesController(OperationsBancairesService operationsBancairesService,AccountServiceImpl userService,AgentService agentService) {
        this.operationsBancairesService = operationsBancairesService;
        this.agentService=agentService;
        this.userService=userService;
    }

    @GetMapping("/save")
    public String showCreateOperationsForm(Model model) {
        model.addAttribute("operationsDto", new OperationsBancairesDto());
        return "CreateOperationsBancaire";
    }

    @PostMapping("/save")
    public String saveOperationsBancaires(@ModelAttribute("operationsDto") OperationsBancairesDto operationsDto, Principal principal,Model model) {

        String username = principal.getName();

        AppUser appUser = userService.loadUserByUsername(username);

        // Utiliser l'ID de l'utilisateur pour obtenir l'agent correspondant
        Agent agent = agentService.findAgentByUserId(appUser.getId());
        OperationsBancaires operationsBancaires = operationsBancairesService.saveOperationsBancaires(operationsDto, agent);
        model.addAttribute("successMessage", "Opération enregistrée avec succès. ID: " + operationsBancaires.getId());
        return "redirect:/operationsBancaires/list";
    }




    @GetMapping("/{operationsBancairesId}/edit")
    public String showUpdateForm(@PathVariable String operationsBancairesId, Model model) {
        Optional<OperationsBancaires> operationsBancairesOptional=operationsBancairesService.getOperationsBancairesById(operationsBancairesId);

        if (operationsBancairesOptional.isPresent()) {
            OperationsBancaires operationsBancaires=operationsBancairesOptional.get();
            model.addAttribute("operationsBancairesDto", operationsBancaires);
            return "editOpBancaires";
        } else {
            // Gérer le cas où les opérations avec l'ID donné ne sont pas trouvées
            return "error";
        }
    }


    @PostMapping("/{operationsBancairesId}/edit")
    public String updateOPbancaire(@PathVariable("operationsBancairesId") String operationsBancairesId, OperationsBancairesDto operationsBancairesDto, Principal principal) {

        OperationsBancaires operationsBancaires=operationsBancairesDto.toEntity();

        String username = principal.getName();

        AppUser appUser = userService.loadUserByUsername(username);

        // Utiliser l'ID de l'utilisateur pour obtenir l'agent correspondant
        Agent agent = agentService.findAgentByUserId(appUser.getId());
        operationsBancaires.setAgent(agent);
        LocalDateTime now = LocalDateTime.now();

        // Formater la date avec suppression des fractions de seconde
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        operationsBancaires.setDate(now.format(formatter));
        operationsBancairesService.updateOpbancaire(operationsBancairesId,operationsBancaires);
        return "redirect:/operationsBancaires/list";

    }
    @GetMapping("/list")
    public String allOperationsBancaires(Model model){
        List<CustomOperationsBancaires> customOperationsBancaires = operationsBancairesService.getCustomOperationsBancaires();
        model.addAttribute("customOperationsBancaires", customOperationsBancaires);
        return "listOperationsBancaires"; // Assurez-vous que c'est le bon nom de vue
    }

    @GetMapping("/{operationsBancairesId}")
    public ResponseEntity<OperationsBancaires> getOperationsBancairesById(@PathVariable String operationsBancairesId) {
        Optional<OperationsBancaires> operationsBancaires = operationsBancairesService.getOperationsBancairesById(operationsBancairesId);
        return operationsBancaires.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{operationsBancairesId}/delete")
    public String deleteOpBancaire(@PathVariable String operationsBancairesId){
        operationsBancairesService.deleteOperationsBancairesById(operationsBancairesId);
        return "redirect:/operationsBancaires/list";
    }



}

