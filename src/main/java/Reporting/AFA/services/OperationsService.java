package Reporting.AFA.services;

import Reporting.AFA.Repository.OperationsRepository;
import Reporting.AFA.dto.OperationsDto;
import Reporting.AFA.Entity.Operations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OperationsService {

    private final OperationsRepository operationsRepository;

    @Autowired
    public OperationsService(OperationsRepository operationsRepository) {
        this.operationsRepository = operationsRepository;
    }


    public Operations saveOperations(OperationsDto operationsDto) {
        Operations operations = operationsDto.convertDtoToEntity();
        System.out.println("Data after conversion: " + operations.toString()); // Vérifiez les données avant de les sauvegarder
        return operationsRepository.save(operations);
    }


    public List<Operations> getAllOperations() {
        return operationsRepository.findAll();
    }

    public Optional<Operations> getOperationsById(String operationsId) {
        return operationsRepository.findById(operationsId);
    }




    public void deleteOperationsById(String operationsId) {
        operationsRepository.deleteById(operationsId);
    }
}
