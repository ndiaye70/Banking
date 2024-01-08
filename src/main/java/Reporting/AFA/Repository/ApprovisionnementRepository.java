package Reporting.AFA.Repository;

import Reporting.AFA.Entity.Approvisionnement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprovisionnementRepository extends JpaRepository<Approvisionnement, String> {
    // Ajoutez des méthodes spécifiques si nécessaire
}

