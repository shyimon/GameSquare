package userManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;


import util.*;

public class UtenteDAO {

	private static Connection con;
	private static PreparedStatement statement;
	private static ResultSet set;
	private static String addUtente;
	private static String checkEmail;
	private static String showAccount;
	private static String showAllUsers;
	private static String checkLogin;

	static
	{
		addUtente="INSERT INTO utente(username,email,password) values(?,?,?)";
		checkEmail="SELECT nome FROM utente where email=?";
		showAccount="SELECT * FROM utente where email=?";
		showAllUsers="SELECT * FROM utente WHERE Tipo='user'";
		checkLogin="SELECT username,email,password,punteggio,tipo FROM utente where email=? AND password=?";
	}
	
	
	public Utente checkLogin(String Email,String password) throws SQLException {	//login
		Utente utenteLoggato = null;
		
		try 
		{
			con=ConnectionPool.getConnection();
			statement=con.prepareStatement(checkLogin);
			statement.setString(1,Email);
			statement.setString(2,password);
			set=statement.executeQuery();
		} 		
		finally
		{
			try
			{
				while(set.next()) 
				{
					utenteLoggato = new Utente();
					utenteLoggato.setUsername(set.getString(1));
					utenteLoggato.setEmail(set.getString(2));
					utenteLoggato.setPassword(set.getString(3));
					utenteLoggato.setPunteggio(set.getInt(4));
					utenteLoggato.setTipo(set.getString(5));

				}
					
			}
			finally
			{
				ConnectionPool.rilasciaConnessione(con);
			}
		}
		return utenteLoggato;	
	}
	
	
	public synchronized void logout(HttpSession session) throws SQLException {	//logout dell'utente
		
		synchronized(session) 
		{
			session.invalidate();
		}
	}
	
}
