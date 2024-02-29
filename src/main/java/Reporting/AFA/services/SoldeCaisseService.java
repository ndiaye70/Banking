package Reporting.AFA.services;

import Reporting.AFA.Entity.SoldeCaisse;
import Reporting.AFA.Repository.SoldeCaisseRepository;
import Reporting.AFA.dto.SoldeCaisseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SoldeCaisseService {

    private final SoldeCaisseRepository soldeCaisseRepository;

    @Autowired
    public SoldeCaisseService(SoldeCaisseRepository soldeCaisseRepository) {
        this.soldeCaisseRepository = soldeCaisseRepository;
    }

    public SoldeCaisse saveSoldeCaisse(SoldeCaisseDto soldeCaisseDto) {
        SoldeCaisse soldeCaisse = soldeCaisseDto.toEntity();
        return soldeCaisseRepository.save(soldeCaisse);
    }

    public List<SoldeCaisse> getAllSoldeCaisses() {
        return soldeCaisseRepository.findAll();
    }

    public Optional<SoldeCaisse> getSoldeCaisseById(long soldeCaisseId) {
        return soldeCaisseRepository.findById(soldeCaisseId);
    }

    public void deleteSoldeCaisseById(long soldeCaisseId) {
        soldeCaisseRepository.deleteById(soldeCaisseId);
    }

    public SoldeCaisse updateSoldeCaisse(long soldeCaisseId, SoldeCaisse soldeCaisse) {
        Optional<SoldeCaisse> existingSoldeCaisseOptional = soldeCaisseRepository.findById(soldeCaisseId);

        if (existingSoldeCaisseOptional.isPresent()) {
            SoldeCaisse existingSoldeCaisse = existingSoldeCaisseOptional.get();

            // Copy the updated values to the existing entity;
            existingSoldeCaisse.setOuvertureCaisseXof(soldeCaisse.getOuvertureCaisseXof());
            existingSoldeCaisse.setFermetureCaisseXof(soldeCaisse.getFermetureCaisseXof());
            existingSoldeCaisse.setOuvertureCaisseEur(soldeCaisse.getOuvertureCaisseEur());
            existingSoldeCaisse.setFermetureCaisseEur(soldeCaisse.getFermetureCaisseEur());
            existingSoldeCaisse.setOuvertureCaisseUsd(soldeCaisse.getOuvertureCaisseUsd());
            existingSoldeCaisse.setFermetureCaisseUsd(soldeCaisse.getFermetureCaisseUsd());

            // Save the updated entity
            return soldeCaisseRepository.save(existingSoldeCaisse);
        } else {
            // Handle the case where the entity with given ID is not found
            throw new RuntimeException("SoldeCaisse with ID " + soldeCaisseId + " not found");
        }
    }

    // Add other custom CRUD methods if needed
}
