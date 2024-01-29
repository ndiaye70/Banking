package Reporting.AFA.Repository;

import Reporting.AFA.Entity.Approvisionnement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApprovisionnementRepository extends JpaRepository<Approvisionnement, String> {
    @Query(nativeQuery = true, value = "SELECT A.id, A.date, CONCAT(U.prenom,' ',U.nom) as agent, A.montant, A.type, A.origine, " +
            "A.compte_caisse1, A.destination, A.compte_caisse2, A.devise, A.statut " +
            "FROM Approvisionnement A, Utilisateur U, Agent G " +
            "WHERE A.id_agent = G.id and U.id = G.id_user")

    List<Object[]> getCustomApprovisionnement();
    // Ajoutez des méthodes spécifiques si nécessaire
}

