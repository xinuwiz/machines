package com.github.xinuwiz.machines.data.repository;

import com.github.xinuwiz.machines.data.model.User;
import com.github.xinuwiz.sql.provider.SessionFactory;

import java.util.Optional;
import java.util.Set;

public interface UserRepository {

    void init();

    void save(User user);

    Optional<User> getOne(String identifier);

    Set<User> getAll();

    void remove(User user);

    static UserRepository newDefault(SessionFactory factory) {
        return new DefaultUserRepository(factory);
    }
}