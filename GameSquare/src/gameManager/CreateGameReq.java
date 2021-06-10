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
 * Questa classe è un control che si occupa di passare a RichiestaGiocoDAO i dati di una richiesta gioco da pubblicare.
 */
@WebServlet("/CreateGameReq")
public class CreateGameReq extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public CreateGameReq() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
 	 * @precondition request.getParameter(“gamereq_title”)!=null AND request.getParameter(“gamereq_source”)!=null AND request.getParameter(“publisher”)!=null AND request.getParameter(“game_genre”)!=null AND request.getParameter(“game_year”)!=null AND request.getParameter(“username”)!=null
 	 * @postcondition request.getAttribute("richiestagioco")!=null
 	 * @throws ServletException, IOException
 	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
    	request.removeAttribute("richiestagioco");
		
    	String title = null;
		String source = null;
		String publisher = null;
		String genere = null;
		String anno = null;
		String username = null;
		
		title = request.getParameter("gamereq_title");
		source = request.getParameter("gamereq_source");
		publisher = request.getParameter("publisher");
		genere = request.getParameter("game_genre");
		anno = request.getParameter("game_year");
		username = request.getParameter("username");
		
		System.out.println(title+" "+source+" "+username); //test
		//creazione del nuovo thread
		RichiestaGioco req = new RichiestaGioco();
		
	
		req.setNomeGioco(title);
		req.setFonte(source);
		req.setUsernameUtente(username);
		req.setAnno(anno);
		req.setGenere(genere);
		req.setPublisher(publisher);
		
	
		
		try {
			
				if(RichiestaGiocoDAO.addGameRequest(req))
				{
					request.setAttribute("richiestagioco", "true");
					//RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/GameSquare/index.jsp");
					//dispatcher.forward(request, response);
					
				}
				else 
				{
					request.setAttribute("richiestagioco", "false");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/nuova-richiesta-gioco.jsp");
					dispatcher.forward(request, response);
				}
			
		} catch (SQLException e) {
			request.setAttribute("richiestagioco", "errorSQL");
			e.printStackTrace();
		}
		
	}

    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		doPost(request, response);
	}

}