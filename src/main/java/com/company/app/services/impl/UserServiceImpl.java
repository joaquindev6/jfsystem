package com.company.app.services.impl;

import com.company.app.models.DocumentType;
import com.company.app.models.User;
import com.company.app.models.UserRole;
import com.company.app.repositories.DocumentTypeRepository;
import com.company.app.repositories.UserRepository;
import com.company.app.repositories.UserRoleRepository;
import com.company.app.repositories.impl.DocumentTypeRepositoryImpl;
import com.company.app.repositories.impl.UserRepositoryImpl;
import com.company.app.repositories.impl.UserRoleRepositoryImpl;
import com.company.app.services.UserService;
import com.company.app.util.MysqlEntityManager;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private EntityManager em;
    private UserRepository userRepository;
    private DocumentTypeRepository documentTypeRepository;
    private UserRoleRepository userRoleRepository;

    public UserServiceImpl() {
        this.em = MysqlEntityManager.getEntityManager();
        this.userRepository = new UserRepositoryImpl(em);
        this.documentTypeRepository = new DocumentTypeRepositoryImpl(em);
        this.userRoleRepository = new UserRoleRepositoryImpl(em);
    }

    @Override
    public List<User> findAllUsers() {
        this.em.clear();
        return this.userRepository.findAll();
    }

    @Override
    public Optional<User> findByIdUser(Long id) {
        this.em.clear();
        return Optional.ofNullable(this.userRepository.findById(id));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        this.em.clear();
        return Optional.ofNullable(this.userRepository.findByUsername(username));
    }

    @Override
    public void saveUser(User user) {
        try {
            this.em.getTransaction().begin();
            this.userRepository.save(user);
            this.em.getTransaction().commit();
        } catch (Exception ex) {
            this.em.getTransaction().rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteUser(Long id) {
        try {
            this.em.getTransaction().begin();
            this.userRepository.delete(id);
            this.em.getTransaction().commit();
        } catch (Exception ex) {
            this.em.getTransaction().rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public List<DocumentType> findAllTypeDocu() {
        this.em.clear();
        return this.documentTypeRepository.findAll();
    }

    @Override
    public Optional<DocumentType> findByIdTypeDocu(Long id) {
        return Optional.ofNullable(this.documentTypeRepository.findById(id));
    }

    @Override
    public List<UserRole> findAllRoles() {
        this.em.clear();
        return this.userRoleRepository.findAll();
    }

    @Override
    public Optional<UserRole> findByIdRole(Long id) {
        return Optional.ofNullable(this.userRoleRepository.findById(id));
    }
}
