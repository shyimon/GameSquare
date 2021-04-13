package gameManager;

public class RichiestaGioco {

	private int idRichiesta;
	private String usernameUtente;
	private String nomeGioco;
	private String fonte;
	private boolean risposta;
	
	public RichiestaGioco(int idRichiesta, String nomeGioco, String fonte) {
		this.idRichiesta = idRichiesta;
		this.nomeGioco = nomeGioco;
		this.fonte = fonte;
	}

	public int getIdRichiesta() {
		return idRichiesta;
	}

	public void setIdRichiesta(int idRichiesta) {
		this.idRichiesta = idRichiesta;
	}

	public String getUsernameUtente() {
		return usernameUtente;
	}

	public void setUsernameUtente(String usernameUtente) {
		this.usernameUtente = usernameUtente;
	}

	public String getNomeGioco() {
		return nomeGioco;
	}

	public void setNomeGioco(String nomeGioco) {
		this.nomeGioco = nomeGioco;
	}

	public String getFonte() {
		return fonte;
	}

	public void setFonte(String fonte) {
		this.fonte = fonte;
	}

	public void setRisposta(boolean risposta) {
		this.risposta = risposta;
	}
	
}
