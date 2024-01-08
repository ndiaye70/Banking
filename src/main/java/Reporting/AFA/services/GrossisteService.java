package Reporting.AFA.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Reporting.AFA.dto.GrossisteDto;
import Reporting.AFA.Entity.Grossiste;
import Reporting.AFA.Repository.GrossisteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GrossisteService {


    private final GrossisteRepository grossisteRepository;

    @Autowired
    public GrossisteService(GrossisteRepository grossisteRepository) {
        this.grossisteRepository = grossisteRepository;
    }

    public Grossiste saveGrossiste(GrossisteDto grossisteDto) {
        Grossiste grossiste = grossisteDto.toEntity();
        return grossisteRepository.save(grossiste);
    }

    public List<Grossiste> getAllGrossistes() {
        return grossisteRepository.findAll();
    }

    public Optional<Grossiste> getGrossisteById(String grossisteId) {
        return grossisteRepository.findById(grossisteId);
    }

    // Ajoutez les méthodes pour la mise à jour (PUT) et la suppression (DELETE) ici
}