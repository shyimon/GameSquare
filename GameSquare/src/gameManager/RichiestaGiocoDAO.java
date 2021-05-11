package gameManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.ConnectionPool;
import util.Utilities;

public class RichiestaGiocoDAO {

	private static Connection con=null;
	private static PreparedStatement statement=null;
	private static ResultSet set=null;
	private static String addReq;
	private static String viewReq;
	private static String deleteReq;

	public static boolean addGameRequest(RichiestaGioco req) throws SQLException 
	{
		addReq= "INSERT INTO richiesta_gioco(utente,nome_gioco,fonte,publisher,anno,genere,risposta) values(?,?,?,?,?,?,?)";
		boolean flag=false;

		try 
		{
			con=ConnectionPool.getConnection();
			statement=con.prepareStatement(addReq);
			statement.setString(1,req.getUsernameUtente());
			statement.setString(2,req.getNomeGioco());
			statement.setString(3,req.getFonte());
			statement.setString(4,req.getPublisher());
			statement.setString(5,req.getAnno());
			statement.setString(6,req.getGenere());
			statement.setBoolean(7,req.getRisposta());
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
	
	
	
	public RichiestaGioco viewRequestById(int id) throws SQLException
	{
		viewReq="SELECT * FROM richiesta_gioco WHERE id=?";
		ArrayList<RichiestaGioco> richieste=new ArrayList<RichiestaGioco>();
		
		try 
		{
			con=ConnectionPool.getConnection();
			statement=con.prepareStatement(viewReq);
			statement.setInt(1, id);
			set=statement.executeQuery();
			while(set.next())
			{
				RichiestaGioco req=new RichiestaGioco();
				req.setIdRichiesta(set.getInt(1));
				req.setUsernameUtente(set.getString(2));
				req.setNomeGioco(set.getString(3));
				req.setPublisher(set.getString(4));
				req.setAnno(set.getString(5));
				req.setGenere(set.getString(6));
				req.setFonte(set.getString(7));
				req.setRisposta(set.getBoolean(8));
				richieste.add(req);
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
		if(richieste.size()!=0) {
			return richieste.get(0);
			} else {
				return null;
			}
	}
	
	public ArrayList<RichiestaGioco> getAllRequests() throws SQLException
	{
		viewReq="SELECT * FROM richiesta_gioco";
		ArrayList<RichiestaGioco> richieste=new ArrayList<RichiestaGioco>();

		try 
		{
			con=ConnectionPool.getConnection();
			statement=con.prepareStatement(viewReq);;
			set=statement.executeQuery();
			while(set.next())
			{
				RichiestaGioco req=new RichiestaGioco();
				req.setIdRichiesta(set.getInt(1));
				req.setUsernameUtente(set.getString(2));
				req.setNomeGioco(set.getString(3));
				req.setPublisher(set.getString(4));
				req.setAnno(set.getString(5));
				req.setGenere(set.getString(6));
				req.setFonte(set.getString(7));
				req.setRisposta(set.getBoolean(8));
				richieste.add(req);
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
		return richieste;
	}
	
	public static boolean deleteGameRequest(int id) throws SQLException
	{
		boolean flag=false;
		try
		{
			deleteReq="DELETE FROM richiesta_gioco WHERE id=?";
			con=ConnectionPool.getConnection();
			statement=con.prepareStatement(deleteReq);
			statement.setInt(1,id);
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
