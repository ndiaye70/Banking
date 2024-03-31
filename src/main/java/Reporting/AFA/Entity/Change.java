package Reporting.AFA.Entity;

import Reporting.AFA.services.CourDuJourService;
import jakarta.persistence.*;
import lombok.Data;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Entity
@Data
public class Change {

    @Id
    private String id;

    private String date;
    private String prenom;
    private String nom;

    @Enumerated(EnumType.STRING)
    private Service service;


    @Enumerated(EnumType.STRING)
    private Type_Piece_Identite typePieceIdentite;

    private String numero_piece_identité;

    private String date_delivrance;

    private String Telephone;

    @Enumerated(EnumType.STRING)
    private Resident resident;

    @Enumerated(EnumType.STRING)
    private Devise deviseRecu;

    @Enumerated(EnumType.STRING)
    private Devise deviseRemis;

    private double montantRecu;
    private double montantRemis;

    private Integer CNI;
    private Integer Passeport;
    private Integer Billet_Avion;

    @ManyToOne
    @JoinColumn(name = "id_agent")
    private Agent agent;

    private String generateId() {
        return UUID.randomUUID().toString();
    }

    public Change() {
        this.id = generateId();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.date = now.format(formatter);
        this.montantRemis = 0.0;
        this.Billet_Avion=0;
        this.CNI=0;
        this.Passeport=0;
    }

    // Enumérations
    public enum Service {
        Achat_de_Devise("Achat De Devise"), Vente_Devise("Vente de Devise");

        private final String label;

        Service(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

    public enum Type_Piece_Identite  {
        CNI("CNI"), Passport("Passeport"),Carte_Consulaire("Carte Consulaire");

        private final String label;

        Type_Piece_Identite(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

    public enum Devise {
        EUR, XOF, USD
    }

    public enum Resident {
        OUI,NON
    }

    public double convertirMontants(CourDuJourService courDuJourService) {
        Optional<CourDuJour> courDuJour = courDuJourService.getCourDuJourById(1L); // Récupère l'objet CourDuJour avec l'ID 1
        if (courDuJour != null) {
            CourDuJour courDuJour1 = courDuJour.get();
            double achatEuro = courDuJour1.getAchatEuro();
            double venteEuro = courDuJour1.getVenteEuro();
            double achatUSD = courDuJour1.getAchatUSD();
            double venteUSD = courDuJour1.getVenteUSD();

            if (!Objects.equals(deviseRecu, deviseRemis)) {
                switch (deviseRecu) {
                    case EUR:
                        montantRemis = montantRecu * achatEuro;
                        break;
                    case USD:
                        montantRemis = montantRecu * achatUSD;
                        break;
                    case XOF:
                        if (deviseRemis == Devise.EUR) {
                            montantRemis = montantRecu / venteEuro;
                            montantRemis = Math.round(montantRemis * 100.0) / 100.0; // Arrondi à deux chiffres après la virgule

                        } else if (deviseRemis == Devise.USD) {
                            montantRemis = montantRecu / venteUSD;
                            montantRemis = Math.round(montantRemis * 100.0) / 100.0; // Arrondi à deux chiffres après la virgule

                        }
                        break;
                }
                return montantRemis;
            } else {
                System.out.println("Les devises sont identiques, aucune conversion n'est nécessaire.");
            }
        } else {
            System.out.println("L'instance de CourDuJour avec l'ID 1 n'a pas été trouvée.");
        }
        return montantRecu;
    }

}
