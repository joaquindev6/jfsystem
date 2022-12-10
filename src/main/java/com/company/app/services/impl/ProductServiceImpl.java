package com.company.app.services.impl;

import com.company.app.annotations.Service;
import com.company.app.annotations.TransactionalJpa;
import com.company.app.models.CategoryProduct;
import com.company.app.models.MarkProduct;
import com.company.app.models.Product;
import com.company.app.models.UnidadMedida;
import com.company.app.repositories.CategoryProductRepository;
import com.company.app.repositories.MarkProductRepository;
import com.company.app.repositories.ProductRepository;
import com.company.app.repositories.UnidadMedidaRepository;
import com.company.app.services.ProductService;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@Service
@TransactionalJpa
public class ProductServiceImpl implements ProductService {

    @Inject
    private ProductRepository productRepository;

    @Inject
    private UnidadMedidaRepository unidadMedidaRepository;

    @Inject
    private MarkProductRepository markRepository;

    @Inject
    private CategoryProductRepository categoryRepository;

    @Override
    public List<Product> findAllProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public Optional<Product> findByIdProduct(Long id) {
        return Optional.ofNullable(this.productRepository.findById(id));
    }

    @Override
    public void saveProduct(Product product) {
        this.productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        this.productRepository.delete(id);
    }

    @Override
    public List<UnidadMedida> findAllUnidMeds() {
        return this.unidadMedidaRepository.findAll();
    }

    @Override
    public Optional<UnidadMedida> findByIdUnidMeds(Long id) {
        return Optional.ofNullable(this.unidadMedidaRepository.findById(id));
    }

    @Override
    public List<CategoryProduct> findAllCategories() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Optional<CategoryProduct> findByIdCategory(Long id) {
        return Optional.ofNullable(this.categoryRepository.findById(id));
    }

    @Override
    public void saveCategory(CategoryProduct categoryProduct) {
           this.categoryRepository.save(categoryProduct);
    }

    @Override
    public void deleteCategory(Long id) {
        this.categoryRepository.delete(id);
    }

    @Override
    public List<MarkProduct> findAllMarks() {
        return this.markRepository.findAll();
    }

    @Override
    public Optional<MarkProduct> findByIdMark(Long id) {
        return Optional.ofNullable(this.markRepository.findById(id));
    }

    @Override
    public void saveMark(MarkProduct markProduct) {
        this.markRepository.save(markProduct);
    }

    @Override
    public void deleteMark(Long id) {
        this.markRepository.delete(id);
    }
}
