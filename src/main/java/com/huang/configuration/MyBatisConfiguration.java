package com.huang.configuration;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.huang.annotation.MasterRepository;
import com.huang.annotation.SlaveRepository;
import com.huang.config.Contants;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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

    @Bean(name = "masterSqlSessionFactory")
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(DataSourceProperties dataSourceProperties) {
        return getSqlSessionFactory(getDataSource(dataSourceProperties,true));
    }

    @Bean(name = "slaveSqlSessionFactory")
    public SqlSessionFactory slaveSqlSessionFactory(DataSourceProperties dataSourceProperties) {
        return getSqlSessionFactory(getDataSource(dataSourceProperties,false));
    }

    @Bean(name = "masterMapperScannerConfigurer")
    @Primary
    public MapperScannerConfigurer masterMapperScannerConfigurer() {
        return getMapperScannerConfigurer("masterSqlSessionFactory", MasterRepository.class);
    }

    @Bean(name = "slaveMapperScannerConfigurer")
    public MapperScannerConfigurer slaveMapperScannerConfigurer() {
        return getMapperScannerConfigurer("slaveSqlSessionFactory", SlaveRepository.class);
    }

    public DataSource getDataSource(DataSourceProperties dataSourceProperties, boolean isMaster) {
        Properties prop = new Properties();
        prop.setProperty("driverClassName", "com.mysql.jdbc.Driver");
        if (isMaster) {
            prop.setProperty("url", dataSourceProperties.getMasterUrl());
            prop.setProperty("username", dataSourceProperties.getMasterUsername());
            prop.setProperty("password", dataSourceProperties.getMasterPassword());
        } else {
            prop.setProperty("url", dataSourceProperties.getSlaveUrl());
            prop.setProperty("username", dataSourceProperties.getSlaveUsername());
            prop.setProperty("password", dataSourceProperties.getSlavePassword());
        }
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
