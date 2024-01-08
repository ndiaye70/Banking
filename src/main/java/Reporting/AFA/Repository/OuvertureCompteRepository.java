package Reporting.AFA.Repository;

import Reporting.AFA.Entity.OuvertureCompte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OuvertureCompteRepository extends JpaRepository<OuvertureCompte, String> {
    // Ajoutez des méthodes de requête personnalisées si nécessaire
}
