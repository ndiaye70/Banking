package Reporting.AFA.Security.repo;

import Reporting.AFA.Entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser,String> {
    public Optional<AppUser> findById(String id);
    AppUser findByUsername(String username);
    AppUser findByEmail(String email);

}
