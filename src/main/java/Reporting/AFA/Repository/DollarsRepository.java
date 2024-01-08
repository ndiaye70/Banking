package Reporting.AFA.Repository;

import Reporting.AFA.Entity.Dollars;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface DollarsRepository extends JpaRepository<Dollars,Long> {
}
