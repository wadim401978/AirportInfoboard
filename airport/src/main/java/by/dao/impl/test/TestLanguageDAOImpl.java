package by.dao.impl.test;

import java.util.List;
import org.springframework.stereotype.Repository;
import by.dao.LanguageDAO;
import by.dao.model.common.Language;

@Repository
public class TestLanguageDAOImpl implements LanguageDAO, ActiveItemsDAO<Language> {

	@Override
	public Language read(Integer id) {
		id--;
		if (id < 0) id = 0;
		List<Language> list = TestDataSet.getInstance().getLanguages();
		if (id >= list.size()) {
			return null;
		}
		return list.get(id);
	}


	@Override
	public Language findLangByTag(String tag) {
		return TestDataSet.getInstance().getLangMap().get(tag);
	}


	@Override
	public List<Language> findLanguages() {
		return TestDataSet.getInstance().getLanguages();
	}


	@Override
	public List<Language> findActiveLanguages() {
		return findActiveItems(this.findLanguages());
	}

}
