package com.company.app.repositories.impl;

import com.company.app.annotations.Repository;
import com.company.app.models.MarkProduct;
import com.company.app.repositories.MarkProductRepository;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@Repository
public class MarkProductRepositoryImpl implements MarkProductRepository {

    @Inject
    private EntityManager em;

    @Override
    public List<MarkProduct> findAll() {
        return this.em.createQuery("select m from MarkProduct m", MarkProduct.class).getResultList();
    }

    @Override
    public MarkProduct findById(Long id) {
        MarkProduct markProduct = null;
        if (id > 0) {
            markProduct = this.em.find(MarkProduct.class, id);
        }
        return markProduct;
    }

    @Override
    public void save(MarkProduct markProduct) {
        if (markProduct.getId() != null && markProduct.getId() > 0) {
            this.em.merge(markProduct);
        } else {
            this.em.persist(markProduct);
        }
    }

    @Override
    public void delete(Long id) {
        if (id > 0) {
            MarkProduct markProduct = findById(id);
            if (markProduct != null) {
                this.em.remove(markProduct);
            }
        }
    }
}
