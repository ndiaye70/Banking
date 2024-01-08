package Reporting.AFA.Repository;

import Reporting.AFA.Entity.Euro;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface EuroRepository extends JpaRepository<Euro,Long> {
}
