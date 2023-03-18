package by.dao.model.infomsg.impl;

import by.dao.model.Entity;
import by.dao.model.infomsg.InfoBlock;

public class TextBlock extends Entity implements InfoBlock {
	private String html;


	public TextBlock(int id, String html) {
		super(id);
		this.html = html;
	}


	@Override
	public String getHtml() {
		return this.html;
	}

}
