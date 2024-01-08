package Reporting.AFA.Security.Services;

import Reporting.AFA.Entity.AppRole;
import Reporting.AFA.Entity.AppUser;

public interface AccountService {
    AppUser addNewUser(String username,String Prenom,String Nom,String email,String password,String confirmPassword);
    AppRole addNewRole(String role);
    void addRoleToUser(String username,String role);
    void removeRoleFromUser(String username,String role);
    AppUser loadUserByUsername(String username);
}
