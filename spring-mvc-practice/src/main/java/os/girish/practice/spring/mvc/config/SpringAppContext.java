package os.girish.practice.spring.mvc.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
@ComponentScan(basePackages = { "os.girish.practice.spring.mvc" })
public class SpringAppContext {

	@Autowired
	private Environment environment;

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
		bean.setDataSource(dataSource());
		bean.setHibernateProperties(hibernateProp());
		bean.setPackagesToScan("os.girish.practice.spring.mvc");
		return bean;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource source = new DriverManagerDataSource();
		source.setDriverClassName(environment.getRequiredProperty("jdbc.driver"));
		source.setUrl(environment.getRequiredProperty("jdbc.url"));
		source.setUsername(environment.getRequiredProperty("jdbc.user"));
		source.setPassword(environment.getRequiredProperty("jdbc.password"));
		return source;
	}

	private Properties hibernateProp() {
		Properties prop = new Properties();
		prop.put("hibernate.dilect", environment.getRequiredProperty("hibernate.dilect"));
		prop.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
		prop.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
		prop.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
		prop.put("hibernate.archive.autodetection", "class, hbm");
		return prop;
	}

	@Bean
	public HibernateTransactionManager getTransactionMgr() {
		HibernateTransactionManager mgr = new HibernateTransactionManager(sessionFactory().getObject());
		return mgr;
	}
}
