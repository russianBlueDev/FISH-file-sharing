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
	private ObjectOutputStream os;

	public Client share(String serverAddress, int serverPort, String shared_path)
			throws UnknownHostException, IOException {
		if (shared_path != null) {
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
		}
		socket = new Socket(serverAddress, serverPort);
		os = new ObjectOutputStream(socket.getOutputStream());
		if (shared_path != null) {
			os.writeObject("SHARE");
			os.writeObject(shared_files);
		}
		return this;
	}

	public void unshare() throws IOException {
		os.writeObject("UNSHARE");
	}

	public static void main(String[] args) {
		if (args.length > 3) {
			System.out
					.println("USAGE:\njava Client [shared_file_path] [server_address] [server_port]");
			System.exit(0);
		}

		String shared_dir = null;
		String serverAddress = "localhost";
		int serverPort = 12345;

		for (String arg : args) {
			if (arg.matches("\\w.*\\..*\\w") && !arg.matches("/")) {
				serverAddress = arg;
			} else if (arg.matches("^[0-9]$")) {
				serverPort = Integer.parseInt(arg);
			} else {
				shared_dir = arg;
			}
		}

		try {
			new Client().share(serverAddress, serverPort, shared_dir).unshare();
		} catch (UnknownHostException e) {
			System.err.println(serverAddress + ": Unknown host");
		} catch (IOException e) {
			System.err.println("Server Communication Failure");
		}
	}
}
