package Reporting.AFA.Repository;

import Reporting.AFA.Entity.CaisseXOF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface   CaisseXOFRepository extends JpaRepository<CaisseXOF, Long> {
    // Ajouter des méthodes personnalisées si nécessaire
}
