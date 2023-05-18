package by.services;

import by.dao.model.common.Language;

public interface LanguageService extends ActiveEntityService<Language> {
	public Language getDefaultLang();
	public Language getLangByTag(String tag);
}
