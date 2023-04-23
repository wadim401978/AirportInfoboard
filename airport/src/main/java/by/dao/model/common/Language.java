package by.dao.model.common;

import by.dao.model.ActiveEntity;

public class Language extends ActiveEntity{
    private String name;
    private String tag;

	public Language(int id, String name, String tag, boolean active) {
		super(id, active);
		this.name = name;
		this.tag = tag;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getTag() {
		return tag;
	}

	public void setTag(String langTag) {
		this.tag = langTag;
	}


	@Override
	public String toString() {
		return super.toString() + getTag() + ": " + getName();
	}
	
	

}
