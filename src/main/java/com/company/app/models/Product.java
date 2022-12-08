package com.company.app.models;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_productos")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_unidad_medida")
    private UnidadMedida unidMed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_marca")
    private MarkProduct markProduct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria")
    private CategoryProduct categoryProduct;

    private int stock;
    private double precio;
    private String fecharegi;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public UnidadMedida getUnidMed() {
        return unidMed;
    }

    public void setUnidMed(UnidadMedida unidMed) {
        this.unidMed = unidMed;
    }

    public MarkProduct getMarkProduct() {
        return markProduct;
    }

    public void setMarkProduct(MarkProduct markProduct) {
        this.markProduct = markProduct;
    }

    public CategoryProduct getCategoryProduct() {
        return categoryProduct;
    }

    public void setCategoryProduct(CategoryProduct categoryProduct) {
        this.categoryProduct = categoryProduct;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getFecharegi() {
        return fecharegi;
    }

    public void setFecharegi(String fecharegi) {
        this.fecharegi = fecharegi;
    }
}
