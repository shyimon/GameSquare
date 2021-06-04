package gameManager;

/**
 * Questa classe rappresenta l'entità Richiesta per l'aggiunta di un nuovo gioco.
 * @author Francesco Galasso
 */
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
	
	/**
	 * @param usernameUtente username dell'utente che ha richiesto il gioco
	 * @param nomeGioco titolo del gioco
	 * @param publisher nome dell'editore che ha pubblicato il gioco
	 * @param anno anno di uscita del gioco
	 * @param genere genere del gioco
	 * @param fonte link ad una fonte attendibile che attesti l'esistenza del gioco richiesto
	 * @return 
	 */
	public RichiestaGioco(String utente, String nomeGioco, String publisher, String anno, String genere, String fonte) {
		this.usernameUtente = utente;
		this.nomeGioco = nomeGioco;
		this.publisher = publisher;
		this.anno = anno;
		this.genere = genere;
		this.fonte = fonte;
	}

	/**
	 * @return idRichiesta id della richiesta
	 */
	public int getIdRichiesta() {
		return idRichiesta;
	}

	/**
	 * @param idRichiesta id da assegnare alla richiesta
	 * @return 
	 */
	public void setIdRichiesta(int idRichiesta) {
		this.idRichiesta = idRichiesta;
	}

	/**
	 * @return usernameUtente username dell'utente che ha effettuato la richiesta
	 */
	public String getUsernameUtente() {
		return usernameUtente;
	}

	/**
	 * @param usernameUtente username dell'utente da assegnare alla richiesta
	 * @return 
	 */
	public void setUsernameUtente(String usernameUtente) {
		this.usernameUtente = usernameUtente;
	}

	/**
	 * @return nomeGioco nome del gioco richiesto
	 */
	public String getNomeGioco() {
		return nomeGioco;
	}

	/**
	 * @param nomeGioco nome del gioco da assegnare alla richiesta
	 * @return 
	 */
	public void setNomeGioco(String nomeGioco) {
		this.nomeGioco = nomeGioco;
	}

	/**
	 * @return fonte fonte del gioco richiesto
	 */
	public String getFonte() {
		return fonte;
	}

	/**
	 * @param fonte fonte del gioco da assegnare alla richiesta
	 * @return 
	 */
	public void setFonte(String fonte) {
		this.fonte = fonte;
	}
	
	/**
	 * @return publisher editore del gioco richiesto
	 */
	public String getPublisher() {
		return publisher;
	}
	
	/**
	 * @param publisher editore del gioco da assegnare alla richiesta
	 * @return 
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	/**
	 * @return anno anno di uscita del gioco richiesto
	 */
	public String getAnno() {
		return anno;
	}
	
	/**
	 * @param anno anno di uscita del gioco da assegnare alla richiesta
	 * @return 
	 */
	public void setAnno(String anno) {
		this.anno = anno;
	}
	
	/**
	 * @return genere genere del gioco richiesto
	 */
	public String getGenere() {
		return genere;
	}
	
	/**
	 * @param genere genere del gioco da assegnare alla richiesta
	 * @return 
	 */
	public void setGenere(String genere) {
		this.genere = genere;
	}
	
	
	
}
