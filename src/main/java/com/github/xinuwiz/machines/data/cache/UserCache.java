package com.github.xinuwiz.machines.data.cache;

import com.github.xinuwiz.machines.data.model.User;

import java.util.Collection;
import java.util.Optional;

public interface UserCache {

    void put(User user);

    Optional<User> getOne(String identifier);

    Collection<User> getAll();

    void remove(User user);

    static UserCache newDefault() {
        return new DefaultUserCache();
    }
}
