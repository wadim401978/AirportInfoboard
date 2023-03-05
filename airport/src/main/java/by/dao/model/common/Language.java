package by.dao.model.common;

import by.dao.model.Entity;

public class Language extends Entity{
    private String name;
    private String code;
    
    public Language(int id, String name, String code) {
		setId(id);
		this.name = name;
		this.code = code;
	}


	public Language() {
		super();
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	@Override
	public String toString() {
		return getId()+ ") " + getCode() + ": " + getName() + " / " + hashCode();
	}
	
	

}
