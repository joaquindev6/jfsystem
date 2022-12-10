package com.company.app.repositories.impl;

import com.company.app.annotations.Repository;
import com.company.app.models.User;
import com.company.app.repositories.UserRepository;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Inject
    private EntityManager em;

    @Override
    public List<User> findAll() {
        return this.em.createQuery("select u from User u inner join u.documentType inner join u.userRole", User.class).getResultList();
    }

    @Override
    public User findById(Long id)  {
        return this.em.find(User.class, id);
    }

    @Override
    public void save(User user) {
        if (user.getId() != null && user.getId() > 0) {
            this.em.merge(user);
        } else {
            this.em.persist(user);
        }
    }

    @Override
    public void delete(Long id) {
        User user = findById(id);
        if (user != null) {
            this.em.remove(user);
        }
    }

    @Override
    public User findByUsername(String username) {
        try {
            return this.em.createQuery("select u from User u where u.username = ?1", User.class)
                    .setParameter(1, username)
                    .getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }
}
