package by.dao.impl.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import by.dao.impl.jdbc.mapper.AbstractExtractor;
import by.dao.model.common.Language;

@PropertySource("classpath:sql.properties")
public abstract class JdbcAbstractDao {

	@Autowired
	private Environment env;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private AbstractExtractor extractor;
	
	public AbstractExtractor getExtractor() {
		return extractor;
	}

	public void setExtractor(AbstractExtractor extractor) {
		this.extractor = extractor;
	}
	
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
	
	public List<Object[]> getNamesMapBatch(Map<Language, String> names, int objectId) {
		List<Object[]> batch = new ArrayList<>();
		Set<Entry<Language, String>> entrySet = names.entrySet();
		for (Entry<Language, String> entry : entrySet) {
			Object[] values = new Object[] {
					objectId, entry.getKey().getId(), entry.getValue()};
//                    1, 1, entry.getValue()};
			batch.add(values);
		}
		return batch;
	}

}
