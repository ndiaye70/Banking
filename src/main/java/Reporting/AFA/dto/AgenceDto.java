package Reporting.AFA.dto;

import Reporting.AFA.Entity.Agence;
import lombok.Data;


@Data



public class AgenceDto {

    private String nomAgence;
    private String code;





    //public void setNomAgence(String nomAgence) {
     //   this.nomAgence = nomAgence;
   // }

    public Agence convertDtoToEntity(){
        Agence agence=new Agence();
        agence.setNomAgence(nomAgence);
        agence.setCode(code);

        return agence;
    }


}
