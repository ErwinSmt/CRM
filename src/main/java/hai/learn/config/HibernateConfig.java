package hai.learn.config;

import com.mchange.v2.c3p0.DriverManagerDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:database.properties")
public class HibernateConfig {

    @Autowired
    private Environment env;

    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean getSessionFactory(){
        LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
        factory.setPackagesToScan("hai.learn.entity");
        factory.setDataSource(dataSource());
        factory.setHibernateProperties(hibernateProperties());
        return factory;
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClass(env.getProperty("hibernate.connection.driverClass"));
        dataSource.setJdbcUrl(env.getProperty("hibernate.connection.url"));
        dataSource.setUser(env.getProperty("hibernate.connection.username"));
        dataSource.setPassword(env.getProperty("hibernate.connection.password"));

        return dataSource;
    }

    public Properties hibernateProperties(){
        Properties properties = new Properties();
        properties.setProperty(org.hibernate.cfg.Environment.SHOW_SQL,
                env.getProperty("hibernate.ShowSql"));
        properties.setProperty(org.hibernate.cfg.Environment.DIALECT,
                env.getProperty("hibernate.dialect"));
        return properties;
    }

    @Bean
    public HibernateTransactionManager transactionManager(){
        HibernateTransactionManager hibernateTransactionManager =
                new HibernateTransactionManager();

        hibernateTransactionManager.setSessionFactory(getSessionFactory().getObject());

        return hibernateTransactionManager;
    }
}
