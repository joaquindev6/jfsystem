package com.company.app.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MysqlEntityManager {

    private static EntityManagerFactory emf = buildEntityManagerFactory();

    private static EntityManagerFactory buildEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("jpamysql");
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
