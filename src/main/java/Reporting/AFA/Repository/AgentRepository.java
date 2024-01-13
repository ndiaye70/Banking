package Reporting.AFA.Repository;

import Reporting.AFA.Entity.Agence;
import Reporting.AFA.Entity.Agent;
import Reporting.AFA.Entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepository extends JpaRepository<Agent,Long> {

    public Agent findByUserId(String id_User);

    Agent findAgentByUserId(String id_User);

    Agent findByUser(AppUser appUser);

    Agent findByAgence(Agence agence);



}
