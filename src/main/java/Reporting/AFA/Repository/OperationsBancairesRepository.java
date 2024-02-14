package Reporting.AFA.Repository;

import Reporting.AFA.Entity.OperationsBancaires;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationsBancairesRepository extends JpaRepository<OperationsBancaires, String> {

    @Query(value = "SELECT O.id, O.date, CONCAT(U.prenom, ' ', U.nom) as agent, O.banques, " +
            "O.nature_operations, O.numero_compte, O.nom, O.prenom, " +
            "O.numero_telephone, O.montant, O.commissions " +
            "FROM operations_bancaires O, agent I, utilisateur U " +
            "WHERE O.id_agent = I.id and U.id = I.id_user", nativeQuery = true)
    List<Object[]> getCustomOperationsBancaires();
}
