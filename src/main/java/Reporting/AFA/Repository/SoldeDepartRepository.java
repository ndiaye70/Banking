package Reporting.AFA.Repository;

import Reporting.AFA.Entity.SoldeDepart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SoldeDepartRepository extends JpaRepository<SoldeDepart, String> {


    @Query(value = "SELECT id FROM solde_depart ORDER BY date DESC limit 1", nativeQuery = true)
    String getID();

    @Query(value = "SELECT COALESCE(orange_money, 0) AS montant " +
            "FROM solde_depart " +
            "WHERE DATE(date) = DATE(NOW()) " +
            "ORDER BY id DESC " +
            "LIMIT 1", nativeQuery = true)
    Double getOrangeMoney();

    @Query(value = "SELECT COALESCE(free_money, 0) AS montant " +
            "FROM solde_depart " +
            "WHERE DATE(date) = DATE(NOW()) " +
            "ORDER BY id DESC " +
            "LIMIT 1", nativeQuery = true)
    Double getFreeMoney();

    @Query(value = "SELECT COALESCE(wave, 0) AS montant " +
            "FROM solde_depart " +
            "WHERE DATE(date) = DATE(NOW()) " +
            "ORDER BY id DESC " +
            "LIMIT 1", nativeQuery = true)
    Double getWave();

    @Query(value ="select COALESCE(wizall, 0) as montant from solde_depart where DATE(date) = DATE(NOW())  ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Double getWizzal();

    @Query(value ="(select COALESCE(western_union, 0) as montant from solde_depart where DATE(date) = DATE(NOW()) ORDER BY id DESC LIMIT 1)", nativeQuery = true)
    Double getwestern_union();

    @Query(value ="(select COALESCE(ria, 0) as montant from solde_depart where DATE(date) = DATE(NOW()) ORDER BY id DESC LIMIT 1)", nativeQuery = true)
    Double getria();

    @Query(value ="(select COALESCE(moneygram, 0) as montant from solde_depart where DATE(date) = DATE(NOW()) ORDER BY id DESC LIMIT 1)", nativeQuery = true)
    Double getmoneygram();

    @Query(value ="(select COALESCE(small_world, 0) as montant from solde_depart where DATE(date) = DATE(NOW()) ORDER BY id DESC LIMIT 1)", nativeQuery = true)
    Double getsmall_world();

    @Query(value ="(select COALESCE(bnb, 0) as montant from solde_depart where DATE(date) = DATE(NOW()) ORDER BY id DESC LIMIT 1)", nativeQuery = true)
    Double getbnb();

    @Query(value ="(select COALESCE(ecobank, 0) as montant from solde_depart where DATE(date) = DATE(NOW()) ORDER BY id DESC LIMIT 1)", nativeQuery = true)
    Double getecobank();

    @Query(value ="(select COALESCE(askia, 0) as montant from solde_depart where DATE(date) = DATE(NOW()) ORDER BY id DESC LIMIT 1)", nativeQuery = true)
    Double getaskia();
    @Query(value ="(select COALESCE(sunu_assurance, 0) as montant from solde_depart where DATE(date) = DATE(NOW()) ORDER BY id DESC LIMIT 1)", nativeQuery = true)
    Double getsunu_assurance();

    @Query(value ="(select COALESCE(creation_entreprise, 0) as montant from solde_depart where DATE(date) = DATE(NOW()) ORDER BY id DESC LIMIT 1)", nativeQuery = true)
    Double getcreation_entreprise();

    @Query(value ="(select COALESCE(cartes_com_imp_exp, 0) as montant from solde_depart where DATE(date) = DATE(NOW()) ORDER BY id DESC LIMIT 1)", nativeQuery = true)
    Double getcartes_com_imp_exp();

    @Query(value ="(select COALESCE(change, 0) as montant from solde_depart where DATE(date) = DATE(NOW()) ORDER BY id DESC LIMIT 1)", nativeQuery = true)
    Double getchange();

    @Query(value ="(select COALESCE(grossiste_bnb, 0) as montant from solde_depart where DATE(date) = DATE(NOW()) ORDER BY id DESC LIMIT 1)", nativeQuery = true)
    Double getgrossiste_bnb();

    @Query(value ="(select COALESCE(bnde_depot_retrait, 0) as montant from solde_depart where DATE(date) = DATE(NOW()) ORDER BY id DESC LIMIT 1)", nativeQuery = true)
    Double getbnde_depot_retrait();

    @Query(value ="(select COALESCE(ouverture_compte_bnde, 0) as montant from solde_depart where DATE(date) = DATE(NOW()) ORDER BY id DESC LIMIT 1)", nativeQuery = true)
    Double getouverture_compte_bnde();

    @Query(value ="(select COALESCE(uba_cartes_prepayees, 0) as montant from solde_depart where DATE(date) = DATE(NOW()) ORDER BY id DESC LIMIT 1)", nativeQuery = true)
    Double getuba_cartes_prepayees();

    @Query(value ="(select COALESCE(uba_depot_retrait, 0) as montant from solde_depart where DATE(date) = DATE(NOW()) ORDER BY id DESC LIMIT 1)", nativeQuery = true)
    Double getuba_depot_retrait();



    default List<Double> getMontants() {
        List<Double> montants = List.of(
                getOrangeMoney() != null ? getOrangeMoney() : 0.0,
                getFreeMoney() != null ? getFreeMoney() : 0.0,
                getWave() != null ? getWave() : 0.0,
                getWizzal() != null ? getWizzal() : 0.0,
                getwestern_union() != null ? getwestern_union() : 0.0,
                getria() != null ? getria() : 0.0,
                getmoneygram() != null ? getmoneygram() : 0.0,
                getsmall_world() != null ? getsmall_world() : 0.0,
                getbnb() != null ? getbnb() : 0.0,
                getecobank() != null ? getecobank() : 0.0,
                getaskia() != null ? getaskia() : 0.0,
                getsunu_assurance() != null ? getsunu_assurance() : 0.0,
                getcreation_entreprise() != null ? getcreation_entreprise() : 0.0,
                getcartes_com_imp_exp() != null ? getcartes_com_imp_exp() : 0.0,
                getchange() != null ? getchange() : 0.0,
                getgrossiste_bnb() != null ? getgrossiste_bnb() : 0.0,
                getbnde_depot_retrait() != null ? getbnde_depot_retrait() : 0.0,
                getouverture_compte_bnde() != null ? getouverture_compte_bnde() : 0.0,
                getuba_cartes_prepayees() != null ? getuba_cartes_prepayees() : 0.0,
                getuba_depot_retrait() != null ? getuba_depot_retrait() : 0.0
                // Ajoutez des appels pour les autres méthodes de montant ici
        );
        return montants;
    }


}
