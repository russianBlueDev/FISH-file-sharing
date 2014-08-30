package test.client;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void TestShareOK() {
		client.shared_dir = new MockDirectory(MOCK_DIR_MODE.DIR_OK);
		client.share("localhost", 6925, "share");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void TestShareNotOK() {
		client.shared_dir = new MockDirectory(MOCK_DIR_MODE.DIR_NOT_OK);
		client.share("localhost", 6925, "share");
	}

}
