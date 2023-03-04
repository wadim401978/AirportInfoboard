package by.dao;

import by.dao.model.common.Language;

public interface AirEntityDAO<ID, T> extends GenericDAO<ID, T> {
    public String getNameByLang(Language lang, T obj);
    public void setNameByLang(String name, Language lang, T obj) ;
}
