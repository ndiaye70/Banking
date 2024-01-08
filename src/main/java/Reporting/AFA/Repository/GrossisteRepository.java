package Reporting.AFA.Repository;



import Reporting.AFA.Entity.Grossiste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GrossisteRepository extends JpaRepository<Grossiste, String> {
    // Ajoutez des méthodes spécifiques si nécessaire
}