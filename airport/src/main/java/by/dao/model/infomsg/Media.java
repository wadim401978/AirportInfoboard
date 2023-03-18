package by.dao.model.infomsg;

import java.io.File;

public abstract class Media implements InfoBlock {
	private File file;
	private String html;

	@Override
	public String getHtml() {
		return this.html;
	}


	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}


}
