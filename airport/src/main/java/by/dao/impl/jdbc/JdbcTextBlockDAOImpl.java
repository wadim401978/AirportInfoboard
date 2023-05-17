package by.dao.impl.jdbc;

import java.util.List;
import org.springframework.stereotype.Repository;
import by.dao.TextBlockDAO;
import by.dao.impl.jdbc.mapper.TextBlockRowMapper;
import by.dao.model.infomsg.TextBlock;

@Repository
public class JdbcTextBlockDAOImpl extends JdbcAbstractDao implements TextBlockDAO {

	private TextBlockRowMapper mapper;
	
	public JdbcTextBlockDAOImpl() {
		super();
		this.mapper = new TextBlockRowMapper();
	}
	
	@Override
	public List<TextBlock> findAllBlocks() {
		String query = getQuery("textAnnouncments.select.all");
		return getJdbcTemplate().query(query, mapper);
	}

	@Override
	public List<TextBlock> findActiveBlocks() {
		String query = getQuery("textAnnouncments.select.active");
		return getJdbcTemplate().query(query, mapper);
	}

	@Override
	public TextBlock read(Integer id) {
		String query = getQuery("textAnnouncments.select.where.id");
		try {
			return getJdbcTemplate().queryForObject(query, mapper, id);
		} catch (Exception e) {
			return new TextBlock(id, "Page №404", false);
		}
		
	}

	@Override
	public void create(TextBlock obj) {
		
		getJdbcTemplate().update(
				getQuery("textAnnouncments.insert.where.id"),
                obj.getHtml(),
                obj.isActive());

		
	}

	@Override
	public void update(TextBlock obj) {
		getJdbcTemplate().update(
				getQuery("textAnnouncments.update"),
                obj.getHtml(),
                obj.isActive(),
                obj.getId());
	}

	@Override
	public void delete(Integer id) {
		getJdbcTemplate().update(
				getQuery("textAnnouncments.delete.where.id"), id);
	}

}
