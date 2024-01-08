package Reporting.AFA.Repository;

import Reporting.AFA.Entity.OuvertureCpteEntreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OuvertureCpteEntrepriseRepository extends JpaRepository<OuvertureCpteEntreprise, String> {
}

