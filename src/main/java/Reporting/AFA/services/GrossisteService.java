package Reporting.AFA.services;

import Reporting.AFA.Entity.Agent;
import Reporting.AFA.Entity.Operations;
import Reporting.AFA.dto.CustomGrossisteResult;
import Reporting.AFA.dto.CustomOperationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Reporting.AFA.dto.GrossisteDto;
import Reporting.AFA.Entity.Grossiste;
import Reporting.AFA.Repository.GrossisteRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GrossisteService {


    private final GrossisteRepository grossisteRepository;

    @Autowired
    public GrossisteService(GrossisteRepository grossisteRepository) {
        this.grossisteRepository = grossisteRepository;
    }

    public Grossiste saveGrossiste(GrossisteDto grossisteDto, Agent agent) {
        Grossiste grossiste = grossisteDto.toEntity();
        grossiste.setAgent(agent);
        return grossisteRepository.save(grossiste);
    }

    public Grossiste updateGrossiste(String grossisteId, Grossiste grossiste) {
        Optional<Grossiste> existingGrossisteOptional = grossisteRepository.findById(grossisteId);

        if (existingGrossisteOptional.isPresent()) {
            Grossiste existingGrossiste = existingGrossisteOptional.get();

            // Copy the updated values to the existing entity
            existingGrossiste.setDate(grossiste.getDate());
            existingGrossiste.setAgent(grossiste.getAgent());
            existingGrossiste.setCivilite(grossiste.getCivilite());
            existingGrossiste.setMontantPayePar(grossiste.getMontantPayePar());
            existingGrossiste.setFraisPayePar(grossiste.getFraisPayePar());
            existingGrossiste.setFrais(grossiste.getFrais());
            existingGrossiste.setNom(grossiste.getNom());
            existingGrossiste.setPrenom(grossiste.getPrenom());
            existingGrossiste.setNumTel(grossiste.getNumTel());
            existingGrossiste.setMontant(grossiste.getMontant());
            existingGrossiste.setAutres(grossiste.getAutres());
            existingGrossiste.setStatut(grossiste.getStatut());

            // Save the updated entity
            return grossisteRepository.save(existingGrossiste);
        } else {
            // Handle the case where the operations with given ID is not found
            throw new RuntimeException("Operations with ID " +grossisteId + " not found");
        }
    }


    public List<CustomGrossisteResult> getCustomGrossiste() {
        List<Object[]> results = grossisteRepository.getCustomGrossiste();
        return results.stream().map(this::mapToCustomGrossisteResult).collect(Collectors.toList());
    }

    private CustomGrossisteResult mapToCustomGrossisteResult(Object[] result) {
        CustomGrossisteResult customGrossisteResult=new CustomGrossisteResult();
        customGrossisteResult.setId((String) result[0]);
        customGrossisteResult.setCaissier((String) result[1]);
        customGrossisteResult.setDate((String) result[2]);
        customGrossisteResult.setNom((String) result[3]);
        customGrossisteResult.setPrenom((String) result[4]);
        customGrossisteResult.setNumTel((String) result[5]);
        customGrossisteResult.setMontant((Double) result[6]);
        customGrossisteResult.setAutres((String) result[7]);
        customGrossisteResult.setStatut((String) result[8]);
        customGrossisteResult.setFrais((Double) result[9]);
        customGrossisteResult.setMontantPayépar(CustomGrossisteResult.MontantPayePar.valueOf((String) result[10]));
        customGrossisteResult.setFraisPayépar(CustomGrossisteResult.FraisPayepar.valueOf((String) result[11]));
        return customGrossisteResult;
    }




    public List<Grossiste> getAllGrossistes() {
        return grossisteRepository.findAll();
    }

    public Optional<Grossiste> getGrossisteById(String grossisteId) {
        return grossisteRepository.findById(grossisteId);
    }

    public void deleteGrossisteById(String grossisteId) {
        grossisteRepository.deleteById(grossisteId);
    }

    // Ajoutez les méthodes pour la mise à jour (PUT) et la suppression (DELETE) ici
}