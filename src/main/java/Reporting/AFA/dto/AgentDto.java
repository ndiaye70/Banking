package Reporting.AFA.dto;

import Reporting.AFA.Entity.Agent;
import Reporting.AFA.Entity.Agence;
import Reporting.AFA.Entity.AppUser;
import lombok.Data;

@Data
public class AgentDto {

    private String agenceNom;
    private String username;

    // Constructeurs, getters et setters

    public AgentDto() {
        // Constructeur par défaut nécessaire pour Thymeleaf
    }

    public AgentDto(String agenceNom, String username) {
        this.agenceNom = agenceNom;
        this.username = username;
    }

    public Agent convertDtoToEntity(Agence agence, AppUser user) {
        Agent agent = new Agent();
        agent.setAgence(agence);
        agent.setUser(user);
        return agent;
    }
}
