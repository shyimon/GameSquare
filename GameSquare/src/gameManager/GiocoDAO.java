package gameManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import threadManager.GameThread;
import util.*;

public class GiocoDAO {
	
	private static Connection con=null;
	private static PreparedStatement statement=null;
	private static ResultSet set=null;
	private static String viewGame;
	private static String addGame;
	private static String getPublishers;
	private static String getGenres;
	private static String updateAverage;

	
	public ArrayList<Gioco> viewGame(String ...strings) throws SQLException
	{
		viewGame="SELECT * FROM gioco";
		ArrayList<Gioco> giochi=new ArrayList<Gioco>();
		int j=1;
		if(strings.length>0)
		{
			if(!Utilities.fieldOk(strings))
				throw new SQLException("parametri di ricerca errati");
			viewGame+=" WHERE ";
			for(int i=0;i<strings.length;i+=2)
			{					
				if(i!=strings.length-2)
					viewGame+=strings[i]+"=? AND ";
				else
					viewGame+=strings[i]+"=?;";
			}
		}
		try 
		{
			con=ConnectionPool.getConnection();
			statement=con.prepareStatement(viewGame);
			for(int i=1;i<strings.length;i+=2,j++) 
				statement.setString(j,strings[i]);
		
			set=statement.executeQuery();
			while(set.next())
			{
				Gioco vidya=new Gioco();
				vidya.setIdGioco(set.getInt(1));
				vidya.setNome(set.getString(2));
				vidya.setDescrizione(set.getString(3));
				vidya.setPublisher(set.getString(4));
				vidya.setAnno(set.getString(5));
				vidya.setGenere(set.getString(6));
				vidya.setImgpath(set.getString(7));
				vidya.setPunteggio(set.getInt(8));
				vidya.setMediaVoti(set.getFloat(9));
				giochi.add(vidya);
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
		return giochi;
	}
	
	public ArrayList<String> GetPublishers() throws SQLException {
		getPublishers = "SELECT DISTINCT publisher FROM gioco;";
		ArrayList <String> results = new ArrayList<String>();
		try 
		{
			con=ConnectionPool.getConnection();
			statement=con.prepareStatement(getPublishers);
			set=statement.executeQuery();
			while(set.next())
			{
				String pub =set.getString(1);
				results.add(pub);
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
	
	public ArrayList<String> GetGenres() throws SQLException {
		getGenres = "SELECT DISTINCT genere FROM gioco;";
		ArrayList <String> results = new ArrayList<String>();
		try 
		{
			con=ConnectionPool.getConnection();
			statement=con.prepareStatement(getGenres);
			set=statement.executeQuery();
			while(set.next())
			{
				String pub =set.getString(1);
				results.add(pub);
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
	
	public static boolean addGame(Gioco game) throws SQLException 
	{
		addGame= "INSERT INTO gioco(nome,descrizione,publisher,anno,genere,imgpath, punteggio) values(?,?,?,?,?,?,?)";
		boolean flag=false;
		
		try 
		{
			con=ConnectionPool.getConnection();
			statement=con.prepareStatement(addGame);
			statement.setString(1,game.getNome());
			statement.setString(2,game.getDescrizione());
			statement.setString(3,game.getPublisher());
			statement.setString(4,game.getAnno());
			statement.setString(5,game.getGenere());
			statement.setString(6,game.getImgpath());
			statement.setInt(7,game.getPunteggio());
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

	public static boolean updateAverage(int gameID, float media) throws SQLException 
	{
		boolean flag=false;
		updateAverage = "UPDATE gioco SET media_voti=? WHERE id=?";
		try
		{
			con=ConnectionPool.getConnection();
			statement=con.prepareStatement(updateAverage);
			statement.setFloat(1,media);
			statement.setInt(2,gameID);
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

}
