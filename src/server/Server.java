package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		System.out.println("Hello from Server");
		ServerSocket ss = new ServerSocket(12345);
		Socket s = ss.accept();
		System.out.println("Accepted connection from " + s.getInetAddress() + ":" + s.getPort());
		ObjectInputStream is = new ObjectInputStream(s.getInputStream());
		System.out.println((List<String>)is.readObject());
		ss.close();
	}

}
