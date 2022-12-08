package com.company.app.repositories.impl;

import com.company.app.models.CategoryProduct;
import com.company.app.repositories.CategoryProductRepository;
import jakarta.persistence.EntityManager;

import java.util.List;

public class CategoryProductRepositoryImpl implements CategoryProductRepository {

    private EntityManager em;

    public CategoryProductRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<CategoryProduct> findAll() {
        return this.em.createQuery("select c from CategoryProduct c", CategoryProduct.class).getResultList();
    }

    @Override
    public CategoryProduct findById(Long id) {
        CategoryProduct categoryProduct = null;
        if (id > 0) {
            categoryProduct = this.em.find(CategoryProduct.class, id);
        }
        return categoryProduct;
    }

    @Override
    public void save(CategoryProduct categoryProduct) {
        if (categoryProduct.getId() != null && categoryProduct.getId() > 0) {
            this.em.merge(categoryProduct);
        } else {
            this.em.persist(categoryProduct);
        }
    }

    @Override
    public void delete(Long id) {
        if (id > 0) {
            CategoryProduct categoryProduct = findById(id);
            if (categoryProduct != null) {
                this.em.remove(categoryProduct);
            }
        }
    }
}
