package by.dao.impl.jdbc;

import java.util.List;
import org.springframework.stereotype.Repository;
import by.dao.LanguageDAO;
import by.dao.impl.jdbc.mapper.LanguageRowMapper;
import by.dao.model.common.Language;

@Repository
public class JdbcLanguageDAOImpl extends JdbcAbstractDao implements LanguageDAO {

	private LanguageRowMapper langMapper;
	
	public JdbcLanguageDAOImpl() {
		super();
	    langMapper = new LanguageRowMapper();
	}

	@Override
	public Language read(Integer id) {
		return getJdbcTemplate().queryForObject(
				getQuery("language.select.where.id"), langMapper, id);
	}

	@Override
	public Language findLangByTag(String tag) {
		return getJdbcTemplate().queryForObject(
				getQuery("language.select.where.tag"), langMapper, tag);
	}

	@Override
	public List<Language> findLanguages() {
		return getJdbcTemplate().query(
				getQuery("language.select.all"), langMapper);
	}

	@Override
	public List<Language> findActiveLanguages() {
		return getJdbcTemplate().query(
				getQuery("language.select.active"), langMapper);
	}

	@Override
	public void update(Language obj)  {
		
		getJdbcTemplate().update(
				getQuery("language.update"),
                obj.getName(),
                obj.getTag(),
                obj.isActive(),
                obj.getId());
	}

	@Override
	public void create(Language obj) {
		getJdbcTemplate().update(
				getQuery("language.insert.where.id"),
                obj.getName(),
                obj.getTag(),
                obj.isActive());
	}

	@Override
	public void delete(Integer id) {
		getJdbcTemplate().update(
				getQuery("language.delete.where.id"), id);
	}

}
