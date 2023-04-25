package by.dao.impl.jdbc;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import by.dao.LanguageDAO;
import by.dao.impl.jdbc.mapper.LanguageRowMapper;
import by.dao.model.common.Language;

@Repository
public class JdbcLanguageDAOImpl extends JdbcAbstractDao implements LanguageDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private LanguageRowMapper langMapper;
	
	public JdbcLanguageDAOImpl() {
		super();
	    langMapper = new LanguageRowMapper();
	}

	@Override
	public Language read(Integer id) {
		return jdbcTemplate.queryForObject(getQuery("language.select.where.id"), langMapper, id);
	}

	@Override
	public Language findLangByTag(String tag) {
		return jdbcTemplate.queryForObject(getQuery("language.select.where.tag"), langMapper, tag);
	}

	@Override
	public List<Language> findLanguages() {
		return jdbcTemplate.query(getQuery("language.select.all"), langMapper);
	}

	@Override
	public List<Language> findActiveLanguages() {
		return jdbcTemplate.query(getQuery("language.select.active"), langMapper);
	}

	@Override
	public void update(Language obj)  {
		
		jdbcTemplate.update(
				getQuery("language.update"),
                obj.getName(),
                obj.getTag(),
                obj.isActive(),
                obj.getId());
	}

	@Override
	public void create(Language obj) {
		jdbcTemplate.update(
				getQuery("language.insert.where.id"),
                obj.getName(),
                obj.getTag(),
                obj.isActive());
	}

	@Override
	public void delete(Integer id) {
		jdbcTemplate.update(getQuery("language.delete.where.id"), id);
	}

}
