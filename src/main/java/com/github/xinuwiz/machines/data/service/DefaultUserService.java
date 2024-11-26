package com.github.xinuwiz.machines.data.service;

import com.github.xinuwiz.machines.data.cache.UserCache;
import com.github.xinuwiz.machines.data.model.User;
import com.github.xinuwiz.machines.data.repository.UserRepository;
import com.github.xinuwiz.sql.provider.SessionFactory;
import lombok.Getter;

import java.util.Set;

@Getter public class DefaultUserService implements UserService {

    private final UserRepository repository;
    private final UserCache cache;

    public DefaultUserService(SessionFactory factory) {
        this.repository = UserRepository.newDefault(factory);
        this.cache = UserCache.newDefault();
    }

    @Override
    public void save(User user) {
        repository.save(user);
        cache.put(user);
    }

    @Override
    public User getOne(String identifier) {
        return cache.getOne(identifier).orElse(
            repository.getOne(identifier).orElse(null)
        );
    }

    @Override
    public Set<User> getAll() {
        return repository.getAll();
    }

    @Override
    public void remove(User user) {
        repository.remove(user);
        cache.remove(user);
    }
}
