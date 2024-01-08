package Reporting.AFA.Repository;

import Reporting.AFA.Entity.OperationsBancaires;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationsBancairesRepository extends JpaRepository<OperationsBancaires, String> {
}

