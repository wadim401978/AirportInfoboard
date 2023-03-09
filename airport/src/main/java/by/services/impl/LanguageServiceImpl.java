package by.services.impl;

import java.util.List;
import java.util.ResourceBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import by.dao.LanguageDAO;
import by.dao.model.common.Language;
import by.services.LanguageService;

@Service
public class LanguageServiceImpl implements LanguageService {
	
	private LanguageDAO dao;
	private ResourceBundle initialResourceBundle;

	public LanguageServiceImpl() {
		this.initialResourceBundle = ResourceBundle.getBundle("initial");
	}

	@Autowired
	public void setDao(LanguageDAO dao) {
		this.dao = dao;
	}
	

	@Override
	public List<Language> getAll() {
		return dao.findLanguages();
	}

	@Override
	public void save(Language obj) {
		// ---Object saved 
        if (obj.getId() == 0) {
            dao.create(obj);
        } else {
            dao.update(obj);
        }
	}

	@Override
	public Language getDefaultLang() {
		return dao.findLangByTag(initialResourceBundle.getString("language.default"));
	}

	@Override
	public Language getLangByTag(String tag) {
		return  dao.findLangByTag(tag);
	}

}
