package Reporting.AFA.Security.Services;

import Reporting.AFA.Entity.AppRole;
import Reporting.AFA.Entity.AppUser;
import Reporting.AFA.dto.AppUserDto;

import java.util.List;

public interface AccountService {
    AppUser addNewUser(String username,String Prenom,String Nom,String email,String password,String confirmPassword);
    AppRole addNewRole(String role);
    void addRoleToUser(String username,String role);
    AppUser save(AppUserDto appUserDto);
    void removeRoleFromUser(String username,String role);
    AppUser loadUserByUsername(String username);
    public List<AppUser> getAllUsers();


}
