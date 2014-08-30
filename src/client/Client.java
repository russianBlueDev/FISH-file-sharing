package client;

import filesystem.Directory;
import filesystem.IDirectory;

public class Client {
	
	private boolean sharing;
	private String serverAddress;
	private int serverPort;
	public IDirectory shared_dir;

	public Client() {
		this.sharing = false;
		this.shared_dir = new Directory();
	}

	public void share(String serverAddress, int serverPort, String shared_path) {
		this.serverAddress = serverAddress;
		this.serverPort = serverPort;
		shared_dir.open(shared_path);
	}



	public static void main(String[] args) {
		System.out.println("Hello from Client");
	}

}
