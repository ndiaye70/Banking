package Reporting.AFA.Repository;

import Reporting.AFA.Entity.Change;
import Reporting.AFA.Entity.CourDuJour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourDuJourRepository extends JpaRepository<CourDuJour, Long> {
    // Ajoutez les méthodes de requête personnalisées ici si nécessaire
}
