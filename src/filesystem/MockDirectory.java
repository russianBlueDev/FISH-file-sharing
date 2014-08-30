package filesystem;

import java.util.Arrays;
import java.util.List;

public class MockDirectory implements IDirectory {

	public MOCK_DIR_MODE mode;

	public enum MOCK_DIR_MODE {
		DIR_OK, DIR_NOT_OK, DIR_EMPTY, DIR_ONE_FILE, DIR_MULTIPLE_FILES, DIR_FILE_WITH_SPACE
	}

	public MockDirectory(MOCK_DIR_MODE mode) {
		this.mode = mode;
	}

	@Override
	public void open(String path) {
		if (this.mode == MOCK_DIR_MODE.DIR_NOT_OK) {
			throw new IllegalArgumentException(path + ": Not a directory");
		}
	}

	@Override
	public List<String> get_files() {

		switch(mode) {
		case DIR_OK:
		case DIR_ONE_FILE:
			return Arrays.asList("file1.txt");
		case DIR_MULTIPLE_FILES:
			return Arrays.asList("file1.txt", "file2.txt", "file3.txt");
		case DIR_EMPTY:
			return Arrays.asList();
		case DIR_FILE_WITH_SPACE:
			return Arrays.asList("file1.txt",  "file with spaces.txt", "file3.txt");
		default:
			assert(false);
			break;
		}

		return null;
	}

}
