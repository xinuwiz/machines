package com.github.xinuwiz.machines.data.cache;

import com.github.xinuwiz.machines.data.model.User;

import java.util.*;

public class DefaultUserCache implements UserCache {

    private final Map<String, User> cache;

    public DefaultUserCache() {
        this.cache = new HashMap<>();
    }

    @Override
    public void put(User user) {
        final String identifier = user.getId();
        cache.put(identifier, user);
    }

    @Override
    public Optional<User> getOne(String identifier) {
        final User user = cache.get(identifier);
        return Optional.ofNullable(user);
    }

    @Override
    public Set<User> getAll() {
        final Collection<User> values = cache.values();
        return new HashSet<>(values);
    }

    @Override
    public void remove(User user) {
        final String identifier = user.getId();
        cache.remove(identifier);
    }
}
