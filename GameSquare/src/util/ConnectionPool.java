package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



public class ConnectionPool {
	private static List<Connection> freeDbConnections;

	static {
		freeDbConnections = new LinkedList<Connection>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, e.getMessage(), e);
		}
	}

	private static synchronized Connection createDBConnection() throws SQLException {
		Connection newConnection = null;

		newConnection = DriverManager.getConnection(
				"jdbc:mysql://" + Costanti.dbIp + ":" + Costanti.dbPort + "/" + Costanti.dbName
						+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", //&useSSL=false"
						Costanti.dbUsername, Costanti.dbPassword);

		newConnection.setAutoCommit(false);
		return newConnection;
	}

	public static synchronized Connection getConnection() throws SQLException {
		Connection connection;

		if (!freeDbConnections.isEmpty()) {
			connection = (Connection) freeDbConnections.get(0);
			freeDbConnections.remove(0);

			try {
				if (connection.isClosed())
					connection = getConnection();
			} catch (SQLException e) {
				connection.close();
				connection = getConnection();
			}
		} else {
			connection = createDBConnection();
		}

		return connection;
	}

	public static synchronized void rilasciaConnessione(Connection connection) throws SQLException {
		if (connection != null)
			freeDbConnections.add(connection);
	}
}
