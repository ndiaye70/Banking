package Reporting.AFA.services;

import Reporting.AFA.dto.OperationsBancairesDto;
import Reporting.AFA.Entity.OperationsBancaires;
import Reporting.AFA.Repository.OperationsBancairesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OperationsBancairesService {

    private final OperationsBancairesRepository operationsBancairesRepository;

    @Autowired
    public OperationsBancairesService(OperationsBancairesRepository operationsBancairesRepository) {
        this.operationsBancairesRepository = operationsBancairesRepository;
    }

    public OperationsBancaires saveOperationsBancaires(OperationsBancairesDto operationsBancairesDto) {
        OperationsBancaires operationsBancaires = operationsBancairesDto.toEntity();
        return operationsBancairesRepository.save(operationsBancaires);
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

