package com.company.app.repositories.impl;

import com.company.app.models.UnidadMedida;
import com.company.app.repositories.UnidadMedidaRepository;
import jakarta.persistence.EntityManager;

import java.util.List;

public class UnidadMedidaRepositoryImpl implements UnidadMedidaRepository {

    private EntityManager em;

    public UnidadMedidaRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<UnidadMedida> findAll() {
        return this.em.createQuery("select u from UnidadMedida u", UnidadMedida.class).getResultList();
    }

    @Override
    public UnidadMedida findById(Long id) {
        return this.em.find(UnidadMedida.class, id);
    }

    @Override
    public void save(UnidadMedida unidadMedida) {

    }

    @Override
    public void delete(Long id) {

    }
}
