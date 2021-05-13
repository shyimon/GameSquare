package gameManager;

public class Voto {

	private int valutazione;
	private String usernameUtente;
	private int idGioco;
	
	public Voto(int valutazione, String utente, int idGioco) {
		this.valutazione = valutazione;
		this.usernameUtente = utente;
		this.idGioco = idGioco;
	}
	public Voto() {
	
	}

	public int getValutazione() {
		return valutazione;
	}

	public void setValutazione(int valutazione) {
		this.valutazione = valutazione;
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
