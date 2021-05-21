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

import listManager.ChangeCategoryServlet;
import util.ConnectionPool;
public class ChangeCategoryServletTest extends Mockito {

	private ChangeCategoryServlet servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	
	
	@BeforeEach
	public void setUp() throws Exception {
		servlet = new ChangeCategoryServlet();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		DatabaseHelper.initializeDatabase();
	}
	
	@AfterEach
	public void tearDown() throws Exception{
		ConnectionPool.setTest(false);
	}
	
	//inserimento corretto
	@Test
	public void testCase_1_1() throws ServletException, IOException{
		
			request.addParameter("username", "AerithGain");
			request.addParameter("game_id", "1");
			request.addParameter("category_value", "Acquistato");
			request.addParameter("user_category", "Platinato");
			request.addParameter("score", "100");
			request.addParameter("usrScore", "390");
			
			int newScore = 340;
			String message = "Categoria aggiornata, nuovo punteggio = "+newScore;

			servlet.doPost(request, response);
			
			assertEquals(message, response.getContentAsString());
		}
	
	@Test
	public void testCase_2() throws ServletException, IOException{
		
			request.addParameter("username", "AerithGain");
			request.addParameter("game_id", "4");
			request.addParameter("category_value", "Acquistato");
			request.addParameter("user_category", "Platinato");
			request.addParameter("score", "100");
			request.addParameter("usrScore", "390");
		

			servlet.doPost(request, response);
			
			assertEquals("Errore", response.getContentAsString());
			assertEquals(500, response.getStatus());
		}
	

	
}
