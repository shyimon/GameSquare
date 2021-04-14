package userManager;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class UserLogin
 */
@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static UtenteDAO model_utente=new UtenteDAO();
    
    public UserLogin() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.removeAttribute("login");
		
		String user_email = request.getParameter("user_email");
		String user_password = request.getParameter("user_password");
		
		String redirectedPage = "";
		
		HttpSession session = request.getSession(true);
		synchronized (session) {
		
			try 
			{
				Utente utenteLoggato = model_utente.checkLogin(user_email, user_password);
				
				if(utenteLoggato!= null)
				{				
					session.setAttribute("utenteLoggato", utenteLoggato);
					session.setAttribute("email", user_email);
					request.setAttribute("login", true);
					redirectedPage = "/index.jsp";
					
				}
				else
				{
					request.setAttribute("login", false);
					redirectedPage = "/login-page.jsp";
				}
			}
				 catch (Exception e) {
					 System.out.println("Servlet error");
				e.printStackTrace();
				}
			}
			
		RequestDispatcher dispatcher = request.getRequestDispatcher(redirectedPage);
		dispatcher.forward(request, response);
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


}