package by.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import by.dao.DAO;
import by.dao.LanguageDAO;
import by.dao.model.common.Language;
import by.services.LanguageService;

@Service(value = "LanguageService")
public class LanguageServiceImpl implements LanguageService {
	
	private LanguageDAO dao;
	private ResourceBundle initialResourceBundle;

	public LanguageServiceImpl() {
		this.initialResourceBundle = ResourceBundle.getBundle("initial");
	}

	@Override
	@Autowired
	public void setDao(DAO<Language> dao) {
		this.dao = (LanguageDAO)dao;
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
		return dao.findLangByTag(
				initialResourceBundle.getString("language.default"));
	}

	@Override
	public Language getLangByTag(String tag) {
		return  dao.findLangByTag(tag);
	}

	@Override
	public Language get(int id) {
		return dao.read(id);
	}

	@Override
	public List<Language> getActiveLanguages() {
		List<Language> list = dao.findActiveLanguages();
		if(list==null) {
			list = new ArrayList<Language>();
		}
		return list;
	}

	@Override
	public Language getNextActiveLanguage(int id) {
		Language activeLang = null;
		List<Language> activeList = getActiveLanguages();
		if(activeList.isEmpty()) {
			activeLang = getDefaultLang();
		} else {
			for(Language lang : activeList) {
				if(lang.getId() > id) {
					activeLang = lang;
					break;
				}
			}
			if(activeLang==null) {
				activeLang = activeList.get(0);
			}
		}
		return activeLang;
	}

	@Override
	public void remove(int id) {
		dao.delete(id);
	}

}
