package filesystem;

import java.io.File;
import java.util.List;

public class Directory implements IDirectory {
	
	File dir;

	public Directory() {
		dir = null;
	}

	@Override
	public void open(String path) {
		File dir = new File(path);
		if (dir.isDirectory()) {
			throw new IllegalArgumentException(path + ": Not a directory");
		}
	}

	@Override
	public List<String> get_files() {
		// TODO Auto-generated method stub
		return null;
	}

}