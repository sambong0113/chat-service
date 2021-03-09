package com.example.chatservice;

import dev.miku.r2dbc.mysql.MySqlConnectionConfiguration;
import dev.miku.r2dbc.mysql.MySqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import reactor.core.publisher.Mono;

@Configuration
@EnableR2dbcRepositories
public class DatabaseConfiguration extends AbstractR2dbcConfiguration {

    @Value("${spring.r2dbc.mysql.host}")
    String host;

    @Value("${spring.r2dbc.mysql.port}")
    Integer port;

    @Value("${spring.r2dbc.mysql.password}")
    String password;

    @Value("${spring.r2dbc.mysql.database}")
    String database;

    @Value("${spring.r2dbc.mysql.username}")
    String username;

    @Override
    @Bean
    public ConnectionFactory connectionFactory() {
        return MySqlConnectionFactory.from(MySqlConnectionConfiguration.builder()
                .host(host)
                .password(password)
                .port(port)
                .username(username)
                .database(database)
                .build()
        );
    }
}
