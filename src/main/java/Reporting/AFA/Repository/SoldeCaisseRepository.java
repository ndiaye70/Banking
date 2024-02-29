package Reporting.AFA.Repository;

import Reporting.AFA.Entity.SoldeCaisse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface SoldeCaisseRepository extends JpaRepository<SoldeCaisse, Long> {

    @Query(value = "SELECT id FROM solde_caisse ORDER BY date DESC limit 1", nativeQuery = true)
    String getID();

    @Query(value = "SELECT " +
            "(SELECT COALESCE(SUM(montant), 0) FROM operations WHERE nature_operation IN " +
            "('Dépôt','Seddo','IZI','Envoie','Encaissement_Assurance','Encaissement_Création_Entreprise'," +
            "'Encaissement_Carte','Paiement_Facture','Achat_Credit_Telephonique') AND DATE(date) = DATE(NOW())) +" +
            "(SELECT COALESCE(SUM(montant), 0) FROM operations_bancaires WHERE nature_operations IN " +
            "('Depot','Achat_Carte_Prépayée','Recharge_Carte_Prépayée') AND DATE(date) = DATE(NOW())) +" +
            "(SELECT COALESCE(SUM(commissions), 0) FROM operations_bancaires WHERE nature_operations = 'retrait' " +
            "AND DATE(date) = DATE(NOW())) +" +
            "(SELECT COALESCE(SUM(montant_recu), 0) FROM change WHERE devise_recu = 'XOF' AND DATE(date) = DATE(NOW())) +" +
            "(SELECT COALESCE(SUM(montant_depot_initial), 0) FROM ouverture_compte WHERE type_compte IN " +
            "('compte_courant','compte_epargne') AND DATE(date) = DATE(NOW())) +" +
            "(SELECT COALESCE(SUM(montant_depot_initial), 0) FROM ouverture_cpte_entreprise WHERE DATE(date) = DATE(NOW())) +" +
            "(SELECT COALESCE(SUM(montant), 0) FROM grossiste WHERE montant_paye_par = 'Especes' AND DATE(date) = DATE(NOW())) +" +
            "(SELECT COALESCE(SUM(frais), 0) FROM grossiste WHERE frais_paye_par = 'Especes' AND DATE(date) = DATE(NOW()))" +
            "AS somme_totale", nativeQuery = true)
    Double getSommeTotale();

    @Query(value = "SELECT " +
            "(SELECT COALESCE(SUM(montant), 0) FROM operations WHERE nature_operation IN " +
            "('retrait','RetraitCode','Decaissement_Assurance','Decaissement_Création_Entreprise','Decaissement_carte') " +
            "AND DATE(date) = DATE(NOW())) +" +
            "(SELECT COALESCE(SUM(montant), 0) FROM operations_bancaires WHERE nature_operations IN " +
            "('retrait','Décaissement_UBA_CP') AND DATE(date) = DATE(NOW())) +" +
            "(SELECT COALESCE(SUM(montant_remis), 0) FROM change WHERE devise_remis = 'XOF' AND DATE(date) = DATE(NOW()))" +
            "AS retrait_totale", nativeQuery = true)
    Double getRetraitTotal();


    @Query(value = "SELECT " +
            "(SELECT COALESCE(SUM(montant), 0) FROM Approvisionnement WHERE type='Approvisionnement_caisse_Entrant' " +
            "AND devise='XOF' AND DATE(date)=DATE(NOW())) +" +
            "(SELECT COALESCE(SUM(montant), 0) FROM Approvisionnement WHERE type='Approvisionnement_compte_virtuel_sortant' " +
            "AND destination='Caisse' AND DATE(date)=DATE(NOW())) +" +
            "(SELECT COALESCE(SUM(montant), 0) FROM Approvisionnement WHERE type='Approvisionnement_compte_virtuel_sortant' " +
            "AND compte_caisse2='Caisse' AND DATE(date)=DATE(NOW()))" +
            "AS somme_totale2", nativeQuery = true)
    Double getApproEntrant();


    @Query(value = "SELECT " +
            "(SELECT COALESCE(SUM(montant), 0) FROM Approvisionnement WHERE type='Approvisionnement_caisse_sortant' " +
            "AND devise='XOF' AND DATE(date)=DATE(NOW())) +" +
            "(SELECT COALESCE(SUM(montant), 0) FROM Approvisionnement WHERE type='Approvisionnement_compte_virtuel_entrant' " +
            "AND origine='Caisse' AND DATE(date)=DATE(NOW())) +" +
            "(SELECT COALESCE(SUM(montant), 0) FROM Approvisionnement WHERE type='Approvisionnement_compte_virtuel_entrant' " +
            "AND compte_caisse1='Caisse' AND DATE(date)=DATE(NOW()))" +
            "AS Appro_Sortant", nativeQuery = true)
    Double getApproSortant();


    @Query(value = "SELECT COALESCE(SUM(montant_recu), 0) AS totale_EUR FROM change WHERE devise_recu = 'EUR' AND DATE(date) = DATE(NOW())", nativeQuery = true)
    Double getTotalEURReceived();

    @Query(value = "SELECT COALESCE(SUM(montant_remis), 0) AS retrait_EUR FROM change WHERE devise_remis = 'EUR' AND DATE(date) = DATE(NOW())", nativeQuery = true)
    Double getTotalEURWithdrawn();


    @Query(value = "SELECT COALESCE(SUM(montant), 0) as ApproEUR_entrant FROM Approvisionnement WHERE type='Approvisionnement_caisse_Entrant' AND devise='EUR' AND DATE(date)=DATE(NOW())", nativeQuery = true)
    Double getApprovisionnementEURIncoming();

    @Query(value = "SELECT COALESCE(SUM(montant), 0) as ApproEUR_sortant FROM Approvisionnement WHERE type='Approvisionnement_caisse_sortant' AND devise='EUR' AND DATE(date)=DATE(NOW())", nativeQuery = true)
    Double getApprovisionnementEUROutgoing();


    @Query(value = "SELECT COALESCE(SUM(montant_recu), 0) AS totale_USD FROM change WHERE devise_recu = 'USD' AND DATE(date) = DATE(NOW())", nativeQuery = true)
    Double getTotalUSDReceived();

    @Query(value = "SELECT COALESCE(SUM(montant_remis), 0) AS retrait_USD FROM change WHERE devise_remis = 'USD' AND DATE(date) = DATE(NOW())", nativeQuery = true)
    Double getTotalUSDWithdrawn();


    @Query(value = "SELECT COALESCE(SUM(montant), 0) as ApproUSD_entrant FROM Approvisionnement WHERE type='Approvisionnement_caisse_Entrant' AND devise='USD' AND DATE(date)=DATE(NOW())", nativeQuery = true)
    Double getApprovisionnementUSDIncoming();

    @Query(value = "SELECT COALESCE(SUM(montant), 0) as ApproUSD_sortant FROM Approvisionnement WHERE type='Approvisionnement_caisse_sortant' AND devise='USD' AND DATE(date)=DATE(NOW())", nativeQuery = true)
    Double getApprovisionnementUSDOutgoing();


    default List<Double> getMontants() {
        return List.of(
                getSommeTotale() != null ? getSommeTotale() : 0.0,
                getRetraitTotal() != null ? getRetraitTotal() : 0.0,
                getApproEntrant() != null ? getApproEntrant() : 0.0,
                getApproSortant() != null ? getApproSortant() : 0.0,
                getTotalEURReceived() != null ? getTotalEURReceived() : 0.0,
                getTotalEURWithdrawn() != null ? getTotalEURWithdrawn() : 0.0,
                getApprovisionnementEURIncoming() != null ? getApprovisionnementEURIncoming() : 0.0,
                getApprovisionnementEUROutgoing() != null ? getApprovisionnementEUROutgoing() : 0.0,
                getTotalUSDReceived() != null ? getTotalUSDReceived() : 0.0,
                getTotalUSDWithdrawn() != null ? getTotalUSDWithdrawn() : 0.0,
                getApprovisionnementUSDIncoming() != null ? getApprovisionnementUSDIncoming() : 0.0,
                getApprovisionnementUSDOutgoing() != null ? getApprovisionnementUSDOutgoing() : 0.0
                // Ajoutez d'autres appels pour les méthodes de requête ici
        );
        // Ajoutez des méthodes de requête personnalisées si nécessaire
    }
}
