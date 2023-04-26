package by.dao.impl.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

@PropertySource("classpath:sql.properties")
public abstract class JdbcAbstractDao {

	@Autowired
	private Environment env;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Environment getEnv() {
		return env;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}


	public String getDefaultLangTag() {
		return getProperty("language.default");
	}

	public String getQuery(String key) {
		return getProperty(key);
	}

	public String getProperty(String key) {
		return env.getProperty(key);
	}

}
