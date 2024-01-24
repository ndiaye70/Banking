package Reporting.AFA.services;

import Reporting.AFA.Entity.Agent;
import Reporting.AFA.Repository.EntrepriseRepository;
import Reporting.AFA.dto.CustomEntrepriseResult;
import Reporting.AFA.dto.EntrepriseDto;
import Reporting.AFA.Entity.Entreprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EntrepriseService {

    private final EntrepriseRepository entrepriseRepository;

    @Autowired
    public EntrepriseService(EntrepriseRepository entrepriseRepository) {
        this.entrepriseRepository = entrepriseRepository;
    }

    public Entreprise saveEntreprise(EntrepriseDto entrepriseDto, Agent agent) {

        Entreprise entreprise = entrepriseDto.toEntity();
        Double Montant=entreprise.getDemande().getMontant();
        entreprise.setMontant(Montant);
        entreprise.setAgent(agent);
        return entrepriseRepository.save(entreprise);
    }




    public List<CustomEntrepriseResult> getCustomEntreprises() {
        List<Object[]> results= entrepriseRepository.getCustomEntreprises();

        return results.stream().map(this::mapToCustomEntrepriseResult).collect(Collectors.toList());
    }

    private CustomEntrepriseResult mapToCustomEntrepriseResult(Object[] result) {
        CustomEntrepriseResult customEntrepriseResult = new CustomEntrepriseResult();
        customEntrepriseResult.setDateCreation((String) result[0]);
        customEntrepriseResult.setAgent((String) result[1]);
        customEntrepriseResult.setNom((String) result[2]);
        customEntrepriseResult.setPrenom((String) result[3]);
        customEntrepriseResult.setNumTel((String) result[4]);
        customEntrepriseResult.setCni((String) result[5]);
        customEntrepriseResult.setTypeEntreprise(CustomEntrepriseResult.TypeEntreprise.valueOf((String) result[6]));
        customEntrepriseResult.setNom_Entreprise((String) result[7]);
        customEntrepriseResult.setDemande(CustomEntrepriseResult.Demande.valueOf((String) result[8]));
        customEntrepriseResult.setMontant((Double) result[9]);
        customEntrepriseResult.setId((String) result[10]);
        return customEntrepriseResult;
    }



    public List<Entreprise> getAllEntreprises() {
        return entrepriseRepository.findAll();
    }

    public Optional<Entreprise> getEntrepriseById(String entrepriseId) {
        return entrepriseRepository.findById(entrepriseId);
    }

    public void deleteEntrepriseById(String entrepriseId) {
        entrepriseRepository.deleteById(entrepriseId);
    }

    public Entreprise updateEntreprise(String entrepriseId, Entreprise entreprise) {
        Optional<Entreprise> existingEntrepriseOptional = entrepriseRepository.findById(entrepriseId);

        if (existingEntrepriseOptional.isPresent()) {
            Entreprise existingEntreprise = existingEntrepriseOptional.get();

            // Copy the updated values to the existing entity
            existingEntreprise.setDateCreation(entreprise.getDateCreation());
            existingEntreprise.setDemande(entreprise.getDemande());
            existingEntreprise.setAgent(entreprise.getAgent());
            existingEntreprise.setCni(entreprise.getCni());
            existingEntreprise.setNom(entreprise.getNom());
            existingEntreprise.setPrenom(entreprise.getPrenom());
            existingEntreprise.setNumTel(entreprise.getNumTel());
            existingEntreprise.setNom_Entreprise(entreprise.getNom_Entreprise());
            existingEntreprise.setTypeEntreprise(entreprise.getTypeEntreprise());
            existingEntreprise.setMontant(entreprise.getMontant());

            // Save the updated entity
            return entrepriseRepository.save(existingEntreprise);
        } else {
            // Handle the case where the operations with given ID is not found
            throw new RuntimeException("Operations with ID " + entrepriseId + " not found");
        }
    }

    // Ajoutez d'autres méthodes CRUD personnalisées si nécessaire
}


