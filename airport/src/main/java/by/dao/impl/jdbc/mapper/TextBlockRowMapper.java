package by.dao.impl.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import by.dao.model.infomsg.TextBlock;

public class TextBlockRowMapper implements RowMapper<TextBlock> {

	@Override
	public TextBlock mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new TextBlock(
				rs.getInt("id"), 
				rs.getString("html"), 
				rs.getBoolean("active"));
	}

}
