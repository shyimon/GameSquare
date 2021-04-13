package threadManager;

public class GameThread {

	private int idThread;
	private String tipoThread;
	private String titolo;
	private String testo;
	private String usernameUtente;
	private int idGioco;
	
	public GameThread() {
		
	}
	
	public GameThread(int idThread, String tipoThread, String titolo, String testo) {
		this.idThread = idThread;
		this.tipoThread = tipoThread;
		this.titolo = titolo;
		this.testo = testo;
	}

	public int getIdThread() {
		return idThread;
	}
	
	public void setIdThread(int idThread) {
		this.idThread = idThread;
	}
	
	public String getTipoThread() {
		return tipoThread;
	}

	public void setTipoThread(String tipoThread) {
		this.tipoThread = tipoThread;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public String getUsernameUtente() {
		return usernameUtente;
	}

	public void setUsernameUtente(String usernameUtente) {
		this.usernameUtente = usernameUtente;
	}

	public int getIdGioco() {
		return idGioco;
	}

	public void setIdGioco(int idGioco) {
		this.idGioco = idGioco;
	}
	
}
