package Reporting.AFA.Entity;

import Reporting.AFA.Entity.AppRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;


@Entity
@Table(name="Utilisateur",uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Data
@AllArgsConstructor
@Builder
public class AppUser {
    @Id
    private String id;
    @Column(name="username",nullable=false,unique=true)
    private String username;

    private String Prenom;
    private String Nom;
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<AppRole> roles;

    private String generateId() {
        return UUID.randomUUID().toString();
    }
    public AppUser(){
        this.id=generateId();
    }



}
