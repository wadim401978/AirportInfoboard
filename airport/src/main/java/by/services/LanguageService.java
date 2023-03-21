package by.services;

import java.util.List;

import by.dao.model.common.Language;

public interface LanguageService extends Service<Language> {
	public Language getDefaultLang();
	public Language getLangByTag(String tag);
	public List<Language> getActiveLanguages();
	public Language getNextActiveLanguage(int id);
}
