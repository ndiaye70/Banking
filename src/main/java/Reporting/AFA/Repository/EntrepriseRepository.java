package Reporting.AFA.Repository;


import Reporting.AFA.Entity.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrepriseRepository extends JpaRepository<Entreprise, String> {
    // Ajoutez des méthodes personnalisées du repository si nécessaire
}
