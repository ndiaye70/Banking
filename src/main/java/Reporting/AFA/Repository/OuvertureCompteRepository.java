package Reporting.AFA.Repository;

import Reporting.AFA.Entity.OuvertureCompte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OuvertureCompteRepository extends JpaRepository<OuvertureCompte, String> {

    @Query(nativeQuery = true, value = "SELECT O.id, O.date,CONCAT(U.prenom,' ', U.nom) as Agent ,O.type_compte,O.nom,O.prenom, O.num_tel,O.adresse,O.activite,O.num_cni_passeport,O.nom_conjoint_conjointe,O.nom_prenom_mere,O.pack,O.montant_depot_initial" +
            "FROM ouverture_compte O,agent A,utilisateur U" +
            "WHERE O.id_agent =A.id and A.id_user=U.id")
    List<Object[]> getCustomCompte();
}



    // Ajoutez des méthodes de requête personnalisées si nécessair
