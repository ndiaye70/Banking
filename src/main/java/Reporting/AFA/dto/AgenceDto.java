package Reporting.AFA.dto;

import Reporting.AFA.Entity.Agence;
import lombok.Data;


@Data



public class AgenceDto {

    private String nomAgence;





    public void setNomAgence(String nomAgence) {
        this.nomAgence = nomAgence;
    }

    public Agence convertDtoToEntity(){
        Agence agence=new Agence();
        agence.setNomAgence(nomAgence);
        return agence;
    }


}
