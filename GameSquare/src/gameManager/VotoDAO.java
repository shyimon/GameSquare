package gameManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import util.ConnectionPool;
import util.Utilities;

public class VotoDAO {
	private static Connection con=null;
	private static PreparedStatement statement=null;
	private static ResultSet set=null;
	private static String addVote;
	private static String getVote;
	private static String deleteVote;
	private static String updateVote;
	private static String average;
	
	public static boolean addVote(Voto voto) throws SQLException 
	{
		addVote= "INSERT INTO voto(valutazione,utente,id_gioco) values(?,?,?)";
		boolean flag=false;

		try 
		{
			con=ConnectionPool.getConnection();
			statement=con.prepareStatement(addVote);
			statement.setInt(1,voto.getValutazione());
			statement.setString(2,voto.getUsernameUtente());
			statement.setInt(3,voto.getIdGioco());
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
	
	public Voto getVote(int idGioco, String utente) throws SQLException
	{
		getVote="SELECT * FROM voto WHERE id_gioco=? AND utente=?";
		ArrayList<Voto> results=new ArrayList<Voto>();
		
		try 
		{
			con=ConnectionPool.getConnection();
			statement=con.prepareStatement(getVote);
			statement.setInt(1, idGioco);
			statement.setString(2, utente);
			set=statement.executeQuery();
			while(set.next())
			{
				Voto v =new Voto();
				v.setValutazione(set.getInt(1));
				v.setUsernameUtente(set.getString(2));
				v.setIdGioco(set.getInt(3));
				results.add(v);
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
		
		if(results.size()==0) {
			return null;
		}
		return results.get(0);
	}
	
	public static boolean deleteVote(int gameid, String user) throws SQLException
	{
		boolean flag=false;
		try
		{
			deleteVote="DELETE FROM voto WHERE id_gioco=? AND utente=?";
			con=ConnectionPool.getConnection();
			statement=con.prepareStatement(deleteVote);
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
	
	public static boolean updateVote(String user, int gameID, int voto) throws SQLException 
	{
		boolean flag=false;
		updateVote = "UPDATE voto SET valutazione=? WHERE utente=? AND id_gioco=?";
		try
		{
			con=ConnectionPool.getConnection();
			statement=con.prepareStatement(updateVote);
			statement.setInt(1,voto);
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
	
	public float calculateAverage(int gameid) throws SQLException
	{
		float avg = 0;
		average="SELECT AVG(valutazione) FROM voto WHERE id_gioco=?";
		
		try 
		{
			con=ConnectionPool.getConnection();
			statement=con.prepareStatement(average);
			statement.setInt(1, gameid);
			set=statement.executeQuery();
			while(set.next())
			{
				avg = set.getFloat(1);
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
		return avg;
	}
}
