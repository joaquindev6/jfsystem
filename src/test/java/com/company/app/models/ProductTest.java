package com.company.app.models;

import com.company.app.util.MysqlEntityManager;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private EntityManager em = MysqlEntityManager.getEntityManager();

    @Test
    void lisetProducts() {
        User user = this.em.createQuery("select u from User u where u.username = ?1", User.class)
                .setParameter(1, "dsfsd")
                .getSingleResult();
        System.out.println(user.getNombres());
    }
}