package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import userManager.*;
import util.ConnectionPool;

class UtenteDAOTest {

	private static UtenteDAO utenteDAO = new UtenteDAO();
	
	
	@BeforeEach
	public void setUp() throws Exception{
		DatabaseHelper.initializeDatabase();
    }

	@AfterEach
    public void tearDown() throws Exception{
		ConnectionPool.setTest(false);
    }
	
	@Test
	void TestCheckLogin() throws SQLException {
		//login corretto
		Utente u = new Utente();
		u = utenteDAO.checkLogin("againsborough@yahoo.it", "midgar03");
		assertNotNull(u);
		
		//email errata
		u = utenteDAO.checkLogin("emailerrata", null);
		assertNull(u);
		
		u = utenteDAO.checkLogin("emailerrata", "midgar03");
		assertNull(u);
		
		//password errata
		u = utenteDAO.checkLogin("againsborough@yahoo.it", null);
		assertNull(u);
		
		u = utenteDAO.checkLogin("againsborough@yahoo.it", "midgar04");
		assertNull(u);
	}
	

	@Test
	void TestCheckEmail() throws SQLException {
		boolean flag = false;
		
		//email corretta
		flag = utenteDAO.checkEmail("againsborough@yahoo.it");
		assertEquals(flag, true);
		
		//email errata
		flag = utenteDAO.checkEmail("questaeunemail@errata.it");
		assertEquals(flag, false);
		
		//email==null
		flag = utenteDAO.checkEmail(null);
		assertEquals(flag, false);
	}
	
	@Test
	void TestCheckPassword() throws SQLException {
		boolean flag = false;
		
		//password corretta
		flag = utenteDAO.checkPassword("againsborough@yahoo.it", "midgar03");
		assertEquals(flag, true);
		
		//password errata
		flag = utenteDAO.checkPassword("againsborough@yahoo.it", "pass");
		assertEquals(flag, false);
		
		//password errata, ma di un altro utente
		flag = utenteDAO.checkPassword("againsborough@yahoo.it", "midgar01");
		assertEquals(flag, false);
		
		//password == null
		flag = utenteDAO.checkPassword("againsborough@yahoo.it", null);
		assertEquals(flag, false);
	}
	
	@Test
	void TestGetScore() throws SQLException{
		int score;
		//utente esistente
		score=utenteDAO.getScore("AerithGain");
		assertEquals(score, 390);
		
		//utente inesistente
		score=utenteDAO.getScore(null);
		assertEquals(score, -1);
	}
	
	@Test
	void TestFindTopUsers() throws SQLException{
		boolean flag = false;
		ArrayList<Utente> users = new ArrayList<Utente>();
		users = utenteDAO.findTopUsers();
		assertEquals(users.size(), 10); //verifico che l'array contiene la top 10
		
		for(int i=0; i<users.size()-1;i++){ //scorro l'array per vedere se i punteggi sono ordinati
			if(users.get(i).getPunteggio()<users.get(i+1).getPunteggio()) 
			flag=true; //se non lo sono cambio il flag
		}
		assertEquals(flag, false);
	}

}
