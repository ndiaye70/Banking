package Reporting.AFA.controller;

import Reporting.AFA.Entity.Agence;
import Reporting.AFA.Entity.Agent;
import Reporting.AFA.Entity.AppUser;
import Reporting.AFA.Security.Services.AccountServiceImpl;
import Reporting.AFA.dto.ChangeDto;
import Reporting.AFA.Entity.Change;
import Reporting.AFA.dto.Customchange;
import Reporting.AFA.services.AgentService;
import Reporting.AFA.services.ChangeService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/changes")
public class ChangeController {
    @Autowired
    private final ChangeService changeService;

    @Autowired
    private final AccountServiceImpl userService;

    @Autowired
    private final AgentService agentService;


    @Autowired
    public ChangeController(ChangeService changeService, AccountServiceImpl userService, AgentService agentService) {
        this.changeService = changeService;
        this.agentService = agentService;
        this.userService = userService;
    }

    @GetMapping("/save")
    public String showChangeForm(Model model) {
        model.addAttribute("changeDto", new ChangeDto());
        return "createChange";
    }


    @PostMapping("/save")
    public String saveChange(@ModelAttribute("changeDto") @Valid ChangeDto changeDto, Principal principal, BindingResult result) {
        if (result.hasErrors()) {
            return "index";
        }


        String username = principal.getName();


        AppUser appUser = userService.loadUserByUsername(username);

        // Utiliser l'ID de l'utilisateur pour obtenir l'agent correspondant
        Agent agent = agentService.findAgentByUserId(appUser.getId());
        try {
            // Enregistrez le changement dans la base de données
            Change change =changeService.saveChange(changeDto, agent);
            return "redirect:/changes/"+change.getId()+"/details" ;
        } catch (Exception e) {
            return "redirect:/changes/save";
        }
    }

    @GetMapping("/{changeId}/details")
    public String getChangeDetails(@PathVariable String changeId, Model model) {
        Optional<Change> change = changeService.getChangeById(changeId);
        if (change.isPresent()) {
            model.addAttribute("changeDto", convertToDto(change.get()));

            Double Montant = change.get().getMontantRemis();
            model.addAttribute("MontantRemis", Montant);
            Agent agent = change.get().getAgent();
            String date = change.get().getDate();
            Agence agence = agent.getAgence();
            AppUser appUser = agent.getUser();
            String nom = appUser.getNom();
            String prenom = appUser.getPrenom();
            String nomComplet = prenom.concat(" ").concat(nom);
            model.addAttribute("date", date);
            model.addAttribute("agence", agence);
            model.addAttribute("nomComplet", nomComplet);

            return "detailChange";
        } else {

            return "redirect:/changes/list";
        }
    }


    private ChangeDto convertToDto(Change change) {
        ChangeDto changeDto = new ChangeDto();
        changeDto.setPrenom(change.getPrenom());
        changeDto.setNom(change.getNom());
        changeDto.setDeviseRecu(String.valueOf(change.getDeviseRecu()));
        changeDto.setDeviseRemis(String.valueOf(change.getDeviseRemis()));
        changeDto.setService(change.getService());
        changeDto.setMontantRecu(change.getMontantRecu());
        changeDto.setCNI(change.getCNI());
        changeDto.setResident(String.valueOf(change.getResident()));
        changeDto.setTypePieceIdentite(change.getTypePieceIdentite());
        changeDto.setTelephone(change.getTelephone());
        changeDto.setNumero_piece_identité(change.getNumero_piece_identité());
        changeDto.setPasseport(change.getPasseport());
        changeDto.setDate_delivrance(change.getDate_delivrance());

        return changeDto;
    }


    @GetMapping("/list")
    public String allchange(Model model) {
        List<Customchange> customchangeList = changeService.getCustomchange();
        model.addAttribute("customchangeList", customchangeList);
        return "listChange";
    }

    @GetMapping("/{changeId}/edit")
    public String showUpdateForm(@PathVariable String changeId, Model model) {
        Optional<Change> changeOptional = changeService.getChangeById(changeId);

        if (changeOptional.isPresent()) {
            Change change = changeOptional.get();
            model.addAttribute("changeDto", change);
            return "editChange";
        } else {
            // Gérer le cas où les opérations avec l'ID donné ne sont pas trouvées
            return "error";
        }
    }

    @PostMapping("/{changeId}/edit")
    public String updateChange(@PathVariable("changeId") String changeId, ChangeDto updatedChangeDto, Principal principal) {

        Change change = updatedChangeDto.toEntity();

        String username = principal.getName();
        AppUser appUser = userService.loadUserByUsername(username);

        // Utiliser l'ID de l'utilisateur pour obtenir l'agent correspondant
        Agent agent = agentService.findAgentByUserId(appUser.getId());
        change.setAgent(agent);

        // Mettre à jour la date
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        change.setDate(now.format(formatter));

        // Mettre à jour l'objet Change
        changeService.updateChange(changeId, change);

        return "redirect:/changes/"+changeId+"/details" ;
    }


    @GetMapping("/{changeId}/delete")
    public String deletechange(@PathVariable String changeId, RedirectAttributes redirectAttributes) {
        try {
            changeService.deleteChangeById(changeId);
            redirectAttributes.addFlashAttribute("successMessage", "Change supprimée avec succès");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erreur lors de la suppression du change");
        }
        return "redirect:/changes/list";
    }


    @GetMapping("/{changeId}")
    public String deleteChange(@PathVariable String changeId) {
        changeService.deleteChangeById(changeId);
        return "redirect/changes/list";
        // Implémentez la suppression ici

    }
}