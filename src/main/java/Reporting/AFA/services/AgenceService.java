package Reporting.AFA.services;

import Reporting.AFA.Entity.Agence;
import Reporting.AFA.Repository.AgenceRepository;
import Reporting.AFA.dto.AgenceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgenceService {

    private final AgenceRepository agenceRepository; // Assurez-vous de créer cette interface et son implémentation

    @Autowired
    public AgenceService(AgenceRepository agenceRepository) {
        this.agenceRepository = agenceRepository;
    }

    public Agence createAgence(AgenceDto agenceDto) {
        Agence agence = agenceDto.convertDtoToEntity();
        Agence savedAgence = agenceRepository.save(agence);

        return savedAgence;
    }

    public List<Agence> getAllAgences() {
        List<Agence> allagences = agenceRepository.findAll();
        return allagences;


    }


}
