package Reporting.AFA.controller;

import Reporting.AFA.Entity.*;
import Reporting.AFA.Security.Services.AccountServiceImpl;
import Reporting.AFA.dto.CustomEntrepriseResult;
import Reporting.AFA.dto.EntrepriseDto;
import Reporting.AFA.dto.OperationsDto;
import Reporting.AFA.services.AgentService;
import Reporting.AFA.services.EntrepriseService;
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
@RequestMapping("/entreprises")
public class EntrepriseController {

    private final EntrepriseService entrepriseService;
    private final AccountServiceImpl userService;
    private final AgentService agentService;


    @Autowired
    public EntrepriseController(EntrepriseService entrepriseService, AccountServiceImpl userService, AgentService agentService) {
        this.entrepriseService = entrepriseService;
        this.agentService = agentService;
        this.userService = userService;
    }

    @GetMapping("/save")
    public String showCreateEntrepriseForm(Model model) {
        model.addAttribute("entrepriseDto", new EntrepriseDto());
        return "CreateEntreprise";
    }

    @PostMapping("/save")
    public String saveEntreprise(@ModelAttribute EntrepriseDto entrepriseDto, Principal principal, Model model) {
        String username = principal.getName();

        AppUser appUser = userService.loadUserByUsername(username);

        // Utiliser l'ID de l'utilisateur pour obtenir l'agent correspondant
        Agent agent = agentService.findAgentByUserId(appUser.getId());
        try {
            // Enregistrez l'entreprise dans la base de données
            Entreprise entreprise = entrepriseService.saveEntreprise(entrepriseDto, agent);

            // Rediriger vers la page de détails de l'entreprise nouvellement créée
            return "redirect:/entreprises/" + entreprise.getId() + "/details";
        } catch (Exception e) {
            // Gérer les erreurs et afficher un message approprié
            model.addAttribute("error", "Erreur lors de l'enregistrement de l'entreprise");
            return "CreateEntreprise"; // Vous pouvez également rediriger vers la page de création avec un message d'erreur
        }
    }

    @GetMapping("/{entrepriseId}/details")
    public String getEntrepriseDetails(@PathVariable String entrepriseId, Model model) {
        Optional<Entreprise> entreprise = entrepriseService.getEntrepriseById(entrepriseId);
        if (entreprise.isPresent()) {
            model.addAttribute("entrepriseDto", convertToDto(entreprise.get()));
            Double Montant = entreprise.get().getMontant();
            Agent agent = entreprise.get().getAgent();
            String date = entreprise.get().getDateCreation();
            Agence agence = agent.getAgence();
            AppUser appUser = agent.getUser();
            String nom = appUser.getNom();
            String prenom = appUser.getPrenom();
            String nomComplet = prenom.concat(" ").concat(nom);
            model.addAttribute("date", date);
            model.addAttribute("agence", agence);
            model.addAttribute("nomComplet", nomComplet);
            model.addAttribute("Montant", Montant);
            return "detailEntreprise"; // Nom de la page HTML pour les détails de l'entreprise
        } else {
            // Entreprise non trouvée, rediriger vers la liste ou afficher un message d'erreur
            return "redirect:/entreprises/list";
        }
    }

    private EntrepriseDto convertToDto(Entreprise entreprise) {
        EntrepriseDto entrepriseDto = new EntrepriseDto();
        entrepriseDto.setNom(entreprise.getNom());
        entrepriseDto.setPrenom(entreprise.getPrenom());
        entrepriseDto.setNumTel(entreprise.getNumTel());
        entrepriseDto.setCni(entreprise.getCni());
        entrepriseDto.setTypeEntreprise(entreprise.getTypeEntreprise());
        entrepriseDto.setNom_Entreprise(entreprise.getNom_Entreprise());
        entrepriseDto.setDemande(entreprise.getDemande());
        // Autres propriétés à définir en fonction de votre structure EntrepriseDto

        return entrepriseDto;
    }

    @GetMapping("/JS/pdf-script.js")
    public String getJavaScript() {
        return "JS/pdf-script";
    }


    @GetMapping("/list")
    public String getCustomEntreprises(Model model) {
        List<CustomEntrepriseResult> customEntreprises = entrepriseService.getCustomEntreprises();
        model.addAttribute("listEntreprise", customEntreprises);
        return "listEntreprise";
    }

    @GetMapping("/{entrepriseId}")
    public ResponseEntity<Entreprise> getEntrepriseById(@PathVariable String entrepriseId) {
        Optional<Entreprise> entreprise = entrepriseService.getEntrepriseById(entrepriseId);
        return entreprise.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{entrepriseId}/delete")
    public String deleteEntrepriseById(@PathVariable String entrepriseId, Model model) {
        try {
            // Supprimez l'entreprise de la base de données
            entrepriseService.deleteEntrepriseById(entrepriseId);
            model.addAttribute("succes", "Quittance supprimée avec succés");
            return "redirect:/entreprises/list";
        } catch (Exception e) {
            model.addAttribute("erreur", "erreur lors de la suppression du quittance");
            return "redirect:/entreprises/list";
        }
    }
    @GetMapping("/{entrepriseId}/edit")
    public String showUpdateForm(@PathVariable String entrepriseId, Model model) {
        Optional<Entreprise> entrepriseOptional = entrepriseService.getEntrepriseById(entrepriseId);

        if (entrepriseOptional.isPresent()) {
            Entreprise entreprise = entrepriseOptional.get();
            model.addAttribute("entrepriseDto", entreprise);
            return "editEntreprise";
        } else {
            // Gérer le cas où les opérations avec l'ID donné ne sont pas trouvées
            return "error";
        }
    }

    @PostMapping("/{entrepriseId}/edit")
    public String updateOperations(@PathVariable("entrepriseId") String entrepriseId, EntrepriseDto entrepriseDto, Principal principal) {

        Entreprise entreprise=entrepriseDto.toEntity();

        String username = principal.getName();

        AppUser appUser = userService.loadUserByUsername(username);

        // Utiliser l'ID de l'utilisateur pour obtenir l'agent correspondant
        Agent agent = agentService.findAgentByUserId(appUser.getId());
        entreprise.setAgent(agent);
        LocalDateTime now = LocalDateTime.now();

        // Formater la date avec suppression des fractions de seconde
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        entreprise.setDateCreation(now.format(formatter));
        entrepriseService.updateEntreprise(entrepriseId, entreprise);
        return "redirect:/entreprises/" + entreprise.getId() + "/details";

    }
}

