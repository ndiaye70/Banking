package Reporting.AFA.Repository;

import Reporting.AFA.Entity.CaisseDollars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface   CaisseDollarsRepository extends JpaRepository<CaisseDollars, Long> {
    // Ajouter des méthodes personnalisées si nécessaire
}
