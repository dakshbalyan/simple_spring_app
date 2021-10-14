package org.example.Configuration;

import org.example.beanExample.BasicBean;
import org.hibernate.ejb.HibernatePersistence;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("org.example.repository")
public class ConfigClass {
    @Bean
    public BasicBean basicBean(){
        BasicBean beanObj = new BasicBean();
        beanObj.setName("daksh");
        return beanObj;
    }

    @Bean(name = "connection")
    public Connection getConnection(){
        String url = "jdbc:mysql://127.0.0.1:3306/personal_test";
        String user = "root";
        String dbPassword = "root";
        Connection con;
        try{
            con = DriverManager.getConnection(url, user, dbPassword);
            return con;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Bean
    public Properties properties(){
        Properties properties = new Properties();
        InputStream inputStream = null;
        try{
            inputStream = this.getClass().getClassLoader()
                    .getResourceAsStream("app.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return properties;
    }

    @Bean
    public DriverManagerDataSource dataSource(Properties properties){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/personal_test");
//        dataSource.setUsername("root");
//        dataSource.setPassword("root");

        dataSource.setDriverClassName(properties.getProperty("driverclass"));
        dataSource.setUrl(properties.getProperty("url"));
        dataSource.setUsername(properties.getProperty("user"));
        dataSource.setPassword(properties.getProperty("password"));
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DriverManagerDataSource dataSource){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        return jdbcTemplate;
    }

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean
                = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setDataSource(dataSource(properties()));
        localContainerEntityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        localContainerEntityManagerFactoryBean.setPackagesToScan("org.example.Entities");
        localContainerEntityManagerFactoryBean.setJpaProperties(hibernateProperties());

        return localContainerEntityManagerFactoryBean;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", properties().getProperty("dialect"));
        properties.setProperty("hibernate.show_sql", properties().getProperty("showSql"));
        properties.setProperty("hibernate.ddl-auto", properties().getProperty("ddl-auto"));
        return properties;
    }

    @Bean
    JpaTransactionManager transactionManager(){
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return jpaTransactionManager;
    }

}
