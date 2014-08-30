package test.client;

import java.io.IOException;

import client.IServer;

public class MockServer implements IServer {
	
	public MOCK_SERVER_MODE mode;

	public enum MOCK_SERVER_MODE {
		OK, NOT_OK
	}

	public MockServer(MOCK_SERVER_MODE mode) {
		this.mode = mode;
	}

	@Override
	public void connect(String serverAddress, int serverPort) throws IOException {
		switch(mode) {
		case OK:
			return;
		case NOT_OK:
			throw new IOException("Server not reachable");
		}
		
	}

}
