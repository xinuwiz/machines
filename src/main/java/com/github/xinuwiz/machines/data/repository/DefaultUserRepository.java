package com.github.xinuwiz.machines.data.repository;

import com.github.xinuwiz.machines.data.model.DefaultUser;
import com.github.xinuwiz.machines.data.model.User;
import com.github.xinuwiz.sql.provider.Session;
import com.github.xinuwiz.sql.provider.SessionFactory;
import lombok.Data;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Data
public class DefaultUserRepository implements UserRepository {

    private final SessionFactory sessionFactory;

    @Override
    public void init() {
        Session session = sessionFactory.open();
        session.execute("CREATE TABLE IF NOT EXISTS users (id VARCHAR(36) PRIMARY KEY, name VARCHAR(16) NOT NULL)");
        session.close();
    }

    @Override
    public void save(User user) {
        Session session = sessionFactory.open();
        session.execute("INSERT INTO users (id, name) VALUES (?, ?)", statement -> {
            statement.set(1, user.getId());
            statement.set(2, user.getName());
        });
        session.close();
    }

    @Override
    public Optional<User> getOne(String identifier) {
        Session session = sessionFactory.open();
        Optional<User> user = session.query("SELECT * FROM users WHERE id = ?",
            statement -> {
                statement.set(1, identifier);
            },
            result -> {
                final String name = result.get(1, String.class);
                final User defaultUser = new DefaultUser(identifier, name);
                return Optional.of(defaultUser);
            });
        session.close();
        return user;
    }

    @Override
    public Set<User> getAll() {
        Session session = sessionFactory.open();
        Set<User> users = session.queryMany("SELECT * FROM users",
            result -> {
                final String id = result.get(1, String.class);
                final String name = result.get(2, String.class);
                return new DefaultUser(id, name);
            });
        session.close();
        return Collections.unmodifiableSet(users);
    }

    @Override
    public void remove(User user) {
        Session session = sessionFactory.open();
        session.execute("DELETE FROM users WHERE id = ?", statement -> {
            statement.set(1, user.getId());
        });
        session.close();
    }
}
