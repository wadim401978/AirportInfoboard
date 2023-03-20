package by.dao;

import java.util.List;

import by.dao.model.common.Language;

public interface LanguageDAO extends GenericDAO<Integer, Language> {
	public Language findLangByTag(String tag);
	public List<Language> findLanguages();
	public List<Language> findActiveLanguages();
}
