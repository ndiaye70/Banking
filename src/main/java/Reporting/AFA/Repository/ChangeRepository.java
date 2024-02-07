package Reporting.AFA.Repository;

import Reporting.AFA.Entity.Change;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChangeRepository extends JpaRepository<Change, String> {
    @Query(nativeQuery = true, value = "SELECT C.id, C.date, CONCAT(U.prenom, ' ', U.nom) as Agent, C.prenom, C.nom, C.service, C.devise_recu, C.devise_remis, C.montant_recu, C.montant_remis "+
    "FROM change C, utilisateur U, agent A "+
   "WHERE C.id_agent = A.id and U.id = A.id_user")

    List<Object[]> getCustomChange();
    // Ajoutez les méthodes de requête personnalisées ici si nécessaire
}