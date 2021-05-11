package threadManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import util.*;

public class ThreadDAO {

	private static Connection con=null;
	private static PreparedStatement statement=null;
	private static ResultSet set=null;
	private static String viewThread;
	private static String addThread;

	
	public GameThread viewThreadById(int idThread) throws SQLException
	{
		viewThread="SELECT * FROM thread WHERE id_thread=?";
		ArrayList<GameThread> threads=new ArrayList<GameThread>();
		
		try 
		{
			con=ConnectionPool.getConnection();
			statement=con.prepareStatement(viewThread);
			statement.setInt(1, idThread);
			set=statement.executeQuery();
			while(set.next())
			{
				GameThread disc=new GameThread();
				disc.setIdThread(set.getInt(1));
				disc.setTipoThread(set.getString(2));
				disc.setTitolo(set.getString(3));
				disc.setTesto(set.getString(4));
				disc.setUsernameUtente(set.getString(5));
				disc.setIdGioco(set.getInt(6));
				threads.add(disc);
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
		if(threads.size()==0) {
			return null;
		}
		return threads.get(0);
	}
	
	public ArrayList<GameThread> viewThreadByGame(int idGioco) throws SQLException
	{
		viewThread="SELECT * FROM thread WHERE id_gioco=?";
		ArrayList<GameThread> threads=new ArrayList<GameThread>();
		
		viewThread+=" ORDER BY id_thread DESC; ";
		
		try 
		{
			con=ConnectionPool.getConnection();
			statement=con.prepareStatement(viewThread);
			statement.setInt(1, idGioco);
			set=statement.executeQuery();
			while(set.next())
			{
				GameThread disc=new GameThread();
				disc.setIdThread(set.getInt(1));
				disc.setTipoThread(set.getString(2));
				disc.setTitolo(set.getString(3));
				disc.setTesto(set.getString(4));
				disc.setUsernameUtente(set.getString(5));
				disc.setIdGioco(set.getInt(6));
				threads.add(disc);
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
		return threads;
	}
	

	public static boolean addThread(GameThread thread) throws SQLException 
	{
		addThread= "INSERT INTO thread(tipo_thread,titolo,testo,utente,id_gioco) values(?,?,?,?,?)";
		boolean flag=false;

		try 
		{
			con=ConnectionPool.getConnection();
			statement=con.prepareStatement(addThread);
			statement.setString(1,thread.getTipoThread());
			statement.setString(2,thread.getTitolo());
			statement.setString(3,thread.getTesto());
			statement.setString(4,thread.getUsernameUtente());
			statement.setInt(5,thread.getIdGioco());
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
