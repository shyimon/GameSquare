package gameManager;

public class RichiestaGioco {

	private int idRichiesta;
	private String usernameUtente;
	private String nomeGioco;
	private String publisher;
	private String anno;
	private String genere;
	private String fonte;
	
	public RichiestaGioco() {
		
	}
	
	public RichiestaGioco(String utente, String nomeGioco, String publisher, String anno, String genere, String fonte) {
		this.usernameUtente = utente;
		this.nomeGioco = nomeGioco;
		this.publisher = publisher;
		this.anno = anno;
		this.genere = genere;
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
	
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getAnno() {
		return anno;
	}
	public void setAnno(String anno) {
		this.anno = anno;
	}
	public String getGenere() {
		return genere;
	}
	public void setGenere(String genere) {
		this.genere = genere;
	}
	
	
	
}
