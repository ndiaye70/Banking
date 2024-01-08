package Reporting.AFA.Repository;

import Reporting.AFA.Entity.Caisse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface   CaisseRepository extends JpaRepository<Caisse, Long> {
    // Ajouter des méthodes personnalisées si nécessaire
}
