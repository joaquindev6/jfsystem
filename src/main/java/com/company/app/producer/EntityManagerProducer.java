package com.company.app.producer;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceUnit;

//Productor de EntityManager

/*@ApplicationScoped
public class EntityManagerProducer {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @PostConstruct
    public void init() {
        if (emf == null) {
            emf  = Persistence.createEntityManagerFactory("jpamysql");
        }
    }

    //Creador
    @Produces
    @Default
    @Dependent //Respeta el ciclo de vida, tanto si pertenece a un singleton o a un request
    public EntityManager entityManager() {
        return emf.createEntityManager();
    }

    //Destructor
    public void close(@Disposes EntityManager em) { //Cuando se disponga a cerrar la conexion primero verifica si esta abierta la conexion
        if (em.isOpen()) {
            em.close();
        }
    }
}*/
