package Reporting.AFA.services;

import Reporting.AFA.Entity.Agent;
import Reporting.AFA.dto.ChangeDto;
import Reporting.AFA.Entity.Change;
import Reporting.AFA.Repository.ChangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChangeService {

    private final ChangeRepository changeRepository;

    @Autowired
    public ChangeService(ChangeRepository changeRepository) {
        this.changeRepository = changeRepository;
    }

    public Change saveChange(ChangeDto changeDto, Agent agent) {
        Change change = changeDto.toEntity();
        change.setAgent(agent);
        return changeRepository.save(change);
    }

    public List<Change> getAllChanges() {
        return changeRepository.findAll();
    }

    public Optional<Change> getChangeById(String changeId) {
        return changeRepository.findById(changeId);
    }

    public void deleteChangeById(String changeId) {
        changeRepository.deleteById(changeId);
    }

    // Ajoutez les méthodes pour la mise à jour (PUT) et la suppression (DELETE) ici
}
