package com.company.app.services;

import com.company.app.models.CategoryProduct;
import com.company.app.models.MarkProduct;
import com.company.app.models.Product;
import com.company.app.models.UnidadMedida;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAllProducts();
    Optional<Product> findByIdProduct(Long id);
    void saveProduct(Product product);
    void deleteProduct(Long id);

    List<UnidadMedida> findAllUnidMeds();
    Optional<UnidadMedida> findByIdUnidMeds(Long id);

    List<CategoryProduct> findAllCategories();
    Optional<CategoryProduct> findByIdCategory(Long id);
    void saveCategory(CategoryProduct categoryProduct);
    void deleteCategory(Long id);

    List<MarkProduct> findAllMarks();
    Optional<MarkProduct> findByIdMark(Long id);
    void saveMark(MarkProduct markProduct);
    void deleteMark(Long id);
}
