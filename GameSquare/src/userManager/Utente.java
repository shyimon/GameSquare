package userManager;

/**
 * Questa classe rappresenta l'entità Utente.
 * @author Francesco Galasso
 */

public class Utente {

	private String tipo;
	private String username;
	private String email;
	private String password;
	private int punteggio;
	
	public Utente () {}

	
	/**
	 * @param username username dell'utente
	 * @param email indirizzo email dell'utente
	 * @param password password dell'utente
	 * @return 
	 */
	public Utente(String username,String email, String password) 
	{
		this.username = username;
		this.email = email;
		this.password = password;
		
	}
	
	/**
	 * @return tipo tipologia di utente(moderatore, utente standard, gestore catalogo o sviluppatore)
	 */
	public String getTipo() 
	{
		return tipo;
	}

	/**
	 * @param tipo tipo da assegnare all'utente
	 * @return 
	 */
	public void setTipo(String tipo)
	{
		this.tipo = tipo;
	}

	/**
	 * @return email indirizzo email dell'utente
	 */
	public String getEmail() 
	{
		return email;
	}

	/**
	 * @param email indirizzo email da assegnare all'utente
	 * @return 
	 */
	public void setEmail(String email) 
	{
		this.email = email;
	}

	/**
	 * @return username username dell'utente
	 */
	public String getUsername() 
	{
		return username;
	}

	/**
	 * @param username username da assegnare all'utente
	 * @return 
	 */
	public void setUsername(String username) 
	{
		this.username = username;
	}

	/**
	 * @return password password dell'utente
	 */
	public String getPassword() 
	{
		return password;
	}

	/**
	 * @param password password da assegnare all'utente
	 * @return 
	 */
	public void setPassword(String password) 
	{
		this.password = password;
	}

	/**
	 * @return punteggio punteggio abilità dell'utente
	 */
	public int getPunteggio() 
	{
		return punteggio;
	}

	/**
	 * @param punteggio punteggio abilità da assegnare all'utente
	 * @return 
	 */
	public void setPunteggio(int punteggio)
	{
		this.punteggio = punteggio;
	}

	@Override
	public String toString() 
	{
		return "email=" + email + ", username=" + username + ", tipo="+ tipo + ", punteggio=" + punteggio ;
	}

}
