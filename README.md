## 使用的技术点
1. springboot
2. apollo
3. logback
4. eurake
5. redis
6. mysql master-slave
7. rocketmq-consumer producter
8. log aop
9. Hibernate Validator

## spring-boot

* Configuration
* EnableAutoConfiguration
* ComponentScan
* SpringBootApplication
* RestController
* RequestMapping

* ConfigurationProperties(prefix = "spring.redis")


## apollo

* apollo-env.properties
* -Denv=dev
* EnableApolloConfig

## logback

* logbak.xml

## eurake

### provider

* EnableDiscoveryClient
```
spring.application.name = WEBPLATFORM
eureka.client.serviceUrl.defaultZone = http://ip:port/eureka/
```

### consumer

## redis

* StringRedisTemplate
```
spring.redis.host = localhost:3306
spring.redis.port = 6379
spring.redis.password = 123456
spring.redis.database = 0
```

## mysql

* datasource
```
    * spring.datasource.url = jdbc:mysql://localhost:3306/webplatform?useUnicode=true&characterEncoding=utf-8
    * spring.datasource.username = root
    * spring.datasource.password = root
    * spring.datasource.driverClassName = com.mysql.jdbc.Driver
```
* mybatis-spring-boot-starter
    * mybatis.mapperLocations = classpath:mapping/*.xml

## rocketmq

* consumer
* producter

## log aop

* @Aspect
* 自定义注解
* 通过注解作切入点 around切面


## Hibernate Validator
