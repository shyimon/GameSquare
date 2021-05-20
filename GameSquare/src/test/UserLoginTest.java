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

import userManager.UserLogin;
import util.ConnectionPool;

public class UserLoginTest extends Mockito {

	private UserLogin servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	

	@BeforeEach
	public void setUp() throws Exception {
		servlet = new UserLogin();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		DatabaseHelper.initializeDatabase();
	}
	
	@AfterEach
	public void tearDown() throws Exception{
		ConnectionPool.setTest(false);
	}
	
	//TC_2.1_1 - Email non trovata nel database
	@Test
	public void testCase_1() throws ServletException, IOException{
		request.addParameter("user_email", "emailfalsa@nonvalida.com");
		request.addParameter("user_password", "nonvalida");

		servlet.doGet(request, response);
		
		assertEquals("incorrectEmail", (String)request.getAttribute("result"));
		assertNull(request.getSession().getAttribute("utenteLoggato"));
	}
	
	
	//TC_2.1_2 - Email trovata nel database, ma password errata
	@Test
	public void testCase_2() throws ServletException, IOException{
		request.addParameter("user_email", "againsborough@yahoo.it");
		request.addParameter("user_password", "nonvalida");

		servlet.doGet(request, response);
		
		assertEquals("incorrectPass", (String)request.getAttribute("result"));
		assertNull(request.getSession().getAttribute("utenteLoggato"));
	}
	
	//TC_2.1_2 - Login corretto
	@Test
	public void testCase_3() throws ServletException, IOException{
		request.addParameter("user_email", "againsborough@yahoo.it");
		request.addParameter("user_password", "midgar03");

		servlet.doGet(request, response);
		
		assertEquals("logged", (String) request.getAttribute("result"));
		assertEquals("AerithGain", (String) request.getSession().getAttribute("utente"));
	}
}