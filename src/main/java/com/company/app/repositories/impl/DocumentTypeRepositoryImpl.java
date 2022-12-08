package com.company.app.repositories.impl;

import com.company.app.models.DocumentType;
import com.company.app.repositories.DocumentTypeRepository;
import jakarta.persistence.EntityManager;

import java.util.List;

public class DocumentTypeRepositoryImpl implements DocumentTypeRepository {

    private EntityManager em;

    public DocumentTypeRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<DocumentType> findAll() {
        List<DocumentType> list = this.em.createQuery("select d from DocumentType d", DocumentType.class).getResultList();
        return list;
    }

    @Override
    public DocumentType findById(Long id) {
        return this.em.find(DocumentType.class, id);
    }

    @Override
    public void save(DocumentType documentType) {

    }

    @Override
    public void delete(Long id) {

    }
}
