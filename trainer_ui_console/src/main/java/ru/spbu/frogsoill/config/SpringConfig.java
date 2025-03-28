package ru.spbu.frogsoill.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.*;
import ru.spbu.frogsoill.Application;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackageClasses = Application.class)
@PropertySource("jdbc.properties")
public class SpringConfig {
    private static final String DB_DRIVER_CLASS_NAME = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:mem:task;DB_CLOSE_DELAY=-1";
    private static final String DB_USER_NAME = "sa";
    private static final String DB_PASSWORD = "";

    @Bean
    @Lazy
    public DataSource dataSource() {
        try {
            BasicDataSource dataSource
                    = new BasicDataSource();
            dataSource.setDriverClassName(DB_DRIVER_CLASS_NAME);
            dataSource.setUrl(DB_URL);
            dataSource.setUsername(DB_USER_NAME);
            dataSource.setPassword(DB_PASSWORD);
            return dataSource;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
