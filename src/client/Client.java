package client;

import java.io.IOException;
import java.util.List;

import filesystem.Directory;
import filesystem.IDirectory;

public class Client {

	private boolean sharing;
	private String serverAddress;
	private int serverPort;

	public IDirectory shared_dir;
	public IServer server;

	public Client() {
		this.sharing = false;
		this.shared_dir = new Directory();
		this.server = new Server();
	}

	public void share(String serverAddress, int serverPort, String shared_path) throws IOException {

		if (sharing) {
			throw new IllegalArgumentException("Client is already sharing");
		}

		this.sharing = true;
		this.serverAddress = serverAddress;
		this.serverPort = serverPort;
		shared_dir.open(shared_path);

		// Check that dir is not empty
		List<String> filenames = shared_dir.get_files();
		if (filenames.size() == 0) {
			throw new IllegalArgumentException(shared_path
					+ ": Directory is empty");
		}

		// Check that no file contains whitespaces
		for (String fname : filenames) {
			if (fname.contains(" ")) {
				throw new IllegalArgumentException(shared_path + ": the file "
						+ fname + " contains a whitespace in its name");
			}
		}

		// try to connect to server
		server.connect(serverAddress, serverPort);
	}

	public static void main(String[] args) {
		System.out.println("Hello from Client");
	}

}
