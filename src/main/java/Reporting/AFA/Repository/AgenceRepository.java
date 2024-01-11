package Reporting.AFA.Repository;
import Reporting.AFA.Entity.Agence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository

public interface AgenceRepository extends JpaRepository<Agence,Long> {


    Agence findByNomAgence(String nomAgence);
}