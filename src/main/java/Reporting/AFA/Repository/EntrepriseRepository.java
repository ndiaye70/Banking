package Reporting.AFA.Repository;


import Reporting.AFA.Entity.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EntrepriseRepository extends JpaRepository<Entreprise, String> {

    @Query(nativeQuery = true, value = "SELECT E.date_creation, CONCAT(U.prenom, ' ', U.nom) as agent, E.nom, E.prenom, E.num_tel, E.cni, E.type_entreprise, E.nom_entreprise, E.demande, E.montant,E.id " +
            "FROM entreprise E, agent A, utilisateur U " +
            "WHERE E.id_agent = A.id AND A.id_user = U.id")
    List<Object[]> getCustomEntreprises();
}
    // Ajoutez des méthodes personnalisées du repository si nécessaire

