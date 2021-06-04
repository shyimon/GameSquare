package threadManager;

/**
 * Questa classe rappresenta l'entità Thread (Discussione/Post su un particolare gioco).
 * @author Francesco Galasso
 */
public class GameThread {

	private int idThread;
	private String tipoThread;
	private String titolo;
	private String testo;
	private String usernameUtente;
	private int idGioco;
	
	public GameThread() {
		
	}
	
	/**
	 * @param idThread id del thread
	 * @param tipoThread tipologia del thread
	 * @param titolo titolo assegnato al thread
	 * @param testo testo del thread
	 * @return 
	 */
	public GameThread(int idThread, String tipoThread, String titolo, String testo) {
		this.idThread = idThread;
		this.tipoThread = tipoThread;
		this.titolo = titolo;
		this.testo = testo;
	}

	/**
	 * @return idThread id del thread
	 */
	public int getIdThread() {
		return idThread;
	}
	
	/**
	 * @param idThread id da assegnare al thread
	 * @return 
	 */
	public void setIdThread(int idThread) {
		this.idThread = idThread;
	}
	
	/**
	 * @return tipoThread tipo del thread
	 */
	public String getTipoThread() {
		return tipoThread;
	}

	/**
	 * @param tipoThread tipo da assegnare al thread
	 * @return 
	 */
	public void setTipoThread(String tipoThread) {
		this.tipoThread = tipoThread;
	}

	/**
	 * @return titolo titolo del thread
	 */
	public String getTitolo() {
		return titolo;
	}

	/**
	 * @param titolo titolo da assegnare al thread
	 * @return 
	 */
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	/**
	 * @return testo testo del thread
	 */
	public String getTesto() {
		return testo;
	}

	/**
	 * @param testo testo da assegnare al thread
	 * @return 
	 */
	public void setTesto(String testo) {
		this.testo = testo;
	}

	/**
	 * @return usernameUtente username dell'utente che ha avviato il thread
	 */
	public String getUsernameUtente() {
		return usernameUtente;
	}

	/**
	 * @param usernameUtente username dell'utente da assegnare al thread
	 * @return 
	 */
	public void setUsernameUtente(String usernameUtente) {
		this.usernameUtente = usernameUtente;
	}

	/**
	 * @return idGioco id del gioco su cui è stato avviato il thread
	 */
	public int getIdGioco() {
		return idGioco;
	}

	/**
	 * @param idGioco id del gioco da assegnare al thread
	 * @return 
	 */
	public void setIdGioco(int idGioco) {
		this.idGioco = idGioco;
	}
	
}
