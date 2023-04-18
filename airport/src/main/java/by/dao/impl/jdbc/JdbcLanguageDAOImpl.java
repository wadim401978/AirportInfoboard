package by.dao.impl.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import by.dao.LanguageDAO;
import by.dao.impl.jdbc.mapper.LanguageRowMapper;
import by.dao.model.common.Language;

//@Transactional
@Repository
public class JdbcLanguageDAOImpl implements LanguageDAO {

	private static final String SELECT_LANGUGES = "SELECT * FROM languages";
	private static final String SELECT_LANG_BY_ID = SELECT_LANGUGES + " WHERE id=?";
	private static final String SELECT_LANG_BY_TAG = SELECT_LANGUGES + " WHERE tag=?";
	private static final String SELECT_ACTIVE_LANG = SELECT_LANGUGES + " WHERE active=1";
	
	
	private JdbcTemplate jdbcTemplate;
	private LanguageRowMapper langMapper;

//	@Autowired
//	public JdbcLanguageDAOImpl(JdbcTemplate jdbcTemplate) {
//		super();
//		this.jdbcTemplate = jdbcTemplate;
//		this.langMapper = new LanguageRowMapper();
//	}
	
	public JdbcLanguageDAOImpl() {
		super();
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
	    dataSource.setUrl("jdbc:mysql://localhost:3306/airport_ib");
	    dataSource.setUsername("root");
	    dataSource.setPassword("1234_Root");
	        
	    jdbcTemplate = new JdbcTemplate(dataSource);
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
		return jdbcTemplate.query(SELECT_LANGUGES, new LanguageRowMapper());
	}

	@Override
	public List<Language> findActiveLanguages() {
		return jdbcTemplate.query(SELECT_ACTIVE_LANG, new LanguageRowMapper());
	}

}
