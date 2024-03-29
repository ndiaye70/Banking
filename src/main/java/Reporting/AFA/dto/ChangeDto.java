package Reporting.AFA.dto;

import Reporting.AFA.Entity.Change;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeDto {

    private String prenom;
    private String nom;
    private Change.Service service;
    private String deviseRecu;
    private String deviseRemis;
    private double montantRecu;
    private Change.Type_Piece_Identite typePieceIdentite;
    private String numero_piece_identité;
    private String date_delivrance;
    private String Telephone;
    private String resident;
    private Integer Passeport ;
    private Integer Billet_Avion;
    private Integer CNI;


    public Change toEntity() {
        Change change = new Change();
        change.setPrenom(this.prenom);
        change.setNom(this.nom);
        change.setService(this.service);
        change.setDeviseRecu(Change.Devise.valueOf(this.deviseRecu));
        change.setDeviseRemis(Change.Devise.valueOf(this.deviseRemis));
        change.setMontantRecu(this.montantRecu);
        change.setTypePieceIdentite(this.typePieceIdentite);
        change.setNumero_piece_identité(this.numero_piece_identité);
        change.setDate_delivrance(this.date_delivrance);
        change.setTelephone(this.Telephone);
        change.setResident(Change.Resident.valueOf(this.resident));
        change.setPasseport(this.Passeport);
        change.setBillet_Avion(this.Billet_Avion);
        change.setCNI(this.CNI);
        return change;
    }


}
