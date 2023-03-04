package by.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.dao.LanguageDAO;
import by.dao.model.common.Language;
import by.services.LanguageService;

//@Service(value = "LanguageService")
@Service
public class TestLanguageServiceImpl implements LanguageService {
	
	
	private LanguageDAO dao;

	@Autowired
	public void setDao(LanguageDAO dao) {
		this.dao = dao;
	}

	@Override
	public List<Language> getAll() {
		List<Language> list = new ArrayList<>();
		for (int i = 1; i <=3; i++) {
			list.add(dao.read(i));
		}
		return list;
	}

	@Override
	public void saveInstance(Language obj) {
		// ---Object saved TODO log4j
	}

}
