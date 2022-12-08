package com.company.app.repositories;

import com.company.app.models.User;

public interface UserRepository extends CrudRepository<User> {
    User findByUsername(String username);
}
