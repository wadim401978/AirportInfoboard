package by.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import by.dao.LanguageDAO;
import by.dao.model.common.Language;

@Repository
public class TestLanguageDAOImpl implements LanguageDAO {

	@Override
	public Language read(Integer id) {
		id--;
		if (id < 0) id = 0;
		return TestDataSet.getInstance().getLanguages().get(id);
	}


	@Override
	public Language getLangByCode(String code) {
		return TestDataSet.getInstance().getLangMap().get(code);
	}


	@Override
	public List<Language> getLanguages() {
		return TestDataSet.getInstance().getLanguages();
	}

}
