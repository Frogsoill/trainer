package ru.spbu.frogsoill.gui.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import ru.spbu.frogsoill.spring.hibernate.config.DbConfig;

@Configuration
@Import(DbConfig.class)
@ComponentScan(basePackages = "ru.spbu.frogsoill")
@PropertySource("jdbc.properties")
public class SpringConfig {
}