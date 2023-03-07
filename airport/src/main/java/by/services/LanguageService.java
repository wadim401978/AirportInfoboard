package by.services;

import by.dao.model.common.Language;

public interface LanguageService extends Service<Language> {
	public Language getDefaultLang();
	public Language getLangByCode(String code);
}
