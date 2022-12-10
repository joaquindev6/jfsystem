package com.company.app.producer;

import com.company.app.util.MysqlEntityManager;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class ProducerResources {

    @Produces
    @RequestScoped
    private EntityManager beanEntityManager() {
        return MysqlEntityManager.getEntityManager();
    }

    public void close(@Disposes EntityManager entityManager) {
        if (entityManager.isOpen()) {
            entityManager.close();
        }
    }
}
