package Reporting.AFA.Repository;



import Reporting.AFA.Entity.Grossiste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GrossisteRepository extends JpaRepository<Grossiste, String> {

    @Query(nativeQuery = true, value = "SELECT G.id,CONCAT(U.prenom,' ',U.nom) as Agent,G.date,G.nom,G.prenom,G.num_tel,G.montant,G.autres,G.statut,G.frais,G.montant_paye_par,G.frais_paye_par " +
            "from grossiste G,utilisateur U,agent A " +
            "WHERE G.id_agent=A.id and A.id_user=U.id")
    List<Object[]> getCustomGrossiste();
    // Ajoutez des méthodes spécifiques si nécessaire
}