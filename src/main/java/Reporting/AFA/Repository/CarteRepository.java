package Reporting.AFA.Repository;

import Reporting.AFA.Entity.Carte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface CarteRepository extends JpaRepository<Carte, String> {

    @Query(nativeQuery = true, value ="SELECT c.id, c.date, CONCAT(U.prenom, '   ',U.nom) as agent, c.nom_complet, c.tel, c.service, c.montant" +
            " FROM Carte c, utilisateur U , agent A" +
            " WHERE c.id_agent=A.id and U.id=A.id_user")
    List<Object[]> getCustomCarte();

    // Ajoutez d'autres méthodes de requête personnalisées si nécessaire
}

