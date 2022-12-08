package com.company.app.repositories.impl;

import com.company.app.models.DocumentType;
import com.company.app.models.User;
import com.company.app.models.UserRole;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.Test;
import jakarta.persistence.EntityManager;
import java.util.List;

class UserRepositoryImplTest {

    private EntityManager getConnectionMysql() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpamysql");
        return entityManagerFactory.createEntityManager();
    }

    private EntityManager em = getConnectionMysql();

    @Test
    void findAll() {
        List<User> users = this.em.createQuery("select u from User u inner join u.documentType inner join u.userRole", User.class).getResultList();
        users.forEach(u -> {
            System.out.println("Nombre: " + u.getNombres() + ", documento: " + u.getDocumentType().getNombre());
        });
    }

    @Test
    void save() {
        User user = new User();
        user.setNombres("gggg");
        user.setApellidos("dsfsd");
        user.setEmail("sdfssss");
        user.setNrodocu("bbbb");
        user.setUsername("zzzz");
        user.setPassword("sdfsd");

        DocumentType documentType = new DocumentType();
        documentType.setId(1L);
        user.setDocumentType(documentType);

        UserRole role = new UserRole();
        role.setId(1L);
        user.setUserRole(role);

        this.em.persist(role);
    }
}