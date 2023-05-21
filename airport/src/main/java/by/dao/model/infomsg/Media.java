package by.dao.model.infomsg;

import java.io.File;

public abstract class Media extends InfoBlock {
	private File file;

	public Media(int id, String html, boolean active, File file) {
		super(id, html, active);
		this.file = file;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
