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
		addReq= "INSERT INTO richiestagioco(Utente,nomeGioco,fonte,publisher,anno,genere,risposta) values(?,?,?,?,?,?,?)";
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
	
	public ArrayList<RichiestaGioco> viewRequest(String ...strings) throws SQLException
	{
		viewReq="SELECT * FROM richiestagioco";
		ArrayList<RichiestaGioco> richieste=new ArrayList<RichiestaGioco>();
		int j=1;
		if(strings.length>0)
		{
			if(!Utilities.fieldOk(strings))
				throw new SQLException("parametri di ricerca errati");
			viewReq+=" WHERE ";
			for(int i=0;i<strings.length;i+=2)
			{					
				if(i!=strings.length-2)
					viewReq+=strings[i]+"=? AND ";
				else
					viewReq+=strings[i]+"=?;";
			}
		}
		try 
		{
			con=ConnectionPool.getConnection();
			statement=con.prepareStatement(viewReq);
			for(int i=1;i<strings.length;i+=2,j++) 
				statement.setString(j,strings[i]);
		
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
			deleteReq="DELETE FROM richiestagioco WHERE id=?";
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
