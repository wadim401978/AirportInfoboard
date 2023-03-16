package by.dao.model.infomsg.impl;

import by.dao.model.infomsg.InfoBlock;

public class TextBlock implements InfoBlock {
	private String html;

	@Override
	public void setHtml(String html) {
		this.html = html;

	}

	@Override
	public String getHtml() {
		return this.html;
	}

}
