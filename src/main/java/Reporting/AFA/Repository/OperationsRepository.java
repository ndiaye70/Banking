package Reporting.AFA.Repository;

import Reporting.AFA.Entity.Operations;
import Reporting.AFA.dto.CustomOperationResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationsRepository extends JpaRepository<Operations, String> {


        @Query(value = "SELECT O.id, CONCAT(U.prenom ,' ', U.nom) as caissier, O.service, O.nature_operation, O.nom, O.prenom, O.numero_telephone, O.montant, O.date, O.statut " +
                "FROM operations O, agent A, utilisateur U " +
                "WHERE O.id_agent = A.id AND A.id_user = U.id", nativeQuery = true)
        List<Object[]> getCustomOperations();
    }



    // Ajoutez des méthodes personnalisées du repository si nécessaire

