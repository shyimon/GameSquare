package gameManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import threadManager.GameThread;
import util.ConnectionPool;

public class RichiestaGiocoDAO {

	private static Connection con=null;
	private static PreparedStatement statement=null;
	private static ResultSet set=null;
	private static String addReq;

	public static boolean addGameRequest(RichiestaGioco req) throws SQLException 
	{
		addReq= "INSERT INTO richiestagioco(nomeGioco,Fonte) values(?,?)";
		boolean flag=false;

		try 
		{
			con=ConnectionPool.getConnection();
			statement=con.prepareStatement(addReq);
			statement.setString(1,req.getNomeGioco());
			statement.setString(2,req.getFonte());
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
