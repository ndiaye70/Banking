package Reporting.AFA.Repository;

import Reporting.AFA.Entity.Change;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChangeRepository extends JpaRepository<Change, String> {
    // Ajoutez les méthodes de requête personnalisées ici si nécessaire
}