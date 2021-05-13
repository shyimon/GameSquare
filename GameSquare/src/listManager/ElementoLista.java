package listManager;

public class ElementoLista {

	private String usernameUtente;
	private int idGioco;
	private String categoria;
	
	public ElementoLista() {
		
	}
	public ElementoLista(String user, int idGame, String category) {
		this.usernameUtente = user;
		this.idGioco = idGame;
		this.categoria = category;
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

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
}
