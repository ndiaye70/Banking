package Reporting.AFA.services;

import Reporting.AFA.Entity.Agent;
import Reporting.AFA.Entity.Grossiste;
import Reporting.AFA.dto.CustomOperationsBancaires;
import Reporting.AFA.dto.OperationsBancairesDto;
import Reporting.AFA.Entity.OperationsBancaires;
import Reporting.AFA.Repository.OperationsBancairesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OperationsBancairesService {

    private final OperationsBancairesRepository operationsBancairesRepository;

    @Autowired
    public OperationsBancairesService(OperationsBancairesRepository operationsBancairesRepository) {
        this.operationsBancairesRepository = operationsBancairesRepository;
    }

    public OperationsBancaires saveOperationsBancaires(OperationsBancairesDto operationsBancairesDto, Agent agent) {
        OperationsBancaires operationsBancaires = operationsBancairesDto.toEntity();
        operationsBancaires.setAgent(agent);
        return operationsBancairesRepository.save(operationsBancaires);
    }

    public OperationsBancaires updateOpbancaire(String OpBancaireId , OperationsBancaires operationsBancaires) {
        Optional<OperationsBancaires> existingOpbancaire=operationsBancairesRepository.findById(OpBancaireId);

        if (existingOpbancaire.isPresent()) {
            OperationsBancaires operationsBancaires1 = existingOpbancaire.get();

            // Copy the updated values to the existing entity
            operationsBancaires1.setDate(operationsBancaires.getDate());
            operationsBancaires1.setAgent(operationsBancaires.getAgent());
            operationsBancaires1.setCivilite(operationsBancaires.getCivilite());
            operationsBancaires1.setNom(operationsBancaires.getNom());
            operationsBancaires1.setPrenom(operationsBancaires.getPrenom());
            operationsBancaires1.setNumeroTelephone(operationsBancaires.getNumeroTelephone());
            operationsBancaires1.setCommissions(operationsBancaires.getCommissions());
            operationsBancaires1.setNumeroCompte(operationsBancaires.getNumeroCompte());
            operationsBancaires1.setMontant(operationsBancaires.getMontant());
            operationsBancaires1.setAutres(operationsBancaires.getAutres());
            operationsBancaires1.setStatut(operationsBancaires.getStatut());
            operationsBancaires1.setBanques(operationsBancaires.getBanques());
            operationsBancaires1.setNatureOperations(operationsBancaires.getNatureOperations());

            // Save the updated entity
            return operationsBancairesRepository.save(operationsBancaires1);
        } else {
            // Handle the case where the operations with given ID is not found
            throw new RuntimeException("Operations with ID " +OpBancaireId+ " not found");
        }
    }

    public List<CustomOperationsBancaires> getCustomOperationsBancaires() {
        List<Object[]> results = operationsBancairesRepository.getCustomOperationsBancaires();
        return results.stream().map(this::mapToCustomOperationsBancaires).collect(Collectors.toList());
    }

    private CustomOperationsBancaires mapToCustomOperationsBancaires(Object[] result) {
        CustomOperationsBancaires customOperationsBancairesResult = new CustomOperationsBancaires();
        customOperationsBancairesResult.setId((String) result[0]);
        customOperationsBancairesResult.setDate((String) result[1]);
        customOperationsBancairesResult.setAgent((String) result[2]);
        customOperationsBancairesResult.setBanques(CustomOperationsBancaires.Banques.valueOf((String) result[3]));
        customOperationsBancairesResult.setNatureOperations(CustomOperationsBancaires.NatureOperations.valueOf((String) result[4]));
        customOperationsBancairesResult.setNumeroCompte((String) result[5]);
        customOperationsBancairesResult.setNom((String) result[6]);
        customOperationsBancairesResult.setPrenom((String) result[7]);
        customOperationsBancairesResult.setNumeroTelephone((String) result[8]);
        customOperationsBancairesResult.setMontant((Double) result[9]);
        customOperationsBancairesResult.setCommissions((Double) result[10]);
        return customOperationsBancairesResult;
    }

    public List<OperationsBancaires> getAllOperationsBancaires() {
        return operationsBancairesRepository.findAll();
    }

    public Optional<OperationsBancaires> getOperationsBancairesById(String operationsBancairesId) {
        return operationsBancairesRepository.findById(operationsBancairesId);
    }

    public void deleteOperationsBancairesById(String operationsBancairesId) {
        operationsBancairesRepository.deleteById(operationsBancairesId);
    }
}

