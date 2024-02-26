package Reporting.AFA.services;

import Reporting.AFA.Entity.Commissions;
import Reporting.AFA.Repository.CommissionsRepository;
import Reporting.AFA.dto.CommissionsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CommissionsService {

    private final CommissionsRepository commissionsRepository;

    @Autowired
    public CommissionsService(CommissionsRepository commissionsRepository) {
        this.commissionsRepository = commissionsRepository;
    }

    public Commissions saveCommissions(CommissionsDto commissionsDto) {
        Commissions commissions=commissionsDto.toEntity();
        return commissionsRepository.save(commissions);
    }



    public Optional<Commissions> getCommissionsById(String commissionsId) {
        return commissionsRepository.findById(commissionsId);
    }

    public void deleteCommissionsById(String commissionsId) {
        commissionsRepository.deleteById(commissionsId);
    }

    public Commissions updateCommissions(String commissionsId, Commissions updatedCommissions) {
        Optional<Commissions> existingCommissionsOptional = commissionsRepository.findById(commissionsId);

        if (existingCommissionsOptional.isPresent()) {
            Commissions existingCommissions = existingCommissionsOptional.get();
            existingCommissions.setDate(updatedCommissions.getDate());
            existingCommissions.setCommissionsOM(updatedCommissions.getCommissionsOM());
            existingCommissions.setCommissionsFreeMoney(updatedCommissions.getCommissionsFreeMoney());
            existingCommissions.setCommissionsWave(updatedCommissions.getCommissionsWave());
            existingCommissions.setCommissionsWizall(updatedCommissions.getCommissionsWizall());
            existingCommissions.setCommissionsWesternUnion(updatedCommissions.getCommissionsWesternUnion());
            existingCommissions.setCommissionsRia(updatedCommissions.getCommissionsRia());
            existingCommissions.setCommissionsMoneygram(updatedCommissions.getCommissionsMoneygram());
            existingCommissions.setCommissionsEcobank(updatedCommissions.getCommissionsEcobank());
            existingCommissions.setCommissionsSmallWorld(updatedCommissions.getCommissionsSmallWorld());
            existingCommissions.setCommissionsBnB(updatedCommissions.getCommissionsBnB());
            existingCommissions.setCommissionsAskia(updatedCommissions.getCommissionsAskia());
            existingCommissions.setCommissionsSunuAssurance(updatedCommissions.getCommissionsSunuAssurance());
            existingCommissions.setCommissionsCreationEntreprise(updatedCommissions.getCommissionsCreationEntreprise());
            existingCommissions.setCommissionsCartesComImpExp(updatedCommissions.getCommissionsCartesComImpExp());
            existingCommissions.setCommissionsChange(updatedCommissions.getCommissionsChange());
            existingCommissions.setCommissionsGrossisteBnb(updatedCommissions.getCommissionsGrossisteBnb());
            existingCommissions.setCommissionsBndeDepotRetrait(updatedCommissions.getCommissionsBndeDepotRetrait());
            existingCommissions.setCommissionsOuvertureCompteBnde(updatedCommissions.getCommissionsOuvertureCompteBnde());
            existingCommissions.setCommissionsUbaCartesPrepayees(updatedCommissions.getCommissionsUbaCartesPrepayees());
            existingCommissions.setCommissionsUbaDepotRetrait(updatedCommissions.getCommissionsUbaDepotRetrait());

            return commissionsRepository.save(existingCommissions);
        } else {
            throw new RuntimeException("Commissions with ID " + commissionsId + " not found");
        }
    }

}
