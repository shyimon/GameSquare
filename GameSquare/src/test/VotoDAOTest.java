package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gameManager.*;
import util.ConnectionPool;

class VotoDAOTest {

	private static VotoDAO votoDAO = new VotoDAO();
	
	@BeforeEach
	public void setUp() throws Exception{
		DatabaseHelper.initializeDatabase();
    }

	@AfterEach
    public void tearDown() throws Exception{
		ConnectionPool.setTest(false);
    }
	
	
	@Test
	void TestAddVote() throws SQLException{
		//inserimento corretto
		Voto voto = new Voto(8, "Zagreus94", 12);
		
		ArrayList <Voto> prev = votoDAO.findAllVotes();
		VotoDAO.addVote(voto);
		ArrayList <Voto> next = votoDAO.findAllVotes();
		assertEquals(prev.size()+1, next.size());
	}
	
	@Test
	void TestAddVoteException(){
		//il gioco non esiste
		Voto voto = new Voto(8, "Zagreus94", 0);
		try {
				VotoDAO.addVote(voto);
				fail("Gioco inesistente");
			} catch (SQLException e) {
			// success
			}
		//l'utente non esiste
		voto = new Voto(8, null, 0);
		try {
				VotoDAO.addVote(voto);
				fail("Utente inesistente");
			} catch (SQLException e) {
				// success
			}
		//chiave primaria già presente
		voto = new Voto(8, "AerithGain", 1);
		try {
				VotoDAO.addVote(voto);
				fail("Chiave primaria dev'essere unica");
			} catch (SQLException e) {
				// success
			}
	}
	
	@Test
	void TestGetVote() throws SQLException{
		//voto esistente
		Voto voto = votoDAO.getVote(1, "AerithGain");
		assertNotNull(voto);
		//voto non esistente
		voto = votoDAO.getVote(1, "Zagreus94");
		assertNull(voto);
		voto = votoDAO.getVote(0, "AerithGain");
		assertNull(voto);
		voto = votoDAO.getVote(1, null);
		assertNull(voto);
	}

	@Test
	void TestDeleteVote() throws SQLException {
		//eliminazione elemento inesistente
		ArrayList <Voto> prev = votoDAO.findAllVotes();
		VotoDAO.deleteVote(8, "AerithGain");
		ArrayList <Voto> next = votoDAO.findAllVotes();
		assertEquals(prev.size(), next.size());
		
		//eliminazione corretta
		VotoDAO.deleteVote(1, "AerithGain");
		next = votoDAO.findAllVotes();
		assertEquals(prev.size()-1, next.size());
		
	}
	
	@Test
	void TestUpdateVote() throws SQLException {
		ArrayList <Voto> prev = votoDAO.findAllVotes();
		//modifica corretta
		Voto voto = votoDAO.getVote(1, "AerithGain");
		int previous = voto.getValutazione();
		VotoDAO.updateVote("AerithGain", 1, 8);
		voto = votoDAO.getVote(1, "AerithGain");
		int current = voto.getValutazione();
		assertNotEquals(previous, current);
		ArrayList <Voto> next = votoDAO.findAllVotes();
		assertEquals(prev.size(), next.size());
		
		//elemento inesistente
		VotoDAO.updateVote(null, 0, 7);
		next = votoDAO.findAllVotes();
		assertEquals(prev.size(), next.size());
		
	}
	
	@Test
	void TestUpdateSameVote() throws SQLException {
		//modifica con lo stesso voto
		ArrayList <Voto> prev = votoDAO.findAllVotes();
		Voto voto = votoDAO.getVote(1, "AerithGain");
		int previous = voto.getValutazione();
		VotoDAO.updateVote("AerithGain", 1, 9);
		voto = votoDAO.getVote(1, "AerithGain");
		int current = voto.getValutazione();
		assertEquals(previous, current);
		ArrayList <Voto> next = votoDAO.findAllVotes();
		assertEquals(prev.size(), next.size());
		
	}
	
	@Test
	void TestCalculateAverage() throws SQLException {
		float avg = 0;
		//gioco con più di un voto
		avg = votoDAO.calculateAverage(1);
		assertEquals(avg, 8.5);
		//gioco con un solo voto
		avg = votoDAO.calculateAverage(3);
		assertEquals(avg, 10);
		//gioco non presente
		avg = votoDAO.calculateAverage(0);
		assertEquals(avg, 0);
		
	}
	
}
