package Reporting.AFA.controller;

import Reporting.AFA.Entity.Commissions;
import Reporting.AFA.Entity.SoldeCaisse;
import Reporting.AFA.Entity.SoldeDepart;
import Reporting.AFA.Repository.CommissionsRepository;
import Reporting.AFA.Repository.CustomRepository;
import Reporting.AFA.Repository.SoldeCaisseRepository;
import Reporting.AFA.Repository.SoldeDepartRepository;
import Reporting.AFA.dto.CommissionsDto;
import Reporting.AFA.dto.SoldeCaisseDto;
import Reporting.AFA.dto.SoldeDepartDto;
import Reporting.AFA.services.CommissionsService;
import Reporting.AFA.services.SoldeCaisseService;
import Reporting.AFA.services.SoldeDepartService;
import ch.qos.logback.core.joran.sanity.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/recaps")
public class RecapController {

    @Autowired
    private SoldeCaisseRepository soldeCaisseRepository;

    @Autowired
    private final SoldeCaisseService soldeCaisseService;

    @Autowired
    private CustomRepository customRepository;
    @Autowired
    private  final SoldeDepartRepository soldeDepartRepository;

    @Autowired
    private  final CommissionsRepository commissionsRepository;

    @Autowired
    private final SoldeDepartService soldeDepartService;
    @Autowired
    private final CommissionsService commissionsService;

    public RecapController(SoldeCaisseService soldeCaisseService, SoldeDepartRepository soldeDepartRepository, CommissionsRepository commissionsRepository, SoldeDepartService soldeDepartService, CommissionsService commissionsService) {
        this.soldeCaisseService = soldeCaisseService;
        this.soldeDepartRepository = soldeDepartRepository;
        this.commissionsRepository = commissionsRepository;
        this.soldeDepartService = soldeDepartService;
        this.commissionsService = commissionsService;
    }

    @GetMapping("/list")
    public String getRecaps(Model model) {
        List<Map<String, Object>> recaps = customRepository.getRecaps();
        model.addAttribute("recaps", recaps);


        double totalSoldeDepart = recaps.stream().mapToDouble(recap -> (double) recap.get("SoldeDepart")).sum();
        double totalMontantDepot = recaps.stream().mapToDouble(recap -> (double) recap.get("montantDepot")).sum();
        double totalMontantRetrait = recaps.stream().mapToDouble(recap -> (double) recap.get("montantRetrait")).sum();
        double totalCommissions = recaps.stream().mapToDouble(recap -> (double) recap.get("Commissions")).sum();
        double totalApproEntrant = recaps.stream().mapToDouble(recap -> (double) recap.get("ApproEntrant")).sum();
        double totalApproSortant = recaps.stream().mapToDouble(recap -> (double) recap.get("ApproSortant")).sum();
        //double totalNombreClients = recaps.stream().mapToDouble(recap -> (double) recap.get("nombreClients")).sum();
        long totalNombreClients = recaps.stream().mapToInt(recap -> Math.toIntExact((long) recap.get("nombreClients"))).sum();


        double totalLigne = IntStream.range(0, 3) // Boucle sur les indices de 0 à 2 (pour les trois premières entrées)
                .mapToDouble(i -> {
                    Map<String, Object> recap = recaps.get(i); // Récupère l'élément à l'indice i
                    return (double) recap.get("SoldeDepart") +
                            (double) recap.get("montantDepot") +
                            (double) recap.get("montantRetrait") +
                            (double) recap.get("Commissions") +
                            (double) recap.get("ApproEntrant") +
                            (double) recap.get("ApproSortant");
                })
                .sum();


        Map<String, Object> recap = recaps.get(8); // Récupère le premier élément de la liste


        double totalLigne2 = (double) recap.get("SoldeDepart") +
                (double) recap.get("montantDepot") +
                (double) recap.get("montantRetrait") +
                (double) recap.get("Commissions") +
                (double) recap.get("ApproEntrant") +
                (double) recap.get("ApproSortant");



        // Ajout des sommes au modèle
        model.addAttribute("totalSoldeDepart", totalSoldeDepart);
        model.addAttribute("totalMontantDepot", totalMontantDepot);
        model.addAttribute("totalMontantRetrait", totalMontantRetrait);
        model.addAttribute("totalCommissions",totalCommissions);
        model.addAttribute("totalApproEntrant", totalApproEntrant);
        model.addAttribute("totalApproSortant", totalApproSortant);
        model.addAttribute("totalNombreClients", totalNombreClients);
        model.addAttribute("totalLigne", totalLigne+totalLigne2); // Ajout de la somme des cellules dans la colonne "Total_ligne"

        return "recaps";}


    @GetMapping("/soldes")
    public String getMontants(Model model) {
        List<Map<String, Object>> recaps = customRepository.getRecaps();

        double totalLigne1 = IntStream.range(0, 3) // Boucle sur les indices de 0 à 2 (pour les trois premières entrées)
                .mapToDouble(i -> {
                    Map<String, Object> recap = recaps.get(i); // Récupère l'élément à l'indice i
                    return (double) recap.get("SoldeDepart") +
                            (double) recap.get("montantDepot") +
                            (double) recap.get("montantRetrait") +
                            (double) recap.get("Commissions") +
                            (double) recap.get("ApproEntrant") +
                            (double) recap.get("ApproSortant");
                })
                .sum();


        Map<String, Object> recap = recaps.get(8); // Récupère le premier élément de la liste


        double totalLigne2 = (double) recap.get("SoldeDepart") +
                (double) recap.get("montantDepot") +
                (double) recap.get("montantRetrait") +
                (double) recap.get("Commissions") +
                (double) recap.get("ApproEntrant") +
                (double) recap.get("ApproSortant");




        List<Double> montants = soldeCaisseRepository.getMontants();
        model.addAttribute("montants", montants);
        Long id= Long.valueOf(soldeCaisseRepository.getID());
        Optional<SoldeCaisse> soldeCaisse = soldeCaisseService.getSoldeCaisseById(id);
        if (soldeCaisse.isPresent()) {
            SoldeCaisse solde = soldeCaisse.get();
            model.addAttribute("id",id);
            model.addAttribute("ouvertureCaisseXof", solde.getOuvertureCaisseXof());
            model.addAttribute("fermetureCaisseXof", solde.getFermetureCaisseXof());
            model.addAttribute("ouvertureCaisseEur", solde.getOuvertureCaisseEur());
            model.addAttribute("fermetureCaisseEur", solde.getFermetureCaisseEur());
            model.addAttribute("ouvertureCaisseUsd", solde.getOuvertureCaisseUsd());
            model.addAttribute("fermetureCaisseUsd", solde.getFermetureCaisseUsd());
            Double totalLigne=totalLigne1+totalLigne2;
            Double totalSolde = solde.getFermetureCaisseXof() + totalLigne;
            model.addAttribute("totalSolde",totalSolde);
        }
        return "SoldeTable"; // Nom de la vue Thymeleaf pour afficher le tableau de montants
    }









    @GetMapping("/save")
    public String showCreateSoldeDepartForm(Model model) {
        model.addAttribute("soldeDepartDto", new SoldeDepartDto());
        return "CreateSoldeDepart";
    }

    @PostMapping("/save")
    public String saveSoldeDepart(@ModelAttribute SoldeDepartDto soldeDepartDto, Model model) {
        try {
            // Enregistrer le solde de départ dans la base de données
            soldeDepartService.saveSoldeDepart(soldeDepartDto);
            model.addAttribute("succes", "Enregistrement des soldes de départ");
            return "redirect:/recaps/list" ;
        } catch (Exception e) {
            // Gérer les erreurs et afficher un message approprié
            model.addAttribute("error", "Erreur lors de l'enregistrement du solde de départ");
            return "CreateSoldeDepart";
        }
    }

    @GetMapping("edit")
    public String showUpdateForm(Model model) {
        String soldeDepartId=soldeDepartRepository.getID();
        Optional<SoldeDepart> soldeDepartOptional = soldeDepartService.getSoldeDepartById(soldeDepartId);

        if (soldeDepartOptional.isPresent()) {
            SoldeDepart soldeDepart = soldeDepartOptional.get();
            model.addAttribute("soldeDepartDto", soldeDepart);
            return "editSoldeDepart";
        } else {
            // Gérer le cas où le solde de départ avec l'ID donné n'est pas trouvé
            return "error";
        }
    }

    @PostMapping("/{soldeDepartId}/edit")
    public String updateSoldeDepart(@PathVariable("soldeDepartId") String soldeDepartId, SoldeDepartDto soldeDepartDto) {

        SoldeDepart soldeDepart = soldeDepartDto.toEntity();

        // Mettre à jour l'entité solde de départ avec l'identifiant correspondant
        soldeDepart.setId(soldeDepartId);

        // Enregistrer les modifications dans la base de données
        soldeDepartService.updateSoldeDepart(soldeDepartId, soldeDepart);

        return "redirect:/recaps/list" ;
    }

    @GetMapping("/create")
    public String showCreateCommissionsForm(Model model) {
        model.addAttribute("commissionsDto", new CommissionsDto());
        return "CreateCommissions";
    }

    @PostMapping("/save2")
    public String saveCommissions(@ModelAttribute CommissionsDto commissionsDto, Model model) {
        try {
            // Enregistrer les commissions dans la base de données
            commissionsService.saveCommissions(commissionsDto);
            model.addAttribute("success", "Enregistrement des commissions réussi");
            return "redirect:/recaps/list";
        } catch (Exception e) {
            // Gérer les erreurs et afficher un message approprié
            model.addAttribute("error", "Erreur lors de l'enregistrement des commissions");
            return "CreateCommissions";
        }
    }

    @GetMapping("/commissions/edit")
    public String showUpdateForms( Model model) {
        String commissionsId=commissionsRepository.getID();
        Optional<Commissions> commissionsOptional = commissionsService.getCommissionsById(commissionsId);

        if (commissionsOptional.isPresent()) {
            Commissions commissions = commissionsOptional.get();
            model.addAttribute("commissionsDto", commissions);
            return "EditCommissions";
        } else {
            // Gérer le cas où les commissions avec l'ID donné ne sont pas trouvées
            return "error";
        }
    }

    @PostMapping("/commissions/{commissionsId}/edit")
    public String updateCommissions(@PathVariable("commissionsId") String commissionsId, @ModelAttribute CommissionsDto commissionsDto, Model model) {
        Commissions commissions=commissionsDto.toEntity();
        try {
            // Mettre à jour les commissions avec l'ID correspondant
            commissionsService.updateCommissions(commissionsId, commissions);
            model.addAttribute("success", "Mise à jour des commissions réussie");
            return "redirect:/recaps/list";
        } catch (Exception e) {
            // Gérer les erreurs et afficher un message approprié
            model.addAttribute("error", "Erreur lors de la mise à jour des commissions");
            return "EditCommissions";
        }
    }


}
