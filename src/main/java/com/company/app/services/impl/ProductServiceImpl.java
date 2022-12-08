package com.company.app.services.impl;

import com.company.app.models.CategoryProduct;
import com.company.app.models.MarkProduct;
import com.company.app.models.Product;
import com.company.app.models.UnidadMedida;
import com.company.app.repositories.CategoryProductRepository;
import com.company.app.repositories.MarkProductRepository;
import com.company.app.repositories.ProductRepository;
import com.company.app.repositories.UnidadMedidaRepository;
import com.company.app.repositories.impl.CategoryProductRepositoryImpl;
import com.company.app.repositories.impl.MarkProductRepositoryImpl;
import com.company.app.repositories.impl.ProductRepositoryImpl;
import com.company.app.repositories.impl.UnidadMedidaRepositoryImpl;
import com.company.app.services.ProductService;
import com.company.app.util.MysqlEntityManager;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {

    private EntityManager em;
    private ProductRepository productRepository;
    private UnidadMedidaRepository unidadMedidaRepository;
    private MarkProductRepository markRepository;
    private CategoryProductRepository categoryRepository;

    public ProductServiceImpl() {
        this.em = MysqlEntityManager.getEntityManager();
        this.productRepository = new ProductRepositoryImpl(this.em);
        this.unidadMedidaRepository = new UnidadMedidaRepositoryImpl(this.em);
        this.markRepository = new MarkProductRepositoryImpl(this.em);
        this.categoryRepository = new CategoryProductRepositoryImpl(this.em);
    }

    @Override
    public List<Product> findAllProducts() {
        this.em.clear();
        return this.productRepository.findAll();
    }

    @Override
    public Optional<Product> findByIdProduct(Long id) {
        this.em.clear();
        return Optional.ofNullable(this.productRepository.findById(id));
    }

    @Override
    public void saveProduct(Product product) {
        try {
            this.em.getTransaction().begin();
            this.productRepository.save(product);
            this.em.getTransaction().commit();
        } catch (Exception ex) {
            this.em.getTransaction().rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteProduct(Long id) {
        try {
            this.em.getTransaction().begin();
            this.productRepository.delete(id);
            this.em.getTransaction().commit();
        } catch (Exception ex) {
            this.em.getTransaction().rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public List<UnidadMedida> findAllUnidMeds() {
        this.em.clear();
        return this.unidadMedidaRepository.findAll();
    }

    @Override
    public Optional<UnidadMedida> findByIdUnidMeds(Long id) {
        this.em.clear();
        return Optional.ofNullable(this.unidadMedidaRepository.findById(id));
    }

    @Override
    public List<CategoryProduct> findAllCategories() {
        this.em.clear();
        return this.categoryRepository.findAll();
    }

    @Override
    public Optional<CategoryProduct> findByIdCategory(Long id) {
        this.em.clear();
        return Optional.ofNullable(this.categoryRepository.findById(id));
    }

    @Override
    public void saveCategory(CategoryProduct categoryProduct) {
        try {
            this.em.getTransaction().begin();
            this.categoryRepository.save(categoryProduct);
            this.em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteCategory(Long id) {
        try {
            this.em.getTransaction().begin();
            this.categoryRepository.delete(id);
            this.em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<MarkProduct> findAllMarks() {
        this.em.clear();
        return this.markRepository.findAll();
    }

    @Override
    public Optional<MarkProduct> findByIdMark(Long id) {
        this.em.clear();
        return Optional.ofNullable(this.markRepository.findById(id));
    }

    @Override
    public void saveMark(MarkProduct markProduct) {
        try {
            this.em.getTransaction().begin();
            this.markRepository.save(markProduct);
            this.em.getTransaction().commit();
        } catch (Exception ex) {
            this.em.getTransaction().rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteMark(Long id) {
        try {
            this.em.getTransaction().begin();
            this.markRepository.delete(id);
            this.em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
