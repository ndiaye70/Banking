package Reporting.AFA.services;

import Reporting.AFA.Entity.SoldeDepart;
import Reporting.AFA.Repository.SoldeDepartRepository;
import Reporting.AFA.dto.SoldeDepartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SoldeDepartService {

    private final SoldeDepartRepository soldeDepartRepository;

    @Autowired
    public SoldeDepartService(SoldeDepartRepository soldeDepartRepository) {
        this.soldeDepartRepository = soldeDepartRepository;
    }

    public SoldeDepart saveSoldeDepart(SoldeDepartDto soldeDepartDto) {
        SoldeDepart soldeDepart = soldeDepartDto.toEntity();
        return soldeDepartRepository.save(soldeDepart);
    }

    public List<SoldeDepart> getAllSoldeDeparts() {
        return soldeDepartRepository.findAll();
    }

    public Optional<SoldeDepart> getSoldeDepartById(String soldeDepartId) {
        return soldeDepartRepository.findById(soldeDepartId);
    }

    public void deleteSoldeDepartById(String soldeDepartId) {
        soldeDepartRepository.deleteById(soldeDepartId);
    }

    public SoldeDepart updateSoldeDepart(String soldeDepartId, SoldeDepart soldeDepart) {
        Optional<SoldeDepart> existingSoldeDepartOptional = soldeDepartRepository.findById(soldeDepartId);

        if (existingSoldeDepartOptional.isPresent()) {
            SoldeDepart existingSoldeDepart = existingSoldeDepartOptional.get();

            // Copy the updated values to the existing entity
            existingSoldeDepart.setDate(soldeDepart.getDate());
            existingSoldeDepart.setOrangeMoney(soldeDepart.getOrangeMoney());
            existingSoldeDepart.setFreeMoney(soldeDepart.getFreeMoney());
            existingSoldeDepart.setWave(soldeDepart.getWave());
            existingSoldeDepart.setWizall(soldeDepart.getWizall());
            existingSoldeDepart.setWesternUnion(soldeDepart.getWesternUnion());
            existingSoldeDepart.setRia(soldeDepart.getRia());
            existingSoldeDepart.setMoneygram(soldeDepart.getMoneygram());
            existingSoldeDepart.setEcobank(soldeDepart.getEcobank());
            existingSoldeDepart.setSmallWorld(soldeDepart.getSmallWorld());
            existingSoldeDepart.setBnb(soldeDepart.getBnb());
            existingSoldeDepart.setAskia(soldeDepart.getAskia());
            existingSoldeDepart.setSunuAssurance(soldeDepart.getSunuAssurance());
            existingSoldeDepart.setCreationEntreprise(soldeDepart.getCreationEntreprise());
            existingSoldeDepart.setCartesComImpExp(soldeDepart.getCartesComImpExp());
            existingSoldeDepart.setChange(soldeDepart.getChange());
            existingSoldeDepart.setGrossisteBnb(soldeDepart.getGrossisteBnb());
            existingSoldeDepart.setBndeDepotRetrait(soldeDepart.getBndeDepotRetrait());
            existingSoldeDepart.setOuvertureCompteBnde(soldeDepart.getOuvertureCompteBnde());
            existingSoldeDepart.setUbaCartesPrepayees(soldeDepart.getUbaCartesPrepayees());
            existingSoldeDepart.setUbaDepotRetrait(soldeDepart.getUbaDepotRetrait());

            // Save the updated entity
            return soldeDepartRepository.save(existingSoldeDepart);
        } else {
            // Handle the case where the operations with given ID is not found
            throw new RuntimeException("SoldeDepart with ID " + soldeDepartId + " not found");
        }
    }

    // Ajoutez d'autres méthodes CRUD personnalisées si nécessaire
}
