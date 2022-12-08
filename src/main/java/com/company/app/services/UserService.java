package com.company.app.services;

import com.company.app.models.DocumentType;
import com.company.app.models.User;
import com.company.app.models.UserRole;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAllUsers();
    Optional<User> findByIdUser(Long id);
    Optional<User> findByUsername(String username);
    void saveUser(User user);
    void deleteUser(Long id);

    List<DocumentType> findAllTypeDocu();
    Optional<DocumentType> findByIdTypeDocu(Long id);

    List<UserRole> findAllRoles();
    Optional<UserRole> findByIdRole(Long id);
}
