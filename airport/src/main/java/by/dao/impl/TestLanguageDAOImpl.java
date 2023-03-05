package by.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import by.dao.LanguageDAO;
import by.dao.model.common.Language;

//@Repository(value = "LanguageDAO")
@Repository
public class TestLanguageDAOImpl implements LanguageDAO {

	private Map<String, Language> langMap;
	private List<Language> languages;
	
	public TestLanguageDAOImpl() {
		super();
		this.langMap = new HashMap<String, Language>();
		int i = 0;
		Language lang = new Language(++i, "русский", "ru");
		langMap.put(lang.getCode(), lang);
		languages = new ArrayList<>();
		languages.add(lang);
		lang = new Language(++i, "английский-english", "en");
		langMap.put(lang.getCode(), lang);
		languages.add(lang);
		lang = new Language(++i, "белорусский-беларуская", "be");
		langMap.put(lang.getCode(), lang);
		languages.add(lang);
		
	}


	@Override
	public Language read(Integer id) {
		id--;
		if (id < 0) id = 0;
		return languages.get(id);
	}


	@Override
	public Language getLangByCode(String code) {
		return langMap.get(code);
	}


	@Override
	public List<Language> getLanguages() {
		return languages;
	}

}
