package by.dao.impl.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@PropertySource("classpath:sql.properties")
public abstract class JdbcAbstractDao {
	
	@Autowired
	private Environment env;
	
	public String getQuery(String key) {
		return env.getProperty(key);
	}

}
