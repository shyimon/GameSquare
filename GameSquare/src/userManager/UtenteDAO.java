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
	private static String showAccount;
	private static String showAllUsers;
	private static String checkLogin;

	static
	{
		addUtente="INSERT INTO utente(username,email,password) values(?,?,?)";
		checkEmail="SELECT nome FROM utente where email=?";
		showAccount="SELECT * FROM utente where email=?";
		showAllUsers="SELECT * FROM utente WHERE Tipo='user'";
		checkLogin="SELECT Email,Tipo,Nome,Cognome FROM utente where Email=? AND Password=?";
	}
	
	
}
