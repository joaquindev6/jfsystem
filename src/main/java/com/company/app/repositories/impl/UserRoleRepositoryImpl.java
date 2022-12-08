package com.company.app.repositories.impl;

import com.company.app.models.UserRole;
import com.company.app.repositories.UserRoleRepository;
import jakarta.persistence.EntityManager;

import java.util.List;

public class UserRoleRepositoryImpl implements UserRoleRepository {

    private EntityManager em;

    public UserRoleRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<UserRole> findAll() {
        return this.em.createQuery("select r from UserRole r", UserRole.class).getResultList();
    }

    @Override
    public UserRole findById(Long id) {
        return this.em.find(UserRole.class, id);
    }

    @Override
    public void save(UserRole userRole) {

    }

    @Override
    public void delete(Long id) {

    }
}
