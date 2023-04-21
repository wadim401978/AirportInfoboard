package by.dao.impl.jdbc;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import by.dao.LanguageDAO;
import by.dao.impl.jdbc.mapper.LanguageRowMapper;
import by.dao.model.common.Language;

@Repository
public class JdbcLanguageDAOImpl implements LanguageDAO {

	private static final String SELECT_LANGUAGES = "SELECT * FROM languages";
	private static final String SELECT_LANG_BY_ID = SELECT_LANGUAGES + " WHERE id=?";
	private static final String SELECT_LANG_BY_TAG = SELECT_LANGUAGES + " WHERE tag=?";
	private static final String SELECT_ACTIVE_LANG = SELECT_LANGUAGES + " WHERE active=1";
	private static final String UPDATE_LANGUAGES = "UPDATE languages "
			+ "SET name=?, tag=?, active=?  WHERE id=?";
	private static final String INSERT_LANGUAGE = "INSERT INTO languages (name, tag, active)"
			+ "VALUES (?, ?, ?)";
	private static final String DELETE_LANGUAGES = "DELETE FROM languages WHERE id=?";
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private LanguageRowMapper langMapper;

	
	public JdbcLanguageDAOImpl() {
		super();
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//	    dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//	    dataSource.setUrl("jdbc:mysql://localhost:3306/airport_ib");
//	    dataSource.setUsername("root");
//	    dataSource.setPassword("1234_Root");
//	        
//	    jdbcTemplate = new JdbcTemplate(dataSource);
	    
	    langMapper = new LanguageRowMapper();
	}

	@Override
	public Language read(Integer id) {
		return jdbcTemplate.queryForObject(SELECT_LANG_BY_ID, langMapper, id);
	}


	@Override
	public Language findLangByTag(String tag) {
		return jdbcTemplate.queryForObject(SELECT_LANG_BY_TAG, langMapper, tag);
	}

	@Override
	public List<Language> findLanguages() {
		return jdbcTemplate.query(SELECT_LANGUAGES, new LanguageRowMapper());
	}

	@Override
	public List<Language> findActiveLanguages() {
		return jdbcTemplate.query(SELECT_ACTIVE_LANG, new LanguageRowMapper());
	}

	@Override
	public void update(Language obj) {
		jdbcTemplate.update(
				UPDATE_LANGUAGES,
                obj.getName(),
                obj.getLangTag(),
                obj.isActive(),
                obj.getId());
	}

	@Override
	public void create(Language obj) {
		jdbcTemplate.update(
				INSERT_LANGUAGE,
                obj.getName(),
                obj.getLangTag(),
                obj.isActive());
	}

	@Override
	public void delete(Integer id) {
		jdbcTemplate.update(DELETE_LANGUAGES, id);
	}
	

}
