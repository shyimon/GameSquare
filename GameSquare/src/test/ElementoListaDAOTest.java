package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.*;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import listManager.*;
import userManager.UtenteDAO;
import util.ConnectionPool;

class ElementoListaDAOTest {

	private static ElementoListaDAO elementoListaDAO = new ElementoListaDAO();
	
	@BeforeEach
	public void setUp() throws Exception{
		DatabaseHelper.initializeDatabase();
    }

	@AfterEach
    public void tearDown() throws Exception{
		ConnectionPool.setTest(false);
    }
	
	@Test
	void TestAddListElement() throws SQLException{
		//inserimento corretto
		ArrayList<ElementoLista> prev = elementoListaDAO.getUserList("Zagreus94");
		ElementoLista elem = new ElementoLista("Zagreus94", 2, "In corso");
		ElementoListaDAO.addListElement(elem);
		ArrayList<ElementoLista> now = elementoListaDAO.getUserList("Zagreus94");
		assertEquals(now.size(), prev.size()+1);
	}
	
	@Test
	void TestAddListElementException() {
		//inserimento di un gioco che non esiste
		ElementoLista elem = new ElementoLista("Zagreus94", 0, "In corso");
		try {
				ElementoListaDAO.addListElement(elem);
				fail("Il gioco non esiste");
			} catch (SQLException e) {
				//success
			}
		
		//inserimento da un utente che non esiste
		elem.setUsernameUtente(null);
		elem.setIdGioco(2);
		try {
				ElementoListaDAO.addListElement(elem);
				fail("L'utente non esiste");
			} catch (SQLException e) {
				//success
			}
		
		//inserimento di un elemento la cui chiave primaria è già presente
		elem.setUsernameUtente("AerithGain");
		elem.setCategoria("Completato");
		try {
				ElementoListaDAO.addListElement(elem);
				fail("Voce già presente");
			} catch (SQLException e) {
			//success
			}
	}
	
	@Test
	void TestGetUserList() throws SQLException{
		ArrayList<ElementoLista> lista = new ArrayList<ElementoLista>();
		//lista di un utente che ha aggiunto giochi
		lista = elementoListaDAO.getUserList("AerithGain");
		assertEquals(lista.size(), 3);
		
		//lista di un utente che non ha aggiunto giochi
		lista = elementoListaDAO.getUserList("CynthiaPT");
		assertEquals(lista.size(), 0);
		
		//lista di un utente che non esiste
		lista = elementoListaDAO.getUserList(null);
		assertEquals(lista.size(), 0);
	}
	
	@Test
	void TestGetListElement() throws SQLException{
		ElementoLista elem = new ElementoLista();
		//elemento esistente
		elem = elementoListaDAO.getListElement("AerithGain", 1);
		assertNotNull(elem);
		//elemento inesistente
		elem = elementoListaDAO.getListElement("AerithGain", 7);
		assertNull(elem);
		//gioco inesistente
		elem = elementoListaDAO.getListElement("AerithGain", -1);
		assertNull(elem);
		//utente inesistente
		elem = elementoListaDAO.getListElement(null, 1);
		assertNull(elem);
	}
	
	@Test
	void TestDeleteListElement() throws SQLException {
		//eliminazione corretta
		ArrayList<ElementoLista> prev = elementoListaDAO.getUserList("AerithGain");
		ElementoListaDAO.deleteListElement(2, "AerithGain");
		ArrayList<ElementoLista> now = elementoListaDAO.getUserList("AerithGain");
		assertEquals(now.size(), prev.size()-1);
	}
	
	@Test
	void TestUpdateCategory() throws SQLException {
		//aggiornamento corretto
		ArrayList<ElementoLista> prev = elementoListaDAO.getUserList("AerithGain");
		ElementoListaDAO.updateCategory("AerithGain", 2, "Completato");
		ElementoLista elem = elementoListaDAO.getListElement("AerithGain", 2);
		assertEquals(elem.getCategoria(), "Completato");
		ArrayList<ElementoLista> now = elementoListaDAO.getUserList("AerithGain");
		assertEquals(now.size(), prev.size());
		
		//se l'aggiornamento è fatto per un elemento che non esiste, continuerà a non esistere
		ElementoListaDAO.updateCategory("Zagreus94", 2, "Completato");
		elem = elementoListaDAO.getListElement("Zagreus94", 2);
		assertNull(elem);
	}
	
	@Test
	void TestGetGameName() throws SQLException{
		String game=null;
		//il gioco è stato aggiunto a una lista
		game = elementoListaDAO.getGameName(1);
		assertEquals(game, "Final Fantasy VII");
		//il gioco non è presente in nessuna lista
		game=elementoListaDAO.getGameName(8);
		assertEquals(game, null);
		//il gioco non è presente nel DB
		game=elementoListaDAO.getGameName(0);
		assertEquals(game, null);
	}
	
	@Test
	void TestGetGameScore() throws SQLException{
		int score;
		//il gioco è stato aggiunto a una lista
		score = elementoListaDAO.getGameScore(1);
		assertEquals(score, 100);
		//il gioco non è presente in nessuna lista
		score=elementoListaDAO.getGameScore(8);
		assertEquals(score, 0);
		//il gioco non è presente nel DB
		score=elementoListaDAO.getGameScore(0);
		assertEquals(score, 0);
	}
	
	@Test
	void TestUpdateUserScore() throws SQLException{
		UtenteDAO userDAO = new UtenteDAO();
		ElementoListaDAO.updateUserScore("AerithGain", 400);
		assertEquals(userDAO.getScore("AerithGain"), 400);
		//il punteggio non viene aggiornato se l'utente non ha aggiunto niente alla lista
		ElementoListaDAO.updateUserScore("ZackFair", 400);
		assertEquals(userDAO.getScore("ZackFair"), 0);
		
		ElementoListaDAO.updateUserScore(null, 400);
		assertEquals(userDAO.getScore(null), -1);
	}
	
	@Test
	void TestGetCategoryStats() throws SQLException{
		int count;
		//il gioco è stato aggiunto a una lista
		count = elementoListaDAO.getCategoryStats(1, "Platinato");
		assertEquals(count, 1);
		//il gioco è presente nelle liste, ma nessuno ha questa categoria
		count = elementoListaDAO.getCategoryStats(1, "In corso");
		assertEquals(count, 0);
		//il gioco non esiste
		count = elementoListaDAO.getCategoryStats(0, "In corso");
		assertEquals(count, 0);
	}
}
