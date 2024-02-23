package Reporting.AFA.Repository;

import Reporting.AFA.Entity.OuvertureCpteEntreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OuvertureCpteEntrepriseRepository extends JpaRepository<OuvertureCpteEntreprise, String> {
    @Query(nativeQuery = true,value = "SELECT O.id, CONCAT(U.prenom, ' ', U.nom) as caissier, O.type_compte, O.forme_juridique, O.rccm, O.ninea, O.denomination_social, O.pack, O.montant_depot_initial, O.nom, O.prenom, O.fonctions, O.numcni_passeport, O.adresse, O.date, O.num_tel " +
            "FROM ouverture_cpte_entreprise O, agent I, utilisateur U " +
            "WHERE O.id_agent = I.id and U.id = I.id_user")
    List<Object[]> getCustomCpteEntreprise();
}

