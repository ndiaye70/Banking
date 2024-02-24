package Reporting.AFA.services;

import Reporting.AFA.Repository.RegularisationRepository;
import Reporting.AFA.dto.RegularisationDto;
import Reporting.AFA.Entity.Regularisation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegularisationService {

    private final RegularisationRepository regularisationRepository;

    @Autowired
    public RegularisationService(RegularisationRepository regularisationRepository) {
        this.regularisationRepository = regularisationRepository;
    }

    public Regularisation saveRegularisation(RegularisationDto regularisationDTO) {
        Regularisation regularisation = regularisationDTO.toEntity();

        return regularisationRepository.save(regularisation);
    }

    public List<Regularisation> getAllRegularisations() {
        return regularisationRepository.findAll();
    }

    public Regularisation updateRegularisation(String id, Regularisation regularisation1) {
        Optional<Regularisation> optionalRegularisation = regularisationRepository.findById(id);
        if (optionalRegularisation.isPresent()) {
            Regularisation regularisation = optionalRegularisation.get();
            regularisation.setDate(regularisation1.getDate());
            regularisation.setLibelle(regularisation1.getLibelle());
            regularisation.setMontant(regularisation1.getMontant());
            regularisation.setCompteCaisse(regularisation1.getCompteCaisse());
            regularisation.setRegularisateur(regularisation1.getRegularisateur());
            return regularisationRepository.save(regularisation);
        } else {
            // Handle the case where the operations with given ID is not found
            throw new RuntimeException("Operations with ID " + id + " not found");

        }
    }


    public void deleteRegularisation(String id) {
        regularisationRepository.deleteById(id);
    }

    public Optional<Regularisation> getRegularisationById(String id) {
        return  regularisationRepository.findById(id);
    }
}


