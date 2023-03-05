package by.dao.impl;

import by.dao.AirEntityDAO;
import by.dao.model.AirEntity;
import by.dao.model.common.Language;

public abstract class TestAirEntityDAO implements AirEntityDAO<Integer, AirEntity> {

	@Override
	public void create(AirEntity obj) {
		//--Object created TODO log4j
	}

	@Override
	public AirEntity read(Integer id) {
		// It's gonna be overwritten by Child
		return null;
	}

	@Override
	public void update(AirEntity obj) {
		//--Object updated TODO log4j
	}

	@Override
	public void delete(Integer id) {
		//--Object deleted TODO log4j
	}

	@Override
	public String getNameByLang(Language lang, AirEntity obj) {
		return obj.getNames().get(lang);
	}

	@Override
	public void setNameByLang(String name, Language lang, AirEntity obj) {
		obj.getNames().put(lang, name);
	}

}
