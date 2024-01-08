package Reporting.AFA.services;

import Reporting.AFA.Repository.EntrepriseRepository;
import Reporting.AFA.dto.EntrepriseDto;
import Reporting.AFA.Entity.Entreprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class EntrepriseService {

    private final EntrepriseRepository entrepriseRepository;

    @Autowired
    public EntrepriseService(EntrepriseRepository entrepriseRepository) {
        this.entrepriseRepository = entrepriseRepository;
    }

    public Entreprise saveEntreprise(EntrepriseDto entrepriseDto) {
        Entreprise entreprise = entrepriseDto.toEntity();
        return entrepriseRepository.save(entreprise);
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

    // Ajoutez d'autres méthodes CRUD personnalisées si nécessaire
}


