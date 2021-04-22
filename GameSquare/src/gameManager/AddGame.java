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
 * Servlet implementation class AddGame
 */
@WebServlet("/AddGame")
public class AddGame extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public AddGame() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String name = null;
		String publisher = null;
		String genere = null;
		String anno = null;
		String descrizione = null;
		String punteggio = null;
		
		name = request.getParameter("game_title");
		publisher = request.getParameter("publisher");
		genere = request.getParameter("game_genre");
		anno = request.getParameter("game_year");
		descrizione = request.getParameter("game_desc");
		punteggio = request.getParameter("score");
		//System.out.println(title+" "+type+" "+text+" "+gameID+" "+username); //test
		
		//creazione del nuovo oggetto gioco
		Gioco gioco = new Gioco();
		
	
		gioco.setNome(name);
		gioco.setPublisher(publisher);
		gioco.setGenere(genere);
		gioco.setAnno(anno);
		gioco.setDescrizione(descrizione);
		gioco.setPunteggio(Integer.parseInt(punteggio));
		gioco.setMediaVoti(0);
		gioco.setImgpath("img/Games/placeholder");
		
	
		
		try {
			
				if(GiocoDAO.addGame(gioco))
				{

					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Game?action=findall");
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