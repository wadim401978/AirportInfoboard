package by.dao.impl.test;

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
	public Language findLangByCode(String code) {
		return TestDataSet.getInstance().getLangMap().get(code);
	}


	@Override
	public List<Language> findLanguages() {
		return TestDataSet.getInstance().getLanguages();
	}

}
