package com.example.schoolmanager.config;

import javax.sql.DataSource;

import jakarta.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "com.example.schoolmanager.respository.online",
    entityManagerFactoryRef = "onlineEntityManagerFactory",
    transactionManagerRef = "onlineTransactionManager"
)
public class OnlineDataSourceConfig {

    @Bean(name = "onlineDataSource")
    @ConfigurationProperties(prefix = "app.datasource.online")
    public DataSource onlineDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "onlineEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean onlineEntityManagerFactory(
        @Qualifier("onlineDataSource") DataSource dataSource
    ) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory =
            new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setPackagesToScan("com.example.schoolmanager.model");
        entityManagerFactory.setPersistenceUnitName("online");
        entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return entityManagerFactory;
    }

    @Bean(name = "onlineTransactionManager")
    public PlatformTransactionManager onlineTransactionManager(
        @Qualifier("onlineEntityManagerFactory") EntityManagerFactory entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
