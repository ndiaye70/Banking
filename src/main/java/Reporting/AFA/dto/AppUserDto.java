package Reporting.AFA.dto;

import Reporting.AFA.Entity.AppUser;
import lombok.Data;

@Data
public class AppUserDto {
    private String Nom;
    private String Prenom;
    private String username;
    private String email;
    private String Password;

    public AppUser toEntity() {
        AppUser appUser = new AppUser();
        appUser.setNom(this.Nom);
        appUser.setPrenom(this.Prenom);
        appUser.setUsername(this.username);
        appUser.setEmail(this.email);
        appUser.setPassword(this.Password);
        return appUser;
    }
}
