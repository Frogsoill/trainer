package ru.spbu.frogsoill.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.spbu.frogsoill.Application;

@Configuration
@ComponentScan(basePackageClasses = Application.class)
public class SpringConfig {
}
