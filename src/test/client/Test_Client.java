package test.client;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import test.client.MockServer.MOCK_SERVER_MODE;
import client.Client;
import filesystem.MockDirectory;
import filesystem.MockDirectory.MOCK_DIR_MODE;

public class Test_Client {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	private Client client;
	
	@Before
	public void setUp() throws Exception {
		client = new Client();
		client.shared_dir = new MockDirectory(MOCK_DIR_MODE.DIR_OK);
		client.server = new MockServer(MOCK_SERVER_MODE.OK);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void TestCallTwiceShare() throws IOException {
		client.share("localhost", 6925, "share");
		client.share("localhost", 6925, "share");
	}

	@Test
	public void TestShareOneFile() throws IOException {
		client.shared_dir = new MockDirectory(MOCK_DIR_MODE.DIR_ONE_FILE);
		client.share("localhost", 6925, "share");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void TestShareDirNotOK() throws IOException {
		client.shared_dir = new MockDirectory(MOCK_DIR_MODE.DIR_NOT_OK);
		client.share("localhost", 6925, "share");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void TestShareDirIsEmpty() throws IOException {
		client.shared_dir = new MockDirectory(MOCK_DIR_MODE.DIR_EMPTY);
		client.share("localhost", 6925, "share");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void TestShareFileWithSpaces() throws IOException {
		client.shared_dir = new MockDirectory(MOCK_DIR_MODE.DIR_FILE_WITH_SPACE);
		client.share("localhost", 6925, "share");
	}
	
	@Test
	public void TestShareServerReachable() throws IOException {
		client.share("localhost", 6925, "share");
	}
	
	@Test(expected = IOException.class)
	public void TestShareServerNotReachable() throws IOException {
		client.server = new MockServer(MOCK_SERVER_MODE.NOT_OK);
		client.share("localhost", 6925, "share");
	}

}
