package Reporting.AFA.services;

import Reporting.AFA.Entity.Agent;
import Reporting.AFA.Repository.AgentRepository;
import Reporting.AFA.Repository.OperationsRepository;
import Reporting.AFA.dto.CustomOperationResult;
import Reporting.AFA.dto.OperationsDto;
import Reporting.AFA.Entity.Operations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OperationsService {

    private final OperationsRepository operationsRepository;
    private final AgentRepository agentRepository;
    @Autowired
    public OperationsService(OperationsRepository operationsRepository,AgentRepository agentRepository) {
        this.operationsRepository = operationsRepository;
        this.agentRepository=agentRepository;
    }


    public Operations saveOperations(OperationsDto operationsDto, Agent agent) {
        Operations operations = operationsDto.convertDtoToEntity();
        operations.setAgent(agent);
        System.out.println("Data after conversion: " + operations.toString()); // Vérifiez les données avant de les sauvegarder
        return operationsRepository.save(operations);
    }


    public List<Operations> getAllOperations() {
        return operationsRepository.findAll();
    }

    public Optional<Operations> getOperationsById(String operationsId) {
        return operationsRepository.findById(operationsId);
    }
    public Operations updateOperations(String operationsId, Operations operations) {
        Optional<Operations> existingOperationsOptional = operationsRepository.findById(operationsId);

        if (existingOperationsOptional.isPresent()) {
            Operations existingOperations = existingOperationsOptional.get();

            // Copy the updated values to the existing entity
            existingOperations.setDate(operations.getDate());
            existingOperations.setNatureOperation(operations.getNatureOperation());
            existingOperations.setCivilite(operations.getCivilite());
            existingOperations.setService(operations.getService());
            existingOperations.setNom(operations.getNom());
            existingOperations.setPrenom(operations.getPrenom());
            existingOperations.setNumeroTelephone(operations.getNumeroTelephone());
            existingOperations.setMontant(operations.getMontant());
            existingOperations.setAutres(operations.getAutres());
            existingOperations.setStatut(operations.getStatut());

            // Save the updated entity
            return operationsRepository.save(existingOperations);
        } else {
            // Handle the case where the operations with given ID is not found
            throw new RuntimeException("Operations with ID " + operationsId + " not found");
        }
    }

    // Dans OperationsService


    public List<CustomOperationResult> getCustomOperations() {
        List<Object[]> results = operationsRepository.getCustomOperations();
        return results.stream().map(this::mapToCustomOperationResult).collect(Collectors.toList());
    }

    private CustomOperationResult mapToCustomOperationResult(Object[] result) {
        CustomOperationResult customOperationResult = new CustomOperationResult();
        customOperationResult.setId((String) result[0]);
        customOperationResult.setCaissier((String) result[1]);
        customOperationResult.setService((String) result[2]);
        customOperationResult.setNatureOperation((String) result[3]);
        customOperationResult.setNom((String) result[4]);
        customOperationResult.setPrenom((String) result[5]);
        customOperationResult.setNumeroTelephone((String) result[6]);
        customOperationResult.setMontant((Double) result[7]);
        customOperationResult.setAutres((String) result[8]);
        customOperationResult.setStatut((String) result[9]);
        return customOperationResult;
    }






    public void deleteOperationsById(String operationsId) {
        operationsRepository.deleteById(operationsId);
    }
}
