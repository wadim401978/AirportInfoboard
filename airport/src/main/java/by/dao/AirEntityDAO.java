package by.dao;

import by.dao.model.common.Language;

public interface AirEntityDAO<ID, T> extends GenericDAO<ID, T> {
    public String readName(Language lang, T obj);
    public void saveNameByLang(String name, Language lang, T obj) ;
}
