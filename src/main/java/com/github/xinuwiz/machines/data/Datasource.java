package com.github.xinuwiz.machines.data;

import com.github.xinuwiz.machines.data.repository.UserRepository;
import com.github.xinuwiz.sql.provider.SessionFactory;
import com.github.xinuwiz.sql.provider.configuration.Configuration;
import com.github.xinuwiz.sql.provider.configuration.ConfigurationBuilder;
import com.github.xinuwiz.sql.provider.configuration.MysConfiguration;
import lombok.Getter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;

@Getter public final class Datasource {

    private final SessionFactory sessionFactory;

    private final UserRepository userRepository;

    public Datasource(Plugin plugin) {

        final ConfigurationSection section = plugin.getConfig().getConfigurationSection("database");
        final String url = section.getString("url");
        final String username = section.getString("username");
        final String password = section.getString("password");

        final Configuration configuration = ConfigurationBuilder
            .of(MysConfiguration.class).url(url).username(username).password(password).build();

        this.sessionFactory = SessionFactory.newDefault(configuration);

        this.userRepository = UserRepository.newDefault(sessionFactory);
    }
}
