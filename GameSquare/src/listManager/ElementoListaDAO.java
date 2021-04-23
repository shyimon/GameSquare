package listManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.ConnectionPool;
import util.Utilities;

public class ElementoListaDAO {
	private static Connection con=null;
	private static PreparedStatement statement=null;
	private static ResultSet set=null;
	private static String addToList;
	private static String getList;
	private static String deleteFromList;
	private static String updateCategory;
	private static String GetGameName;
	private static String GetGameScore;
	private static String updateScore;
	private static String GetStats;
	
	public static boolean addListElement(ElementoLista elem) throws SQLException 
	{
		addToList= "INSERT INTO elementolista (Utente,IdGioco,categoria) values(?,?,?)";
		boolean flag=false;

		try 
		{
			con=ConnectionPool.getConnection();
			statement=con.prepareStatement(addToList);
			statement.setString(1,elem.getUsernameUtente());
			statement.setInt(2,elem.getIdGioco());
			statement.setString(3,elem.getCategoria());
			flag=statement.executeUpdate()>0;
			con.commit();
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
	
	public ArrayList <ElementoLista> getUserList(String username) throws SQLException
	{
		getList="SELECT * FROM voto WHERE Utente=?";
		ArrayList<ElementoLista> results=new ArrayList<ElementoLista>();
		
		try 
		{
			con=ConnectionPool.getConnection();
			statement=con.prepareStatement(getList);
			statement.setString(1, username);
			set=statement.executeQuery();
			while(set.next())
			{
				ElementoLista el =new ElementoLista();
				el.setUsernameUtente(set.getString(1));
				el.setIdGioco(set.getInt(2));
				el.setCategoria(set.getString(3));
				results.add(el);
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
		return results;
	}
	
	public static boolean deleteListElement(int gameid, String user) throws SQLException
	{
		boolean flag=false;
		try
		{
			deleteFromList="DELETE FROM elementolista WHERE IdGioco=? AND Utente=?";
			con=ConnectionPool.getConnection();
			statement=con.prepareStatement(deleteFromList);
			statement.setInt(1,gameid);
			statement.setString(2,user);
			flag=statement.executeUpdate()>0;
			con.commit();
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
	
	public static boolean updateCategory(String user, int gameID, String categoria) throws SQLException 
	{
		boolean flag=false;
		updateCategory = "UPDATE elementolista SET categoria=? WHERE Utente=? AND IdGioco=?";
		try
		{
			con=ConnectionPool.getConnection();
			statement=con.prepareStatement(updateCategory);
			statement.setString(1,categoria);
			statement.setString(2,user);
			statement.setInt(3,gameID);
			flag=statement.executeUpdate()>0;
			con.commit();
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
	
	public String getGameName(int gameID) throws SQLException
	{
		GetGameName="SELECT DISTINCT nome FROM elementolista JOIN gioco ON gioco.id=elementoLista.IdGioco WHERE IdGioco=?";
		String name=null;
		
		try 
		{
			con=ConnectionPool.getConnection();
			statement=con.prepareStatement(GetGameName);
			statement.setInt(1, gameID);
			set=statement.executeQuery();
			while(set.next())
			{
				name = set.getString(1);
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
		return name;
	}
	public int getGameScore(int gameID) throws SQLException
	{
		GetGameScore="SELECT DISTINCT punteggio FROM elementolista JOIN gioco ON gioco.id=elementoLista.IdGioco WHERE IdGioco=?";
		int score=0;
		
		try 
		{
			con=ConnectionPool.getConnection();
			statement=con.prepareStatement(GetGameScore);
			statement.setInt(1, gameID);
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
	
	public static boolean updateUserScore(String user, int punteggio) throws SQLException 
	{
		boolean flag=false;
		updateScore = "update elementolista join utente on utente.username=elementoLista.Utente set punteggio=? where utente=?";
		try
		{
			con=ConnectionPool.getConnection();
			statement=con.prepareStatement(updateScore);
			statement.setInt(1,punteggio);
			statement.setString(2,user);
			flag=statement.executeUpdate()>0;
			con.commit();
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
	
	public int getCategoryStats(int gameID, String categoria) throws SQLException
	{
		GetStats="SELECT count(*) FROM elementolista WHERE IdGioco=? and categoria=?";
		int count=0;
		
		try 
		{
			con=ConnectionPool.getConnection();
			statement=con.prepareStatement(GetStats);
			statement.setInt(1, gameID);
			statement.setString(2, categoria);
			set=statement.executeQuery();
			while(set.next())
			{
				count = set.getInt(1);
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
		return count;
	}
}
