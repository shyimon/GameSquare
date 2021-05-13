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
	private static String checkPassword;
	private static String showAccount;
	private static String showTopUsers;
	private static String checkLogin;

	static
	{
		addUtente="INSERT INTO utente(username,email,password) values(?,?,?)";
		checkEmail="SELECT username FROM utente where email=?";
		checkPassword="SELECT username FROM utente where email=? AND password=?";
		showAccount="SELECT * FROM utente where email=?";
		showTopUsers="SELECT * FROM utente ORDER BY punteggio DESC LIMIT 10";
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
	
	
	
	public boolean checkEmail(String Email) throws SQLException {	//check dell'email
		boolean flag=false;

		try 
		{
			con=ConnectionPool.getConnection();
			statement=con.prepareStatement(checkEmail);
			statement.setString(1,Email);
			set=statement.executeQuery();
			flag=set.next();
		} 		
		finally
		{
			try
			{
				if(statement!=null)
					statement.close();
			}
			finally
			{
				ConnectionPool.rilasciaConnessione(con);
			}
		}
		return flag;	
	}
	
	public boolean checkPassword(String email, String Pass) throws SQLException {	//check della password
		boolean flag=false;

		try 
		{
			con=ConnectionPool.getConnection();
			statement=con.prepareStatement(checkPassword);
			statement.setString(1,email);
			statement.setString(2,Pass);
			set=statement.executeQuery();
			flag=set.next();
		} 		
		finally
		{
			try
			{
				if(statement!=null)
					statement.close();
			}
			finally
			{
				ConnectionPool.rilasciaConnessione(con);
			}
		}
		return flag;	
	}
	
	public int getScore(String username) throws SQLException
	{
		String getScore="SELECT punteggio FROM utente WHERE username=?";
		int score= -1;
		
		try 
		{
			con=ConnectionPool.getConnection();
			statement=con.prepareStatement(getScore);
			statement.setString(1, username);
			set=statement.executeQuery();
			while(set.next())
			{
				score = set.getInt(1);
			}
		}
		finally
		{
			try
			{
				if(statement!=null)
					statement.close();
			}
			finally
			{
				ConnectionPool.rilasciaConnessione(con);
			}
		}
		return score;
	}
	
	public ArrayList<Utente> findTopUsers() throws SQLException
	{
		ArrayList<Utente> utenti=new ArrayList<Utente>();
		try 
		{
			con=ConnectionPool.getConnection();
			statement=con.prepareStatement(showTopUsers);
			set=statement.executeQuery();
			while(set.next())
			{
				Utente user=new Utente();
				user.setUsername(set.getString(1));
				user.setEmail(set.getString(2));
				user.setPassword(set.getString(3));
				user.setPunteggio(set.getInt(4));
				user.setTipo(set.getString(5));
				utenti.add(user);
			}
		}
		finally
		{
			try
			{
				if(statement!=null)
					statement.close();
			}
			finally
			{
				ConnectionPool.rilasciaConnessione(con);
			}
		}
		return utenti;
	}
}

