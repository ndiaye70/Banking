package Reporting.AFA.Repository;

import Reporting.AFA.Entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminRepository extends JpaRepository<AppUser,String> {
    @Query(nativeQuery = true, value ="select distinct prenom,nom,email,username" +
            " from utilisateur A,utilisateur_roles B" +
            " where A.id=B.app_user_id and B.roles_role='ADMIN'")
    List<Object[]> getAdmin();

    @Query(nativeQuery = true, value ="select distinct username from utilisateur" +
            " where id not in " +
            "(select distinct id from utilisateur A,utilisateur_roles B where A.id=B.app_user_id and B.roles_role in ('ADMIN'))")

    List getUser();

}
