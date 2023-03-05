package by.services.impl;

import java.util.List;
import java.util.ResourceBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import by.dao.LanguageDAO;
import by.dao.model.common.Language;
import by.services.LanguageService;

@Service
public class TestLanguageServiceImpl implements LanguageService {
	
	private LanguageDAO dao;

	@Autowired
	public void setDao(LanguageDAO dao) {
		this.dao = dao;
	}

	@Override
	public List<Language> getAll() {
		return dao.getLanguages();
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
		ResourceBundle resourceBundle = ResourceBundle.getBundle("initial");
		return dao.getLangByCode(resourceBundle.getString("language.default"));
	}

}
