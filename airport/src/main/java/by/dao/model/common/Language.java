package by.dao.model.common;

import by.dao.model.Entity;

public class Language extends Entity{
    private String name;
    private String tag;
    
    public Language(int id, String name, String langTag) {
		setId(id);
		this.name = name;
		this.tag = langTag;
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

	public String getLangTag() {
		return tag;
	}

	public void setLangTag(String langTag) {
		this.tag = langTag;
	}


	@Override
	public String toString() {
		return super.toString() + getLangTag() + ": " + getName();
	}
	
	

}
