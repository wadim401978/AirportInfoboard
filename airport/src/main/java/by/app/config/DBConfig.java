package by.app.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:database.properties")
public class DBConfig {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	@Bean(value = "dataSource")
	public DataSource getDataSource() {
		DriverManagerDataSource dm = new DriverManagerDataSource();
		
		if  (dataSource == null) {
			dataSource = dm;
		}
	    
		dm.setDriverClassName(env.getProperty("spring.datasource.driver"));
		dm.setUrl(env.getProperty("spring.datasource.url"));
		dm.setUsername(env.getProperty("spring.datasource.root"));
		dm.setPassword(env.getProperty("spring.datasource.password")); 
	    return dataSource;
	    
	}
	
	@Bean(value = "jdbcTemplate")
	public JdbcTemplate getJdbcTemplate() {
		if (jdbcTemplate == null) {
			jdbcTemplate = new JdbcTemplate(dataSource);
		}
		return jdbcTemplate;
	}
	
	@Bean(value = "txManager")
	public PlatformTransactionManager txManager() {
	    return new DataSourceTransactionManager(getDataSource()); // (2)
	}

}
