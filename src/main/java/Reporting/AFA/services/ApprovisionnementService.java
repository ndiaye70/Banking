package Reporting.AFA.services;

import Reporting.AFA.Entity.Agent;
import Reporting.AFA.Repository.ApprovisionnementRepository;
import Reporting.AFA.dto.ApprovisionnementDto;
import Reporting.AFA.Entity.Approvisionnement;
import Reporting.AFA.dto.CustomApprovisionnement;
import Reporting.AFA.dto.CustomGrossisteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApprovisionnementService {

    private final ApprovisionnementRepository approvisionnementRepository;

    @Autowired
    public ApprovisionnementService(ApprovisionnementRepository approvisionnementRepository) {
        this.approvisionnementRepository = approvisionnementRepository;
    }

    public Approvisionnement saveApprovisionnement(ApprovisionnementDto approvisionnementDto, Agent agent) {
        Approvisionnement approvisionnement = approvisionnementDto.toEntity();
        approvisionnement.setAgent(agent);
        return approvisionnementRepository.save(approvisionnement);
    }


    public Approvisionnement updateapprovisionnement(String approvisionnementId, Approvisionnement approvisionnement) {
        Optional<Approvisionnement> optionalApprovisionnement = approvisionnementRepository.findById(approvisionnementId);

        if (optionalApprovisionnement.isPresent()) {

            Approvisionnement approvisionnement1 = optionalApprovisionnement.get();

            approvisionnement1.setDate(approvisionnement.getDate());
            approvisionnement1.setAgent(approvisionnement.getAgent());
            approvisionnement1.setMontant(approvisionnement.getMontant());
            approvisionnement1.setStatut(approvisionnement.getStatut());
            approvisionnement1.setType(approvisionnement.getType());
            approvisionnement1.setOrigine(approvisionnement.getOrigine());
            approvisionnement1.setDevise(approvisionnement.getDevise());
            approvisionnement1.setDestination(approvisionnement.getDestination());
            approvisionnement1.setCompteCaisse1(approvisionnement.getCompteCaisse1());

            approvisionnement1.setCompteCaisse2(approvisionnement.getCompteCaisse2());

            return  approvisionnementRepository.save(approvisionnement1);

        } else {
            // Handle the case where the operations with given ID is not found
            throw new RuntimeException("Approvisionnement avec l'ID " +approvisionnementId + " non trouvé");
        }


        }

    public List<CustomApprovisionnement> getCustomApprovisionnement() {
        List<Object[]> results = approvisionnementRepository.getCustomApprovisionnement();
        return results.stream().map(this::mapToCustomApprovisionnement).collect(Collectors.toList());
    }

    private CustomApprovisionnement mapToCustomApprovisionnement(Object[] result) {
        CustomApprovisionnement customApprovisionnement = new CustomApprovisionnement();
        customApprovisionnement.setId((String) result[0]);
        customApprovisionnement.setDate((String) result[1]);
        customApprovisionnement.setAgent((String) result[2]);
        customApprovisionnement.setMontant((Double) result[3]);
        customApprovisionnement.setType(CustomApprovisionnement.TYPE.valueOf((String) result[4]));
        customApprovisionnement.setOrigine(CustomApprovisionnement.Origine.valueOf((String) result[5]));
        customApprovisionnement.setCompteCaisse1(CustomApprovisionnement.Compte.valueOf ((String) result[6]));
        customApprovisionnement.setDestination(CustomApprovisionnement.Origine.valueOf ((String) result[7]));
        customApprovisionnement.setCompteCaisse2(CustomApprovisionnement.Compte.valueOf ((String) result[8]));
        customApprovisionnement.setDevise(CustomApprovisionnement.Devise.valueOf ((String) result[9]));
        customApprovisionnement.setStatut((String) result[10]);
        return customApprovisionnement;
    }



    public Optional<Approvisionnement> getApprovisionnementById(String approvisionnementId ){
            return approvisionnementRepository.findById(approvisionnementId);
        }
        public void deleteapprovisionnement(String approvisionnementId){
        approvisionnementRepository.deleteById(approvisionnementId);

        }

        // Ajoutez d'autres méthodes de service au besoin

}
