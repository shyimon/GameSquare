package gameManager;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class CreateGameReq
 */
@WebServlet("/CreateGameReq")
public class CreateGameReq extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public CreateGameReq() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String title = null;
		String source = null;
		String username = null;
		
		title = request.getParameter("gamereq_title");
		source = request.getParameter("gamereq_source");
		username = request.getParameter("username");
		
		System.out.println(title+" "+source+" "+username); //test
		//creazione del nuovo thread
		RichiestaGioco req = new RichiestaGioco();
		
	
		req.setNomeGioco(title);
		req.setFonte(source);
		req.setUsernameUtente(username);
		req.setRisposta(false);
		
	
		
		try {
			
				if(RichiestaGiocoDAO.addGameRequest(req))
				{
					
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/GameSquare/index.jsp");
					dispatcher.forward(request, response);
					
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		doPost(request, response);
	}

}