package userManager;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserLogout
 */
@WebServlet("/Logout")
public class UserLogout extends HttpServlet
{
	private static final long serialVersionUID = 1042434985115718411L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
			request.getSession(false).removeAttribute("utenteLoggato");
			request.getSession(false).removeAttribute("utente");
			request.getSession(false).invalidate();
			response.getWriter().write("Logout effettuato");
			response.sendRedirect(request.getContextPath() + "/logout.jsp");
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
}