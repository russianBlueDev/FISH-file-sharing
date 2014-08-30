package client;

import java.io.IOException;

public interface IServer {

	void connect(String serverAddress, int serverPort) throws IOException;

}
