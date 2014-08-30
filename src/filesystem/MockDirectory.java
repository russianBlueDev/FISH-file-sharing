package filesystem;

public class MockDirectory implements IDirectory {

	public MOCK_DIR_MODE mode;

	public enum MOCK_DIR_MODE {
		DIR_OK, DIR_NOT_OK
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

}
