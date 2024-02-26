package Reporting.AFA.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.*;

import java.util.*;

@Repository

public class CustomRepositoryImpl implements CustomRepository {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private SoldeDepartRepository soldeDepartRepository;

    @Autowired
    private  CommissionsRepository commissionsRepository;

    public CustomRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Map<String, Object>> getRecaps() {
        List<Map<String, Object>> recaps = new ArrayList<>();

        Query query = entityManager.createNativeQuery("SELECT 'Orange Money' AS service, " +
                "    (SELECT COALESCE(SUM(CASE WHEN nature_operation IN ('Dépôt', 'Seddo', 'Paiement_de_Facture') THEN montant ELSE 0 END), 0) FROM operations WHERE service = 'Orange_money' AND DATE(date) = DATE(NOW())) AS montant_depot, " +
                "    (SELECT COALESCE(SUM(CASE WHEN nature_operation IN ('retrait', 'RetraitCode') THEN montant ELSE 0 END), 0) FROM operations WHERE service = 'Orange_money' AND DATE(date) = DATE(NOW())) AS montant_retrait, " +
                "    (SELECT COALESCE(COUNT(*), 0) FROM operations WHERE service = 'Orange_money' AND DATE(date) = DATE(NOW())) AS nombre_de_client, " +
                "    (SELECT COALESCE(SUM(CASE WHEN type IN ('Approvisionnement_caisse_sortant','Approvisionnement_compte_virtuel_entrant') AND compte_caisse2='Orange_Money' THEN montant ELSE 0 END), 0) FROM approvisionnement WHERE DATE(date) = DATE(NOW())) + " +
                "    (SELECT COALESCE(SUM(CASE WHEN montant_paye_par='OM' THEN montant ELSE 0 END), 0) FROM grossiste WHERE DATE(date) = DATE(NOW())) + " +
                "    (SELECT COALESCE(SUM(CASE WHEN frais_paye_par='OM' THEN frais ELSE 0 END), 0) FROM grossiste WHERE DATE(date) = DATE(NOW())) AS Appro_Entrant, " +
                "    (SELECT COALESCE(SUM(CASE WHEN type IN ('Approvisionnement_caisse_entrant','Approvisionnement_compte_virtuel_sortant') AND compte_caisse1='Orange_Money' THEN -montant ELSE 0 END), 0) FROM approvisionnement WHERE DATE(date) = DATE(NOW())) AS Appro_Sortant " +
                "UNION ALL " +
                "SELECT 'Free Money' AS service, " +
                "    (SELECT COALESCE(SUM(CASE WHEN nature_operation IN ('Dépôt', 'IZI', 'Paiement_de_Facture') THEN montant ELSE 0 END), 0) FROM operations WHERE service = 'Free_money' AND DATE(date) = DATE(NOW())) AS montant_depot, " +
                "    (SELECT COALESCE(SUM(CASE WHEN nature_operation IN ('retrait', 'RetraitCode') THEN montant ELSE 0 END), 0) FROM operations WHERE service = 'Free_money' AND DATE(date) = DATE(NOW())) AS montant_retrait, " +
                "    (SELECT COALESCE(COUNT(DISTINCT service), 0) FROM operations WHERE service = 'Free_money' AND DATE(date) = DATE(NOW())) AS nombre_de_client, " +
                "    (SELECT COALESCE(SUM(CASE WHEN type IN ('Approvisionnement_caisse_sortant','Approvisionnement_compte_virtuel_entrant') AND compte_caisse2='Free_Money' THEN montant ELSE 0 END), 0) FROM approvisionnement WHERE DATE(date) = DATE(NOW())) AS Appro_Entrant, " +
                "    (SELECT COALESCE(SUM(CASE WHEN type IN ('Approvisionnement_caisse_entrant','Approvisionnement_compte_virtuel_sortant') AND compte_caisse1='Free_Money' THEN -montant ELSE 0 END), 0) FROM approvisionnement WHERE DATE(date) = DATE(NOW())) AS Appro_Sortant " +
                "UNION ALL " +
                "SELECT 'Wave' AS service, " +
                "    (SELECT COALESCE(SUM(CASE WHEN nature_operation IN ('Dépôt', 'Achat_Credit_Telephonique', 'Paiement_de_Facture') THEN montant ELSE 0 END), 0) FROM operations WHERE service = 'Wave' AND DATE(date) = DATE(NOW())) AS montant_depot, " +
                "    (SELECT COALESCE(SUM(CASE WHEN nature_operation IN ('retrait', 'RetraitCode') THEN montant ELSE 0 END), 0) FROM operations WHERE service = 'Wave' AND DATE(date) = DATE(NOW())) AS montant_retrait, " +
                "    (SELECT COALESCE(COUNT(DISTINCT service), 0) FROM operations WHERE service = 'Wave' AND DATE(date) = DATE(NOW())) AS nombre_de_client, " +
                "    (SELECT COALESCE(SUM(CASE WHEN type IN ('Approvisionnement_caisse_sortant','Approvisionnement_compte_virtuel_entrant') AND compte_caisse2='Wave' THEN montant ELSE 0 END), 0) FROM approvisionnement WHERE DATE(date) = DATE(NOW())) + " +
                "    (SELECT COALESCE(SUM(CASE WHEN montant_paye_par='Wave' THEN montant ELSE 0 END), 0) FROM grossiste WHERE DATE(date) = DATE(NOW())) + " +
                "    (SELECT COALESCE(SUM(CASE WHEN frais_paye_par='Wave' THEN frais ELSE 0 END), 0) FROM grossiste WHERE DATE(date) = DATE(NOW())) AS Appro_Entrant, " +
                "    (SELECT COALESCE(SUM(CASE WHEN type IN ('Approvisionnement_caisse_entrant','Approvisionnement_compte_virtuel_sortant') AND compte_caisse1='Wave' THEN -montant ELSE 0 END), 0) FROM approvisionnement WHERE DATE(date) = DATE(NOW())) AS Appro_Sortant " +
                "UNION ALL " +
                "SELECT 'Wizzal' AS service, " +
                "   (SELECT COALESCE(SUM(CASE WHEN nature_operation IN ('Dépôt', 'Achat_Credit_Telephonique', 'Paiement_de_Facture') THEN montant ELSE 0 END), 0) FROM operations WHERE service = 'Wizall' AND DATE(date) = DATE(NOW())) AS montant_depot, " +
                "   (SELECT COALESCE(SUM(CASE WHEN nature_operation IN ('retrait', 'RetraitCode') THEN montant ELSE 0 END), 0) FROM operations WHERE service = 'Wizall' AND DATE(date) = DATE(NOW())) AS montant_retrait, " +
                "    (SELECT COALESCE(COUNT(service), 0) FROM operations WHERE service = 'Wizall' AND DATE(date) = DATE(NOW())) AS nombre_de_client, " +
                "    COALESCE( " +
                "        (SELECT SUM(CASE WHEN type IN ('Approvisionnement_caisse_sortant','Approvisionnement_compte_virtuel_entrant') AND compte_caisse2='Wizall' THEN montant ELSE 0 END) FROM approvisionnement WHERE DATE(date) = DATE(NOW()))," +
                "        0 " +
                "    ) AS Appro_Entrant, " +
                "    COALESCE( " +
                "        (SELECT SUM(CASE WHEN type IN ('Approvisionnement_caisse_entrant','Approvisionnement_compte_virtuel_sortant') AND compte_caisse1='Wizall' THEN -montant ELSE 0 END) FROM approvisionnement WHERE DATE(date) = DATE(NOW())), " +
                "        0 " +
                "    ) AS Appro_Sortant " +
                "UNION ALL " +

                "SELECT 'Western_union' AS service, " +
                "    (SELECT COALESCE(SUM(CASE WHEN nature_operation IN ('Envoie') THEN montant ELSE 0 END), 0) FROM operations WHERE service = 'Western_union' AND DATE(date) = DATE(NOW())) AS montant_depot, " +
                "    (SELECT COALESCE(SUM(CASE WHEN nature_operation IN ('retrait') THEN montant ELSE 0 END), 0) FROM operations WHERE service = 'Western_union' AND DATE(date) = DATE(NOW())) AS montant_retrait," +
                "    (SELECT COALESCE(COUNT(service), 0) FROM operations WHERE service = 'Western_union' AND DATE(date) = DATE(NOW())) AS nombre_de_client, " +
                "    COALESCE( " +
                "        (SELECT SUM(CASE WHEN type IN ('Approvisionnement_caisse_sortant','Approvisionnement_compte_virtuel_entrant') AND compte_caisse2='Western_union' THEN montant ELSE 0 END) FROM approvisionnement WHERE DATE(date) = DATE(NOW())), " +
                "        0 " +
                "    ) AS Appro_Entrant, " +
                "    COALESCE(" +
                "        (SELECT SUM(CASE WHEN type IN ('Approvisionnement_caisse_entrant','Approvisionnement_compte_virtuel_sortant') AND compte_caisse1='Western_union' THEN -montant ELSE 0 END) FROM approvisionnement WHERE DATE(date) = DATE(NOW())), " +
                "        0" +
                "    ) AS Appro_Sortant " +
                "UNION ALL " +
                "SELECT 'Ria' AS service, " +
                "    (SELECT COALESCE(SUM(CASE WHEN nature_operation IN ('Envoie') THEN montant ELSE 0 END), 0) FROM operations WHERE service = 'Ria' AND DATE(date) = DATE(NOW())) AS montant_depot, " +
                "    (SELECT COALESCE(SUM(CASE WHEN nature_operation IN ('retrait') THEN montant ELSE 0 END), 0) FROM operations WHERE service = 'Ria' AND DATE(date) = DATE(NOW())) AS montant_retrait, " +
                "    (SELECT COALESCE(COUNT(service), 0) FROM operations WHERE service = 'Ria' AND DATE(date) = DATE(NOW())) AS nombre_de_client, " +
                "    COALESCE(" +
                "        (SELECT SUM(CASE WHEN type IN ('Approvisionnement_caisse_sortant','Approvisionnement_compte_virtuel_entrant') AND compte_caisse2='Ria' THEN montant ELSE 0 END) FROM approvisionnement WHERE DATE(date) = DATE(NOW())), " +
                "        0 " +
                "    ) AS Appro_Entrant, " +
                "    COALESCE( " +
                "        (SELECT SUM(CASE WHEN type IN ('Approvisionnement_caisse_entrant','Approvisionnement_compte_virtuel_sortant') AND compte_caisse1='Ria' THEN -montant ELSE 0 END) FROM approvisionnement WHERE DATE(date) = DATE(NOW())), " +
                "        0" +
                "    ) AS Appro_Sortant " +
                "UNION ALL " +

                "SELECT 'Moneygram' AS service, " +
                "    (SELECT COALESCE(SUM(CASE WHEN nature_operation IN ('Envoie') THEN montant ELSE 0 END), 0) FROM operations WHERE service = 'Moneygram' AND DATE(date) = DATE(NOW())) AS montant_depot, " +
                "    (SELECT COALESCE(SUM(CASE WHEN nature_operation IN ('retrait') THEN montant ELSE 0 END), 0) FROM operations WHERE service = 'Moneygram' AND DATE(date) = DATE(NOW())) AS montant_retrait, " +
                "    (SELECT COALESCE(COUNT(service), 0) FROM operations WHERE service = 'Moneygram' AND DATE(date) = DATE(NOW())) AS nombre_de_client, " +
                "    COALESCE( " +
                "        (SELECT SUM(CASE WHEN type IN ('Approvisionnement_caisse_sortant','Approvisionnement_compte_virtuel_entrant') AND compte_caisse2='Moneygram' THEN montant ELSE 0 END) FROM approvisionnement WHERE DATE(date) = DATE(NOW())), " +
                "        0 " +
                "    ) AS Appro_Entrant, " +
                "    COALESCE( " +
                "        (SELECT SUM(CASE WHEN type IN ('Approvisionnement_caisse_entrant','Approvisionnement_compte_virtuel_sortant') AND compte_caisse1='Moneygram' THEN -montant ELSE 0 END) FROM approvisionnement WHERE DATE(date) = DATE(NOW())), " +
                "        0 " +
                "    ) AS Appro_Sortant " +
                "UNION ALL " +
                "SELECT 'Smallworld' AS service, " +
                "    (SELECT COALESCE(SUM(CASE WHEN nature_operation IN ('Envoie') THEN montant ELSE 0 END), 0) FROM operations WHERE service = 'Smallworld' AND DATE(date) = DATE(NOW())) AS montant_depot, " +
                "    (SELECT COALESCE(SUM(CASE WHEN nature_operation IN ('retrait') THEN montant ELSE 0 END), 0) FROM operations WHERE service = 'Smallworld' AND DATE(date) = DATE(NOW())) AS montant_retrait, " +
                "    (SELECT COALESCE(COUNT(service), 0) FROM operations WHERE service = 'Smallworld' AND DATE(date) = DATE(NOW())) AS nombre_de_client, " +
                "    COALESCE( " +
                "        (SELECT SUM(CASE WHEN type IN ('Approvisionnement_caisse_sortant','Approvisionnement_compte_virtuel_entrant') AND compte_caisse2='Smallworld' THEN montant ELSE 0 END) FROM approvisionnement WHERE DATE(date) = DATE(NOW())), " +
                "        0 " +
                "    ) AS Appro_Entrant, " +
                "    COALESCE( " +
                "        (SELECT SUM(CASE WHEN type IN ('Approvisionnement_caisse_entrant','Approvisionnement_compte_virtuel_sortant') AND compte_caisse1='Smallworld' THEN -montant ELSE 0 END) FROM approvisionnement WHERE DATE(date) = DATE(NOW())), " +
                "        0 " +
                "    ) AS Appro_Sortant " +
                "UNION ALL " +
                "SELECT 'BnB' AS service, " +
                "    (SELECT COALESCE(SUM(CASE WHEN nature_operation IN ('Envoie') THEN montant ELSE 0 END), 0) FROM operations WHERE service = 'BnB' AND DATE(date) = DATE(NOW())) AS montant_depot, " +
                "    (SELECT COALESCE(SUM(CASE WHEN nature_operation IN ('retrait') THEN montant ELSE 0 END), 0) FROM operations WHERE service = 'BnB' AND DATE(date) = DATE(NOW())) AS montant_retrait, " +
                "    (SELECT COALESCE(COUNT(service), 0) FROM operations WHERE service = 'BnB' AND DATE(date) = DATE(NOW())) AS nombre_de_client, " +
                "    COALESCE( " +
                "        (SELECT SUM(CASE WHEN type IN ('Approvisionnement_caisse_sortant','Approvisionnement_compte_virtuel_entrant') AND compte_caisse2='BnB' THEN montant ELSE 0 END) FROM approvisionnement WHERE DATE(date) = DATE(NOW())), " +
                "        0 " +
                "    ) AS Appro_Entrant, " +
                "    COALESCE( " +
                "        (SELECT SUM(CASE WHEN type IN ('Approvisionnement_caisse_entrant','Approvisionnement_compte_virtuel_sortant') AND compte_caisse1='BnB' THEN -montant ELSE 0 END) FROM approvisionnement WHERE DATE(date) = DATE(NOW())), " +
                "        0 " +
                "    ) AS Appro_Sortant " +
                "UNION ALL " +
                "SELECT 'Ecobank' AS service, " +
                "    (SELECT COALESCE(SUM(CASE WHEN nature_operation IN ('Dépôt') THEN montant ELSE 0 END), 0) FROM operations WHERE service = 'Ecobank' AND DATE(date) = DATE(NOW())) AS montant_depot, " +
                "    (SELECT COALESCE(SUM(CASE WHEN nature_operation IN ('retrait','RetraitCode') THEN montant ELSE 0 END), 0) FROM operations WHERE service = 'Ecobank' AND DATE(date) = DATE(NOW()) ) AS montant_retrait, " +
                "    (SELECT COALESCE(COUNT(service), 0) FROM operations WHERE service = 'Ecobank' AND DATE(date) = DATE(NOW())) AS nombre_de_client, " +
                "    COALESCE( " +
                "        (SELECT SUM(CASE WHEN type IN ('Approvisionnement_caisse_sortant','Approvisionnement_compte_virtuel_entrant') AND compte_caisse2='Ecobank' THEN montant ELSE 0 END) FROM approvisionnement WHERE DATE(date) = DATE(NOW())), " +
                "        0 " +
                "    ) AS Appro_Entrant, " +
                "    COALESCE(  " +
                "        (SELECT SUM(CASE WHEN type IN ('Approvisionnement_caisse_entrant','Approvisionnement_compte_virtuel_sortant') AND compte_caisse1='Ecobank' THEN -montant ELSE 0 END) FROM approvisionnement WHERE DATE(date) = DATE(NOW())), " +
                "        0  " +
                "    ) AS Appro_Sortant " +
                "UNION ALL " +

                "SELECT 'Askia' AS service, " +
                "    COALESCE(SUM(CASE WHEN nature_operation = 'Encaissement_Assurance' THEN montant ELSE 0 END), 0) AS montant_depot, " +
                "    COALESCE(SUM(CASE WHEN nature_operation = 'Decaissement_Assurance' THEN montant ELSE 0 END), 0) AS montant_retrait, " +
                "    COALESCE(COUNT(DISTINCT service), 0) as nombre_de_client, " +
                "    0 AS Appro_Entrant, " +
                "    0 AS Appro_Sortant " +
                "FROM operations WHERE service = 'Askia' AND DATE(date) = DATE(NOW()) " +

                "UNION ALL " +

                "SELECT 'Sunu Assurance' AS service, " +
                "    COALESCE(SUM(CASE WHEN nature_operation = 'Encaissement_Assurance' THEN montant ELSE 0 END), 0) AS montant_depot, " +
                "    COALESCE(SUM(CASE WHEN nature_operation = 'Decaissement_Assurance' THEN montant ELSE 0 END), 0) AS montant_retrait, " +
                "    COALESCE(COUNT(DISTINCT service), 0) as nombre_de_client, " +
                "    0 AS Appro_Entrant, " +
                "    0 AS Appro_Sortant " +
                "FROM operations WHERE service = 'Sunu_Assurance' AND DATE(date) = DATE(NOW()) " +

                "UNION ALL " +
                "SELECT 'Creation Entreprise' AS service, " +
                "    COALESCE(SUM(CASE WHEN nature_operation = 'Encaissement_Création_Entreprise' THEN montant ELSE 0 END), 0) AS montant_depot, " +
                "    COALESCE(SUM(CASE WHEN nature_operation = 'Decaissement_Création_Entreprise' THEN montant ELSE 0 END), 0) AS montant_retrait, " +
                "    COALESCE(COUNT(DISTINCT service), 0) as nombre_de_client, " +
                "    0 AS Appro_Entrant, " +
                "    0 AS Appro_Sortant " +
                "FROM operations WHERE service = 'Creation_Entreprise' AND DATE(date) = DATE(NOW()) " +

                "UNION ALL " +

                "SELECT 'CartesCom Imp_Exp' AS service, " +
                "    COALESCE(SUM(CASE WHEN nature_operation = 'Encaissement_Carte' THEN montant ELSE 0 END), 0) AS montant_depot, " +
                "    COALESCE(SUM(CASE WHEN nature_operation = 'Decaissement_Carte' THEN montant ELSE 0 END), 0) AS montant_retrait, " +
                "    COALESCE(COUNT(DISTINCT service), 0) as nombre_de_client, " +
                "    0 AS Appro_Entrant, " +
                "    0 AS Appro_Sortant " +
                "FROM operations WHERE service = 'Cartes_Com_Imp_Exp' AND DATE(date) = DATE(NOW()) " +

                "UNION ALL " +

                "SELECT 'Change' AS service, " +
                "    COALESCE(SUM(CASE WHEN devise_recu = 'XOF' THEN montant_recu ELSE 0 END), 0) AS montant_depot, " +
                "    COALESCE(SUM(CASE WHEN devise_remis = 'XOF' THEN montant_remis ELSE 0 END), 0) AS montant_retrait, " +
                "    COALESCE(COUNT(DISTINCT id), 0) as nombre_de_client, " +
                "    0 AS Appro_Entrant, " +
                "    0 AS Appro_Sortant " +
                "FROM change WHERE DATE(date) = DATE(NOW()) AND (devise_recu IN ('XOF') OR devise_remis = 'XOF') " +
                "UNION ALL " +
                "SELECT 'Grossiste BnB' AS service, " +
                "    COALESCE(SUM(montant), 0) AS montant_depot, " +
                "    0 AS montant_retrait, " +
                "    COALESCE(COUNT(DISTINCT id), 0) as nombre_de_client, " +
                "    0 AS Appro_Entrant, " +
                "    0 AS Appro_Sortant " +
                "FROM grossiste WHERE DATE(date) = DATE(NOW()) " +

                "UNION ALL " +

                "SELECT 'BNDE Depot/Retrait' AS service, " +
                "    COALESCE(SUM(CASE WHEN nature_operations = 'Depot' OR nature_operations = 'Dépôt_initial' THEN montant ELSE 0 END), 0) AS montant_depot, " +
                "    COALESCE(SUM(CASE WHEN nature_operations = 'Retrait' THEN montant ELSE 0 END), 0) AS montant_retrait, " +
                "    COUNT(*) AS nombre_de_client, " +
                "    0 AS Appro_Entrant, " +
                "    0 AS Appro_Sortant " +
                "FROM operations_bancaires WHERE banques = 'BNDE' AND DATE(date) = DATE(NOW()) " +
                "AND (nature_operations IN ('Depot', 'Dépôt_initial') OR nature_operations = 'Retrait') " +

                "UNION ALL " +
                "SELECT 'Ouverture Compte' AS service, " +
                "    COALESCE(SUM(CASE WHEN DATE(date) = DATE(NOW()) THEN montant_depot_initial ELSE 0 END), 0) AS montant_depot, " +
                "    COALESCE(SUM(CASE WHEN DATE(date) = DATE(NOW()) THEN montant_depot_initial ELSE 0 END), 0) AS montant_retrait, " +
                "    COALESCE(COUNT(DISTINCT id), 0) AS nombre_de_client, " +
                "    0 AS Appro_Entrant, " +
                "    0 AS Appro_Sortant " +
                "FROM ouverture_compte WHERE DATE(date) = DATE(NOW()) " +


                "UNION ALL " +
                "SELECT 'UBA Carte Prepayée' AS service, " +
                "    COALESCE(SUM(CASE WHEN nature_operations IN ('Achat_Carte_Prépayée', 'Recharge_Carte_Prépayée') THEN montant ELSE 0 END), 0) AS montant_depot, " +
                "    COALESCE(SUM(CASE WHEN nature_operations = 'Décaissement_UBA_CP' THEN montant ELSE 0 END), 0) AS montant_retrait, " +
                "    COUNT(*) AS nombre_de_client, " +
                "    0 AS Appro_Entrant, " +
                "    0 AS Appro_Sortant " +
                "FROM operations_bancaires " +
                "WHERE banques = 'UBA' AND DATE(date) = DATE(NOW()) " +
                "AND (nature_operations IN ('Achat_Carte_Prépayée', 'Recharge_Carte_Prépayée') OR nature_operations = 'Décaissement_UBA_CP') " +

                "UNION ALL " +

                "SELECT 'UBA Depot/Retrait' AS service, " +
                "    COALESCE(SUM(CASE WHEN nature_operations = 'Depot' OR nature_operations = 'Dépôt_initial' THEN montant ELSE 0 END), 0) AS montant_depot, " +
                "    COALESCE(SUM(CASE WHEN nature_operations = 'Retrait' THEN montant ELSE 0 END), 0) AS montant_retrait, " +
                "    COUNT(*) AS nombre_de_client, " +
                "    0 AS Appro_Entrant, " +
                "    0 AS Appro_Sortant " +
                "FROM operations_bancaires " +
                "WHERE banques = 'UBA' AND DATE(date) = DATE(NOW()) " +
                "AND (nature_operations IN ('Depot', 'Dépôt_initial') OR nature_operations = 'Retrait') "
        );

        List<Object[]> results = query.getResultList();
        List<Double> montants = soldeDepartRepository.getMontants(); // Récupérer la liste des montants une seule fois
        List<Double> montants2 = new ArrayList<>(Collections.nCopies(20, 0.0));
        List<Double> commissions=commissionsRepository.getMontantsCom();
        for (int i = 0; i < results.size(); i++) {
            Object[] result = results.get(i);
            Map<String, Object> recap = new HashMap<>();
            recap.put("service", result[0]);
            recap.put("montantDepot", result[1]);
            recap.put("montantRetrait", result[2]);
            recap.put("nombreClients", result[3]);
            recap.put("ApproEntrant", result[4]);
            recap.put("ApproSortant", result[5]);

            if (montants.size()<=10) {
                recap.put("SoldeDepart", montants2.get(i));
            }
            else
            {recap.put("SoldeDepart", montants.get(i));
            }

            recap.put("Commissions",commissions.get(i));

            recaps.add(recap);
            // Associer le solde départ correspondant au récapitulatif

        }



        return recaps;

    }
}

