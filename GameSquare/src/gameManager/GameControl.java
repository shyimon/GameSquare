package gameManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class GameControl
 */
@WebServlet("/Game")
public class GameControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GameControl() {
        super();
    }

    static GiocoDAO gameModel = new GiocoDAO();
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
	try {
		if(action != null) 
			{	
				if(action.equals("gioco"))
					{
							System.out.println(action);
							request.removeAttribute("game");
			
							String gameID = request.getParameter("id");
							System.out.println(gameID);
							ArrayList<Gioco> app=gameModel.viewGame("id", gameID);
							request.setAttribute("game", app.get(0));
					}
				else if(action.equals("findall"))
				{		
					System.out.println(action);
					request.getSession().removeAttribute("giochi");	
					request.removeAttribute("action_name");
					ArrayList<Gioco> app=gameModel.viewGame();
					request.setAttribute("action_name", "Tutti i giochi");
					request.getSession().setAttribute("giochi", app);	
				}
				else if(action.equals("publisher"))
				{		
					System.out.println(action);
					request.removeAttribute("action_name");
					request.getSession().removeAttribute("giochi");	
					String publisher = request.getParameter("pub");
					ArrayList<Gioco> app=gameModel.viewGame("publisher", publisher);
					request.setAttribute("action_name", "Ricerca per publisher: "+publisher);
					request.getSession().setAttribute("giochi", app);	
				
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			}	
	
	
	RequestDispatcher dispatcher;
	if(action.equals("gioco")) 
		dispatcher = getServletContext().getRequestDispatcher("/pagina-gioco.jsp");
	else if(action.equals("findall")) 
		dispatcher = getServletContext().getRequestDispatcher("/catalogo.jsp");
	else if(action.equals("publisher")) 
		dispatcher = getServletContext().getRequestDispatcher("/catalogo.jsp");
	else
		dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
	
	dispatcher.forward(request, response);
	
	}
	
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
