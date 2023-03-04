package by.dao.impl;

import org.springframework.stereotype.Repository;

import by.dao.LanguageDAO;
import by.dao.model.common.Language;

//@Repository(value = "LanguageDAO")
@Repository
public class TestLanguageDAOImpl implements LanguageDAO {
	
	
	@Override
	public void create(Language obj) {
		//--Object created TODO log4j
	}

	@Override
	public Language read(Integer id) {
		Language lang;
		switch (id) {
		case 2:
			lang = new Language(id, "английский-english", "en");
			break;

		case 3:
			lang = new Language(id, "белорусский-беларуская", "be");
			break;

		default:
			lang = new Language(id, "русский", "ru");
			break;
		}
		return lang;
	}

	@Override
	public void update(Language obj) {
		//--Object updated TODO log4j
	}

	@Override
	public void delete(Integer id) {
		//--Object deleted TODO log4j
	}

}
