package filesystem;

import java.util.List;

public interface IDirectory {
	public void open(String path);
	public List<String> get_files();
}
