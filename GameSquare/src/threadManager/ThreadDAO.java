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

	
	public ArrayList<GameThread> viewThread(String ...strings) throws SQLException
	{
		viewThread="SELECT * FROM thread";
		ArrayList<GameThread> threads=new ArrayList<GameThread>();
		int j=1;
		if(strings.length>0)
		{
			if(!Utilities.fieldOk(strings))
				throw new SQLException("parametri di ricerca errati");
			viewThread+=" WHERE ";
			for(int i=0;i<strings.length;i+=2)
			{					
				if(i!=strings.length-2)
					viewThread+=strings[i]+"=? AND ";
				else
					viewThread+=strings[i]+"=?";
			}
		}
		viewThread+=" ORDER BY idThread DESC; ";
		
		try 
		{
			con=ConnectionPool.getConnection();
			statement=con.prepareStatement(viewThread);
			for(int i=1;i<strings.length;i+=2,j++) 
				statement.setString(j,strings[i]);
		
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
	

}
