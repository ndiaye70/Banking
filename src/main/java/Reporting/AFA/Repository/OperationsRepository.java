package Reporting.AFA.Repository;

import Reporting.AFA.Entity.Operations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationsRepository extends JpaRepository<Operations, String> {
    // Ajoutez des méthodes personnalisées du repository si nécessaire
}
