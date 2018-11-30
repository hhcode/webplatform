package com.huang.configuration;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.huang.annotation.MasterRepository;
import com.huang.annotation.SlaveRepository;
import com.huang.config.Contants;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * mybatis 初始化类
 *
 * @Author huangjihui
 * @Date 2018/11/29 16:49
 */
@Slf4j
@Configuration
public class MyBatisConfiguration {

    @Bean(name = "masterDataSourceProperties")
    @ConfigurationProperties(prefix = "mysql.datasource.master")
    public DataSourceProperties masterDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "slaveDataSourceProperties")
    @ConfigurationProperties(prefix = "mysql.datasource.slave")
    public DataSourceProperties slaveDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean("masterDataSource")
    public DataSource masterDataSource(DataSourceProperties masterDataSourceProperties) {
        return getDataSource(masterDataSourceProperties);
    }

    @Bean("slaveDataSource")
    public DataSource slaveDataSource(DataSourceProperties slaveDataSourceProperties) {
        return getDataSource(slaveDataSourceProperties);
    }


    @Bean(name = "masterSqlSessionFactory")
    public SqlSessionFactory masterSqlSessionFactory(DataSource masterDataSource) {
        return getSqlSessionFactory(masterDataSource);
    }

    @Bean(name = "slaveSqlSessionFactorySlave")
    public SqlSessionFactory slaveSqlSessionFactory(DataSource slaveDataSource) {
        return getSqlSessionFactory(slaveDataSource);
    }

    @Bean
    @DependsOn(value = "masterMapperScannerConfigurer")
    public MapperScannerConfigurer masterMapperScannerConfigurer() {
        return getMapperScannerConfigurer("sqlSessionFactoryMaster", MasterRepository.class);
    }

    @Bean
    @DependsOn(value = "slaveMapperScannerConfigurer")
    public MapperScannerConfigurer slaveMapperScannerConfigurer() {
        return getMapperScannerConfigurer("sqlSessionFactorySlave", SlaveRepository.class);
    }

    public DataSource getDataSource(DataSourceProperties dataSourceProperties) {
        Properties prop = new Properties();
        prop.setProperty("driverClassName", "com.mysql.jdbc.Driver");
        prop.setProperty("url", dataSourceProperties.getUrl());
        prop.setProperty("username", dataSourceProperties.getUsername());
        prop.setProperty("password", dataSourceProperties.getPassword());

        try {
            return DruidDataSourceFactory.createDataSource(prop);
        } catch (Exception e) {
            log.error("create datasource error", e);
            throw new RuntimeException(e);
        }
    }

    public SqlSessionFactory getSqlSessionFactory(DataSource dataSource) {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);

        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            factoryBean.setMapperLocations(resolver.getResources(Contants.mybatisLocationResource));
            return factoryBean.getObject();
        } catch (Exception e) {
            log.error("sqlSessionFactor init error", e);
            throw new RuntimeException(e);
        }
    }

    public MapperScannerConfigurer getMapperScannerConfigurer(String sessionFactoryBeanName, Class reponsitoryClazz) {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setSqlSessionFactoryBeanName(sessionFactoryBeanName);
        configurer.setBasePackage(Contants.mybatisBasePackage);
        configurer.setAnnotationClass(reponsitoryClazz);
        return configurer;
    }
}
