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

import gameManager.CreateGameReq;
import util.ConnectionPool;

public class CreateGameReqTest extends Mockito{
	
	private CreateGameReq servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	
	
	@BeforeEach
	public void setUp() throws Exception {
		servlet = new CreateGameReq();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		DatabaseHelper.initializeDatabase();
	}
	
	@AfterEach
	public void tearDown() throws Exception{
		ConnectionPool.setTest(false);
	}
	
	//creazione corretta della richiesta
	@Test
	public void testCase_1() throws ServletException, IOException{
		
	
		request.addParameter("gamereq_title", "Donkey Kong Country");
		request.addParameter("gamereq_source", "https://it.wikipedia.org/wiki/Donkey_Kong_Country");
		request.addParameter("publisher", "Nintendo");
		request.addParameter("game_genre", "Platform");
		request.addParameter("game_year", "1994");
		request.addParameter("username", "Zagreus94");

		servlet.doGet(request, response);
		assertEquals("true", (String)request.getAttribute("richiestagioco"));
		
	}
	
	//test per lanciare l'eccezione - utente non esistente
	@Test
	public void testCase_2() throws ServletException, IOException{
	
		request.addParameter("gamereq_title", "Donkey Kong Country");
		request.addParameter("gamereq_source", "https://it.wikipedia.org/wiki/Donkey_Kong_Country");
		request.addParameter("publisher", "Nintendo");
		request.addParameter("game_genre", "Platform");
		request.addParameter("game_year", "1994");
		request.addParameter("username", "UtenteNonEsistente");
		
		//utente non esistente
		servlet.doGet(request, response);
		assertEquals("false", (String)request.getAttribute("richiestagioco"));
		
		
	}
	
	//parametri nulli
	@Test
	public void testCase_3() throws ServletException, IOException{
	
	
		
		servlet.doGet(request, response);
		assertEquals("false", (String)request.getAttribute("richiestagioco"));
		
		
	}


}
