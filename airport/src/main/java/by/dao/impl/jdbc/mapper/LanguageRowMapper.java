package by.dao.impl.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import by.dao.model.common.Language;

public class LanguageRowMapper implements RowMapper<Language> {

	@Override
	public Language mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Language(
				rs.getInt("id"), 
				rs.getString("name"), 
				rs.getString("tag"), 
				rs.getBoolean("active"));
	}
}
