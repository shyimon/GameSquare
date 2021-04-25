package listManager;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteFromListServlet
 */
@WebServlet("/DeleteFromList")
public class DeleteFromListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static ElementoListaDAO model = new ElementoListaDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteFromListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = null; 
		String gameID = null; 
		String category_value = null; 
		String user_category= null;
		int score = 0;
		int usrScore = 0;
		username = request.getParameter("username");
		gameID = request.getParameter("game_id");
		user_category=request.getParameter("user_category");
		score=Integer.parseInt(request.getParameter("score"));
		usrScore=Integer.parseInt(request.getParameter("usrScore"));
		System.out.println(username + " vuole togliere " +gameID+" dalla lista, punteggio "+score+" partendo da un punteggio di "+usrScore+" e dalla categoria "+user_category);//test
		
		int oldScore =calcolaPunteggio(score, user_category);
		//System.out.println("il vecchio punteggio è "+ score);
		usrScore-=oldScore;
		//System.out.println("l'utente ora avrà "+ usrScore);
		try {
			if(ElementoListaDAO.deleteListElement(Integer.parseInt(gameID), username)) {
				System.out.println("Eliminato");
				ElementoListaDAO.updateUserScore(username, usrScore);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	int calcolaPunteggio(int score, String categoria) {
		int newScore=score;
		
		if(categoria.equals("In corso"))
			newScore=score+10;
		else if(categoria.equals("Completato"))
			newScore=score+30;
		else if(categoria.equals("Platinato"))
			newScore=score+50;
		else if(categoria.equals("Sviluppato"))
			newScore=score*2;
		return newScore;
	}

}
