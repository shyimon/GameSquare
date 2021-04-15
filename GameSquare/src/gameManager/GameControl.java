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
							request.removeAttribute("game");
			
							String gameID = request.getParameter("id");
							ArrayList<Gioco> app=gameModel.viewGame("id", gameID);
							request.setAttribute("game", app.get(0));
					}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			}	
	
	
	RequestDispatcher dispatcher;
	if(action.equals("gioco")) 
		dispatcher = getServletContext().getRequestDispatcher("/pagina-gioco.jsp");
	else
		dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
	
	dispatcher.forward(request, response);
	
	}
	
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
