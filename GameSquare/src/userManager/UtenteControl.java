package userManager;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class UtenteControl
 */
@WebServlet("/Utente")
public class UtenteControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
static UtenteDAO model_utente = new UtenteDAO();
	
    public UtenteControl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	/*	String action = request.getParameter("action");
		String username = request.getParameter("username");
		
		try
		{
			if(action != null)
			{
				if(action.equals("logout"))
				{
					model_utente.logout(request.getSession());
				}
			
			}
			
		}catch(Exception e){e.printStackTrace();}
		
		RequestDispatcher dispatcher;
		if (action.equals("logout"))
			dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
	
			else
				dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
		
			dispatcher.forward(request, response);*/
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
