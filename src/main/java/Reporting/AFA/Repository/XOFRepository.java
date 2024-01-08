package Reporting.AFA.Repository;

import Reporting.AFA.Entity.XOF;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface XOFRepository extends JpaRepository<XOF,Long> {
}
