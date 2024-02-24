package Reporting.AFA.Repository;

import Reporting.AFA.Entity.Regularisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegularisationRepository extends JpaRepository<Regularisation,String> {

}
