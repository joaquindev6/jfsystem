package com.company.app.repositories.impl;

import com.company.app.models.Product;
import com.company.app.repositories.ProductRepository;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {

    private EntityManager em;

    public ProductRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Product> findAll() {
        return this.em.createQuery("select p from Product p", Product.class).getResultList();
    }

    @Override
    public Product findById(Long id) {
        Product product = null;
        if (id > 0) {
            product = this.em.find(Product.class, id);
        }
        return product;
    }

    @Override
    public void save(Product product) {
        if (product.getId() != null && product.getId() > 0) {
            this.em.merge(product);
        } else {
            this.em.persist(product);
        }
    }

    @Override
    public void delete(Long id) {
        if (id > 0) {
            Product product = findById(id);
            if (product != null) {
                this.em.remove(product);
            }
        }
    }
}
