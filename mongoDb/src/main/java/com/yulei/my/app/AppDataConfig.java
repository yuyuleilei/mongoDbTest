package com.yulei.my.app;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import com.zhixindu.commons.repository.DefaultPageRepository;
import com.zhixindu.commons.repository.PageRepository;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.mapping.Mapper;
import org.mongodb.morphia.mapping.MapperOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.inject.Inject;

/**
 * @author Yulei
 * @version 1.0
 * @date 2017/7/4
 * @description
 */
@Configuration
@PropertySource(value = {"classpath:/my.properties"}, ignoreResourceNotFound = true)
@EnableTransactionManagement
public class AppDataConfig {

    //    @Value("${jdbc.driverClassName}")
//    private String driverClassName;
//    @Value("${jdbc.url}")
//    private String url;
//    @Value("${jdbc.username}")
//    private String username;
//    @Value("${jdbc.password}")
//    private String password;
//    @Value("${jdbc.maxPoolSize}")
//    private int maxPoolSize;
//    @Value("${jdbc.minPoolSize}")
//    private int minPoolSize;
//    @Value("${jdbc.initialPoolSize}")
//    private int initialPoolSize;
//    @Value("${jdbc.maxStatements}")
//    private int maxStatements;
//    @Value("${jdbc.maxIdleTime}")
//    private int maxIdleTime;
    @Inject
    private Environment environment;

    @Value("${mongo.database}")
    private String mongoDatabase;
    @Value("${mongo.databaseUrl}")
    private String mongoDatabaseUrl;

    //    @Bean
//    public DataSource dataSource() throws PropertyVetoException {
//        ComboPooledDataSource dataSource = new ComboPooledDataSource();
//        dataSource.setDriverClass(driverClassName);
//        dataSource.setJdbcUrl(url);
//        dataSource.setUser(username);
//        dataSource.setPassword(password);
//        dataSource.setMaxPoolSize(maxPoolSize);
//        dataSource.setMinPoolSize(minPoolSize);
//        dataSource.setInitialPoolSize(initialPoolSize);
//        dataSource.setMaxIdleTime(maxIdleTime);
//        dataSource.setMaxStatements(maxStatements);
//        return dataSource;
//    }
//
//    @Bean
//    public JdbcTemplate jdbcTemplate() throws PropertyVetoException {
//        return new JdbcTemplate(dataSource());
//    }
//
//    @Bean
//    public DataSourceTransactionManager transactionManager() throws PropertyVetoException {
//        return new DataSourceTransactionManager(dataSource());
//    }
//
//    @Bean
//    public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
//        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource());
//        // 分页插件
//        Interceptor paginationInterceptor = new PaginationInterceptor();
//        Properties properties = new Properties();
//        properties.setProperty("stmtIdRegex", "*.*ByPage");
//        properties.setProperty("dialect","mysql");
//        paginationInterceptor.setProperties(properties);
//        sessionFactory.setPlugins(new Interceptor[]{paginationInterceptor});
//        sessionFactory.setTypeAliasesPackage("com.zhixindu.pay.core.domain");
//        return sessionFactory;
//    }
//
//    @Bean
//    public SqlSession sqlSession() throws Exception {
//        return new SqlSessionTemplate(sqlSessionFactory().getObject());
//    }
//
//    @Bean
//    public PageRepository pageRepository() throws Exception {
//        return new DefaultPageRepository(sqlSession());
//    }

    @Bean
    public MongoClient mongoClient() {
        //mongoUri = environment.getProperty("mongo.uri");
        MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
        builder.connectionsPerHost(50); // 最大50个连接
        MongoClientURI mongoClientURI = new MongoClientURI(mongoDatabaseUrl, builder);
        return new MongoClient(mongoClientURI);
    }

    @Bean
    public MongoDatabase mongoDatabase() {
        //mongoDatabase = environment.getProperty("mongo.database");
        return mongoClient().getDatabase(mongoDatabase);
    }

    @Bean
    public Datastore datastore() {
        MapperOptions mapperOptions = new MapperOptions();
        // 设置扫描子目录
        mapperOptions.setMapSubPackages(true);
        final Morphia morphia = new Morphia(new Mapper(mapperOptions));
        morphia.mapPackage("com.yulei.my.domain");
//        mongoDatabase = environment.getProperty("mongo.database");
        final Datastore datastore = morphia.createDatastore(mongoClient(), mongoDatabase);
        datastore.ensureIndexes();
        return datastore;
    }

    @Bean
    public PageRepository mongoPageRepository() {
        return new DefaultPageRepository();
    }

}
