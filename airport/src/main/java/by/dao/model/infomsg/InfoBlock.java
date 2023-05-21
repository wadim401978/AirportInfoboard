package by.dao.model.infomsg;

import by.dao.model.ActiveEntity;

public abstract class InfoBlock extends ActiveEntity {
	private String html;
	
	public InfoBlock(int id, String html, boolean active) {
		super(id, active);
		this.html = html;
	}
	
	public InfoBlock() {
	}
	
	public String getHtml() {
		return html;
	}
	
	public void setHtml(String html) {
		this.html = html;
	}
}
