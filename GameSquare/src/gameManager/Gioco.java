package gameManager;

public class Gioco {


	private int idGioco;
	private String nome;
	private String descrizione;
	private String publisher;
	private String anno;
	private String genere;
	private String imgpath;
	private int punteggio;
	private float mediaVoti;
	
	public Gioco() {
		
	}
	
	public Gioco(String nome, String descrizione, String publisher, String anno, String genere) {
		this.nome = nome;
		this.descrizione = descrizione;
		this.publisher = publisher;
		this.anno = anno;
		this.genere = genere;

	}
	
	
	public int getIdGioco() {
		return idGioco;
	}
	public void setIdGioco(int idGioco) {
		this.idGioco = idGioco;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
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
	public String getImgpath() {
		return imgpath;
	}
	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}
	public int getPunteggio() {
		return punteggio;
	}
	public void setPunteggio(int punteggio) {
		this.punteggio = punteggio;
	}
	public float getMediaVoti() {
		return mediaVoti;
	}
	public void setMediaVoti(float mediaVoti) {
		this.mediaVoti = mediaVoti;
	}
	@Override
	public String toString() {
		return "Gioco [idGioco=" + idGioco + ", nome=" + nome + ", descrizione=" + descrizione + ", publisher="
				+ publisher + ", anno=" + anno + ", genere=" + genere + ", imgpath=" + imgpath + ", punteggio="
				+ punteggio + ", mediaVoti=" + mediaVoti + "]";
	}
	
	
	
}
