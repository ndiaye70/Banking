package Reporting.AFA.services;

import Reporting.AFA.Entity.Agent;
import Reporting.AFA.dto.ChangeDto;
import Reporting.AFA.Entity.Change;
import Reporting.AFA.Repository.ChangeRepository;
import Reporting.AFA.dto.CustomGrossisteResult;
import Reporting.AFA.dto.Customchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ChangeService {

    private final ChangeRepository changeRepository;
    private final CourDuJourService courDuJourService;
    @Autowired
    public ChangeService(ChangeRepository changeRepository, CourDuJourService courDuJourService) {
        this.changeRepository = changeRepository;
        this.courDuJourService = courDuJourService;
    }

    public Change saveChange(ChangeDto changeDto, Agent agent) {
        Change change = changeDto.toEntity();
        change.setAgent(agent);

        double montant=change.convertirMontants(courDuJourService);
        change.setMontantRemis(montant);
        return changeRepository.save(change);

    }
    public Change updateChange(String changeId, Change change) {
        // Recherche du changement existant dans la base de données
        Optional<Change> existingChangeOptional = changeRepository.findById(changeId);

        if (existingChangeOptional.isPresent()) {
            Change existingChange = existingChangeOptional.get();

            // Copie des valeurs mises à jour vers l'entité existante
            existingChange.setPrenom(change.getPrenom());
            existingChange.setNom(change.getNom());
            existingChange.setService(change.getService());
            existingChange.setDeviseRecu(change.getDeviseRecu());
            existingChange.setDeviseRemis(change.getDeviseRemis());
            existingChange.setMontantRecu(change.getMontantRecu());

            // Conversion des montants si nécessaire
            double montantRemis = existingChange.convertirMontants(courDuJourService);
            existingChange.setMontantRemis(montantRemis);

            // Enregistrement de l'entité mise à jour dans la base de données
            return changeRepository.save(existingChange);
        } else {
            // Gérer le cas où l'entité avec l'ID donné n'est pas trouvée
            throw new RuntimeException("Change with ID " + changeId + " not found");
        }
    }

    public List<Customchange> getCustomchange() {
        List<Object[]> results = changeRepository.getCustomChange();
        return results.stream().map(this::mapToCustomChange).collect(Collectors.toList());
    }




    public Customchange mapToCustomChange(Object[] result) {
        Customchange customChange = new Customchange();
        customChange.setId((String) result[0]);
        customChange.setDate((String) result[1]);
        customChange.setAgent((String) result[2]);
        customChange.setPrenom((String) result[3]);
        customChange.setNom((String) result[4]);
        customChange.setService(Change.Service.valueOf((String) result[5]));
        customChange.setDeviseRecu(Change.Devise.valueOf((String) result[6]));
        customChange.setDeviseRemis(Change.Devise.valueOf((String) result[7]));
        customChange.setMontantRecu((Double) result[8]);
        customChange.setMontantRemis((Double) result[9]);
        return customChange;
    }


    public Optional<Change> getChangeById(String changeId) {
        return changeRepository.findById(changeId);
    }

    public void deleteChangeById(String changeId) {
        changeRepository.deleteById(changeId);
    }

    // Ajoutez les méthodes pour la mise à jour (PUT) et la suppression (DELETE) ici
}
