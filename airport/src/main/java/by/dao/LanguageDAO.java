package by.dao;

import java.util.List;

import by.dao.model.common.Language;

public interface LanguageDAO extends GenericDAO<Integer, Language> {
	public Language getLangByCode(String code);
	public List<Language> getLanguages();
}
