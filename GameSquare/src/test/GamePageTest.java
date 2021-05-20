package test;
import org.mockito.Mockito;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;


import gameManager.GamePageServlet;
import util.ConnectionPool;

public class GamePageTest extends Mockito{

	private GamePageServlet servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	
	
	@BeforeEach
	public void setUp() throws Exception {
		servlet = new GamePageServlet();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		DatabaseHelper.initializeDatabase();
	}
	
	@AfterEach
	public void tearDown() throws Exception{
		ConnectionPool.setTest(false);
	}
	
	//visualizzazione corretta del thread
	@Test
	public void testCase_1() throws ServletException, IOException{
	
		request.addParameter("id", "1");

		servlet.doPost(request, response);
		assertEquals("Final Fantasy VII", response.getContentAsString());
		
	}
	
	//visualizzazione errata del thread
		@Test
		public void testCase_2() throws ServletException, IOException{
		
			request.addParameter("id", "0");

			servlet.doPost(request, response);
			assertEquals("errore", response.getContentAsString());
			
		}
	
	
}
