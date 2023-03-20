package by.dao.model.common;

import by.dao.model.Entity;

public class Language extends Entity{
    private String name;
    private String tag;
    private boolean active;
    
    public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public Language(int id, String name, String tag, boolean active) {
		super(id);
		this.name = name;
		this.tag = tag;
		this.active = active;
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
