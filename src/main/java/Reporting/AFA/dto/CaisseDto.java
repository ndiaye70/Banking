package Reporting.AFA.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
@Getter
@Setter
@Data

public class CaisseDto {
    private int billetCinqCents;
    private int billetDeuxCents;
    private int billetCent;
    private int billetCinquante;
    private int billetCinq;
    private int billetDix;
    private int billetVingt;
}

