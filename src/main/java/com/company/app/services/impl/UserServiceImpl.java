package com.company.app.services.impl;

import com.company.app.annotations.Service;
import com.company.app.annotations.TransactionalJpa;
import com.company.app.models.DocumentType;
import com.company.app.models.User;
import com.company.app.models.UserRole;
import com.company.app.repositories.DocumentTypeRepository;
import com.company.app.repositories.UserRepository;
import com.company.app.repositories.UserRoleRepository;
import com.company.app.services.UserService;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@Service
@TransactionalJpa
public class UserServiceImpl implements UserService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private DocumentTypeRepository documentTypeRepository;

    @Inject
    private UserRoleRepository userRoleRepository;

    @Override
    public List<User> findAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public Optional<User> findByIdUser(Long id) {
        return Optional.ofNullable(this.userRepository.findById(id));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(this.userRepository.findByUsername(username));
    }

    @Override
    public void saveUser(User user) {
        this.userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        this.userRepository.delete(id);
    }

    @Override
    public List<DocumentType> findAllTypeDocu() {
        return this.documentTypeRepository.findAll();
    }

    @Override
    public Optional<DocumentType> findByIdTypeDocu(Long id) {
        return Optional.ofNullable(this.documentTypeRepository.findById(id));
    }

    @Override
    public List<UserRole> findAllRoles() {
        return this.userRoleRepository.findAll();
    }

    @Override
    public Optional<UserRole> findByIdRole(Long id) {
        return Optional.ofNullable(this.userRoleRepository.findById(id));
    }
}
