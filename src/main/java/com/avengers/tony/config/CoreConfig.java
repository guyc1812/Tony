package com.avengers.tony.config;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import java.net.UnknownHostException;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com")
@PropertySource(value = {
        "classpath:application.properties",
        "classpath:server.properties",
        "classpath:db.properties",
        "classpath:log4j.properties"})
public class CoreConfig {

    @Bean
    public MongoClient MongoClient(Environment environment) throws UnknownHostException {
        final String mongoUrl = environment.getProperty("mongo.url");
        final String mongoPort = environment.getProperty("mongo.port");
        return new MongoClient(mongoUrl, Integer.parseInt(mongoPort));
    }

}
