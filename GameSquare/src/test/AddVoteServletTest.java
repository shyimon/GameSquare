package test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import gameManager.AddVoteServlet;
import util.ConnectionPool;
public class AddVoteServletTest extends Mockito {

	private AddVoteServlet servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	
	
	@BeforeEach
	public void setUp() throws Exception {
		servlet = new AddVoteServlet();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		DatabaseHelper.initializeDatabase();
	}
	
	@AfterEach
	public void tearDown() throws Exception{
		ConnectionPool.setTest(false);
	}
	
	//valutazione per un gioco non ancora votato
	@Test
	public void testCase_1_1() throws ServletException, IOException{
		
			request.addParameter("username", "AerithGain");
			request.addParameter("game_id", "4");
			request.addParameter("vote_value", "9");
			
			float media = 9;
			servlet.doGet(request, response);
			
			assertEquals("success", request.getAttribute("result"));
			assertEquals(media, request.getAttribute("newAverage"));
		}

	//valutazione per un gioco votato già da qualcun altro
	@Test
	public void testCase_1_2() throws ServletException, IOException{
			
			request.addParameter("username", "Zagreus94");
			request.addParameter("game_id", "7");
			request.addParameter("vote_value", "10");
				
			float media = (float) 9.5;
			servlet.doGet(request, response);
				
			assertEquals("success", request.getAttribute("result"));
			assertEquals(media, request.getAttribute("newAverage"));
	}
	
	//valutazione per un gioco già votato, con sovrascrittura della media
	@Test
	public void testCase_1_3() throws ServletException, IOException{
			
			request.addParameter("username", "AerithGain");
			request.addParameter("game_id", "1");
			request.addParameter("vote_value", "10");
				
			float media = 9;
			servlet.doGet(request, response);
				
			assertEquals("success", request.getAttribute("result"));
			assertEquals(media, request.getAttribute("newAverage"));
		}
	
	//inserimento errato, per coprire l'eccezione
	@Test
	public void testCase_2() throws ServletException, IOException{
		
			request.addParameter("username", "nonesisto");
			request.addParameter("game_id", "1");
			request.addParameter("vote_value", "9");
			

			servlet.doGet(request, response);
			
			assertEquals("fail", request.getAttribute("result"));
		}
	
}
