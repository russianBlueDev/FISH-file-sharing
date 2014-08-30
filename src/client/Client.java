package client;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

public class Client {

	private File shared_dir;
	private List<String> shared_files;
	private Socket socket;

	public void share(String serverAddress, int serverPort, String shared_path)
			throws UnknownHostException, IOException {
		shared_dir = new File(shared_path);
		if (!shared_dir.isDirectory()) {
			throw new IllegalArgumentException(shared_path
					+ ": Not a directory");
		}
		shared_files = Arrays.asList(shared_dir.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return new File(dir + File.separator + name).isFile();
			}
		}));
		socket = new Socket(serverAddress, serverPort);
		ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
		os.writeObject(shared_files);
	}

	public static void main(String[] args) throws UnknownHostException,
			IOException {
		if(args.length < 1) {
			System.out.println("USAGE:\njava Client shared_file_path [server_address] [server_port]");
			System.exit(0);
		}
		String shared_dir = args[0];
		String serverAddress = args.length > 1 ? args[1] : "localhost";
		int serverPort = args.length > 2 ? Integer.parseInt(args[2]) : 12345;
		new Client().share(serverAddress, serverPort, shared_dir);
	}
}
