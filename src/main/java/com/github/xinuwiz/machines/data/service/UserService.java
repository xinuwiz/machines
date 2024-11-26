package com.github.xinuwiz.machines.data.service;

import com.github.xinuwiz.machines.data.model.User;
import com.github.xinuwiz.sql.provider.SessionFactory;

import java.util.Optional;
import java.util.Set;

public interface UserService {

    void save(User user);

    User getOne(String identifier);

    Set<User> getAll();

    void remove(User user);

    static UserService newDefault(SessionFactory factory) {
        return new DefaultUserService(factory);
    }
}
