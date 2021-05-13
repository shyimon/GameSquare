package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gameManager.*;
import threadManager.GameThread;
import util.ConnectionPool;

class RichiestaGiocoDAOTest {

	private static RichiestaGiocoDAO richiestaGiocoDAO = new RichiestaGiocoDAO();
	
	@BeforeEach
	public void setUp() throws Exception{
		DatabaseHelper.initializeDatabase();
    }

	@AfterEach
    public void tearDown() throws Exception{
		ConnectionPool.setTest(false);
    }
	
	@Test
	void TestAddGameRequest() throws SQLException{
		//inserimento corretto
		ArrayList<RichiestaGioco> prev  = richiestaGiocoDAO.getAllRequests();
		RichiestaGioco r = new RichiestaGioco("AerithGain", "Dragon Quest VIII", "Square Enix", "2004", "RPG", "https://it.wikipedia.org/wiki/Dragon_Quest_VIII:_L%27odissea_del_re_maledetto");
		RichiestaGiocoDAO.addGameRequest(r);
		ArrayList<RichiestaGioco> now  = richiestaGiocoDAO.getAllRequests();
		assertEquals(now.size(), prev.size()+1);
	}
	
	@Test
	void TestAddGameRequestException() {
		//inserimento da un utente che non esiste
		RichiestaGioco r = new RichiestaGioco("nonesisto", "Dragon Quest VIII", "Square Enix", "2004", "RPG", "https://it.wikipedia.org/wiki/Dragon_Quest_VIII:_L%27odissea_del_re_maledetto");
		try {
				RichiestaGiocoDAO.addGameRequest(r);
				fail("L'utente non esiste");
			} catch (SQLException e) {
				//success
			}
	}
	
	@Test
	public void TestViewRequestById() throws SQLException{
		//la richiesta esiste
		int id = 1;
		RichiestaGioco r = new RichiestaGioco();
		r= richiestaGiocoDAO.viewRequestById(id);
		assertNotNull(r);
		
		//la richiesta non esiste
		id = -1;
		r= richiestaGiocoDAO.viewRequestById(id);
		assertNull(r);
		
	}
	
	@Test
	public void TestDeleteGameRequest() throws SQLException{
		ArrayList<RichiestaGioco> prev  = richiestaGiocoDAO.getAllRequests();
		
		//la richiesta non esiste
		int id = 0;
		RichiestaGiocoDAO.deleteGameRequest(id);
		ArrayList<RichiestaGioco> now  = richiestaGiocoDAO.getAllRequests();
		assertEquals(prev.size(), now.size());
		
		//la richiesta esiste
		id = 1;
		RichiestaGiocoDAO.deleteGameRequest(id);
		now  = richiestaGiocoDAO.getAllRequests();
		assertEquals(prev.size()-1, now.size());
		
	}
	
	@Test
	public void TestGetAllRequests() throws SQLException{
		ArrayList<RichiestaGioco> reqs  = richiestaGiocoDAO.getAllRequests();
		assertEquals(reqs.size(), 2);
	}
	

}
