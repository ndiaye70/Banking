package Reporting.AFA.Repository;

import Reporting.AFA.Entity.Agence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AgenceRepository extends JpaRepository<Agence,Long> {
}
