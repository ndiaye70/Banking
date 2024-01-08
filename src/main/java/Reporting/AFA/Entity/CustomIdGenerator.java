package Reporting.AFA.Entity;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnitUtil;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        if (object instanceof Identifiable) {
            Identifiable entity = (Identifiable) object;

            // Générer un identifiant basé sur le jour, le mois et les trois premières lettres du nom de la table
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("ddMM");
            String formattedDate = currentDate.format(dateFormatter);

            EntityManagerFactory entityManagerFactory = session.getSessionFactory().unwrap(EntityManagerFactory.class);
            PersistenceUnitUtil persistenceUnitUtil = entityManagerFactory.getPersistenceUnitUtil();
            Class<?> entityClass = object.getClass();

            Metamodel metamodel = entityManagerFactory.getMetamodel();
            EntityType<?> entityType = metamodel.entity(entityClass);

            String tableName = entityType.getName();

            String tablePrefix = tableName.length() >= 3 ? tableName.substring(0, 3) : tableName;

            // Combiner le jour, le mois et les trois premières lettres du nom de la table pour former l'identifiant
            String generatedId = formattedDate + tablePrefix;

            // Définir l'identifiant généré sur l'entité
            entity.setId(generatedId);

            return generatedId;
        } else {
            throw new HibernateException("La classe n'est pas prise en charge par ce générateur d'identifiants.");
        }
    }
}
