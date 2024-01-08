package Reporting.AFA.services;
import Reporting.AFA.Repository.OuvertureCpteEntrepriseRepository;
import Reporting.AFA.dto.OuvertureCpteEntrepriseDto;
import Reporting.AFA.Entity.OuvertureCpteEntreprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OuvertureCpteEntrepriseService {

    private final OuvertureCpteEntrepriseRepository ouvertureCpteEntrepriseRepository;

    @Autowired
    public OuvertureCpteEntrepriseService(OuvertureCpteEntrepriseRepository ouvertureCpteEntrepriseRepository) {
        this.ouvertureCpteEntrepriseRepository = ouvertureCpteEntrepriseRepository;
    }

    public OuvertureCpteEntreprise saveOuvertureCpteEntreprise(OuvertureCpteEntrepriseDto ouvertureCpteEntrepriseDto) {
        OuvertureCpteEntreprise ouvertureCpteEntreprise = ouvertureCpteEntrepriseDto.convertDtoToEntity();
        return ouvertureCpteEntrepriseRepository.save(ouvertureCpteEntreprise);
    }

    public List<OuvertureCpteEntreprise> getAllOuvertureCpteEntreprises() {
        return ouvertureCpteEntrepriseRepository.findAll();
    }

    public Optional<OuvertureCpteEntreprise> getOuvertureCpteEntrepriseById(String ouvertureCpteEntrepriseId) {
        return ouvertureCpteEntrepriseRepository.findById(ouvertureCpteEntrepriseId);
    }

    public void deleteOuvertureCpteEntrepriseById(String ouvertureCpteEntrepriseId) {
        ouvertureCpteEntrepriseRepository.deleteById(ouvertureCpteEntrepriseId);
    }


}


