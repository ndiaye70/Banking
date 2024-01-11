package Reporting.AFA.services;

import Reporting.AFA.Entity.Agence;
import Reporting.AFA.Entity.Agent;
import Reporting.AFA.Entity.AppUser;
import Reporting.AFA.dto.AgentDto;
import Reporting.AFA.Repository.AgenceRepository;
import Reporting.AFA.Repository.AgentRepository;
import Reporting.AFA.Security.repo.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgentService {

    private final AgentRepository agentRepository;
    private final AgenceRepository agenceRepository;
    private final UserRepository appUserRepository;

    @Autowired
    public AgentService(AgentRepository agentRepository, AgenceRepository agenceRepository, UserRepository appUserRepository) {
        this.agentRepository = agentRepository;
        this.agenceRepository = agenceRepository;
        this.appUserRepository = appUserRepository;
    }

    @Transactional
    public Agent createAgent(AgentDto agentDto) {
        // Récupérer l'Agence depuis le nom
        Agence agence = agenceRepository.findByNomAgence(agentDto.getAgenceNom());

        // Récupérer l'AppUser depuis le username
        AppUser user = appUserRepository.findByUsername(agentDto.getUsername());

        // Vérifier si l'Agence et l'AppUser existent
        if (agence == null || user == null) {
            // Gérer le cas où l'Agence ou l'AppUser n'existe pas
            throw new EntityNotFoundException("L'Agence ou l'AppUser n'existe pas.");
        }

        // Créer l'Agent en utilisant la méthode convertDtoToEntity
        Agent agent = agentDto.convertDtoToEntity(agence, user);

        // Enregistrer l'Agent dans la base de données
        return agentRepository.save(agent);
    }


    public void deleteAgent(Long agentId) {
        agentRepository.deleteById(agentId);
    }


    public void assignAgentToAnotherAgence(Long agentId, String agenceNom) {
        Agent agent = agentRepository.findById(agentId).orElseThrow(() -> new RuntimeException("Agent not found"));

        // Rechercher l'agence par le nom
        Agence newAgence = agenceRepository.findByNomAgence(agenceNom);

        if (newAgence == null) {
            throw new RuntimeException("Agence not found with name: " + agenceNom);
        }

        agent.setAgence(newAgence);

        agentRepository.save(agent);
    }

    public List<Agent> getAllAgents() {
        return agentRepository.findAll();
    }
}
