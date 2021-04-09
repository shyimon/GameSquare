package userManager;

public class Utente {

	private String tipo;
	private String username;
	private String email;
	private String password;
	private int punteggio;
	
	public Utente () {}

	public Utente(String username,String email, String password) 
	{
		this.username = username;
		this.email = email;
		this.password = password;
		
	}
	
	public String getTipo() 
	{
		return tipo;
	}

	public void setTipo(String tipo)
	{
		this.tipo = tipo;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public String getUsername() 
	{
		return username;
	}

	public void setUsername(String username) 
	{
		this.username = username;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

	public int getPunteggio() 
	{
		return punteggio;
	}

	public void setPunteggio(int punteggio)
	{
		this.punteggio = punteggio;
	}


}
