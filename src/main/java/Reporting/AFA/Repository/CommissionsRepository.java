package Reporting.AFA.Repository;

import Reporting.AFA.Entity.Commissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommissionsRepository extends JpaRepository<Commissions, String> {

    @Query(value = "SELECT id FROM commissions ORDER BY date DESC LIMIT 1", nativeQuery = true)
    String getID();

    @Query(value = "SELECT COALESCE(commissionsom, 0) AS montant FROM commissions WHERE DATE(date) = DATE(NOW()) ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Double getCommissionsOM();

    @Query(value = "SELECT COALESCE(commissions_free_money, 0) AS montant FROM commissions WHERE DATE(date) = DATE(NOW()) ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Double getCommissionsFreeMoney();

    @Query(value = "SELECT COALESCE(commissions_wave, 0) AS montant FROM commissions WHERE DATE(date) = DATE(NOW()) ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Double getCommissionsWave();

    @Query(value = "SELECT COALESCE(commissions_wizall, 0) AS montant FROM commissions WHERE DATE(date) = DATE(NOW()) ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Double getCommissionsWizall();

    @Query(value = "SELECT COALESCE(commissions_western_union, 0) AS montant FROM commissions WHERE DATE(date) = DATE(NOW()) ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Double getCommissionsWesternUnion();

    @Query(value = "SELECT COALESCE(commissions_ria, 0) AS montant FROM commissions WHERE DATE(date) = DATE(NOW()) ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Double getCommissionsRia();

    @Query(value = "SELECT COALESCE(commissions_moneygram, 0) AS montant FROM commissions WHERE DATE(date) = DATE(NOW()) ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Double getCommissionsMoneygram();

    @Query(value = "SELECT COALESCE(commissions_ecobank, 0) AS montant FROM commissions WHERE DATE(date) = DATE(NOW()) ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Double getCommissionsEcobank();

    @Query(value = "SELECT COALESCE(commissions_small_world, 0) AS montant FROM commissions WHERE DATE(date) = DATE(NOW()) ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Double getCommissionsSmallWorld();

    @Query(value = "SELECT COALESCE(commissions_bnb, 0) AS montant FROM commissions WHERE DATE(date) = DATE(NOW()) ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Double getCommissionsBnb();

    @Query(value = "SELECT COALESCE(commissions_askia, 0) AS montant FROM commissions WHERE DATE(date) = DATE(NOW()) ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Double getCommissionsAskia();

    @Query(value = "SELECT COALESCE(commissions_sunu_assurance, 0) AS montant FROM commissions WHERE DATE(date) = DATE(NOW()) ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Double getCommissionsSunuAssurance();

    @Query(value = "SELECT COALESCE(commissions_creation_entreprise, 0) AS montant FROM commissions WHERE DATE(date) = DATE(NOW()) ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Double getCommissionsCreationEntreprise();

    @Query(value = "SELECT COALESCE(commissions_cartes_com_imp_exp, 0) AS montant FROM commissions WHERE DATE(date) = DATE(NOW()) ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Double getCommissionsCartesComImpExp();

    @Query(value = "SELECT COALESCE(commissions_change, 0) AS montant FROM commissions WHERE DATE(date) = DATE(NOW()) ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Double getCommissionsChange();

    @Query(value = "SELECT COALESCE(commissions_grossiste_bnb, 0) AS montant FROM commissions WHERE DATE(date) = DATE(NOW()) ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Double getCommissionsGrossisteBnb();

    @Query(value = "SELECT COALESCE(commissions_bnde_depot_retrait, 0) AS montant FROM commissions WHERE DATE(date) = DATE(NOW()) ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Double getCommissionsBndeDepotRetrait();

    @Query(value = "SELECT COALESCE(commissions_ouverture_compte_bnde, 0) AS montant FROM commissions WHERE DATE(date) = DATE(NOW()) ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Double getCommissionsOuvertureCompteBnde();

    @Query(value = "SELECT COALESCE(commissions_uba_cartes_prepayees, 0) AS montant FROM commissions WHERE DATE(date) = DATE(NOW()) ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Double getCommissionsUbaCartesPrepayees();

    @Query(value = "SELECT COALESCE(commissions_uba_depot_retrait, 0) AS montant FROM commissions WHERE DATE(date) = DATE(NOW()) ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Double getCommissionsUbaDepotRetrait();

    default List<Double> getMontantsCom() {
        List<Double> montants = List.of(
                getCommissionsOM() != null ? getCommissionsOM() : 0.0,
                getCommissionsFreeMoney() != null ? getCommissionsFreeMoney() : 0.0,
                getCommissionsWave() != null ? getCommissionsWave() : 0.0,
                getCommissionsWizall() != null ? getCommissionsWizall() : 0.0,
                getCommissionsWesternUnion() != null ? getCommissionsWesternUnion() : 0.0,
                getCommissionsRia() != null ? getCommissionsRia() : 0.0,
                getCommissionsMoneygram() != null ? getCommissionsMoneygram() : 0.0,
                getCommissionsSmallWorld() != null ? getCommissionsSmallWorld() : 0.0,
                getCommissionsBnb() != null ? getCommissionsBnb() : 0.0,
                getCommissionsEcobank() != null ? getCommissionsEcobank() : 0.0,
                getCommissionsAskia() != null ? getCommissionsAskia() : 0.0,
                getCommissionsSunuAssurance() != null ? getCommissionsSunuAssurance() : 0.0,
                getCommissionsCreationEntreprise() != null ? getCommissionsCreationEntreprise() : 0.0,
                getCommissionsCartesComImpExp() != null ? getCommissionsCartesComImpExp() : 0.0,
                getCommissionsChange() != null ? getCommissionsChange() : 0.0,
                getCommissionsGrossisteBnb() != null ? getCommissionsGrossisteBnb() : 0.0,
                getCommissionsBndeDepotRetrait() != null ? getCommissionsBndeDepotRetrait() : 0.0,
                getCommissionsOuvertureCompteBnde() != null ? getCommissionsOuvertureCompteBnde() : 0.0,
                getCommissionsUbaCartesPrepayees() != null ? getCommissionsUbaCartesPrepayees() : 0.0,
                getCommissionsUbaDepotRetrait() != null ? getCommissionsUbaDepotRetrait() : 0.0
        );
        return montants;
    }
}
