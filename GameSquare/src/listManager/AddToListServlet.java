package listManager;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gameManager.Voto;
import gameManager.VotoDAO;

/**
 * Servlet implementation class AddToListServlet
 */
@WebServlet("/AddToList")
public class AddToListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	static ElementoListaDAO model = new ElementoListaDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToListServlet() {
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
		int score = 0;
		int usrScore = 0;
		username = request.getParameter("username");
		gameID = request.getParameter("game_id");
		category_value = request.getParameter("category_value");
		score=Integer.parseInt(request.getParameter("score"));
		usrScore=Integer.parseInt(request.getParameter("usrScore"));
		System.out.println(username + " ha aggiunto " +gameID+" a "+category_value+" punteggio "+score+" partendo da un punteggio di "+usrScore);//test
		
		
		score =ScoreHelper.calcolaPunteggio(score, category_value);
		System.out.println("il nuovo punteggio è "+ score);
		
		usrScore+=score;
		System.out.println("l'utente ora avrà "+ usrScore);
	
		try {
			
			ElementoLista e  = new ElementoLista();
			e.setIdGioco(Integer.parseInt(gameID));
			e.setUsernameUtente(username);
			e.setCategoria(category_value);
			if(ElementoListaDAO.addListElement(e)) {
				
				ElementoListaDAO.updateUserScore(username, usrScore);
				response.getWriter().write("Aggiunto alla lista, nuovo punteggio = "+usrScore);//test
			}
		} catch (SQLException e) {
			request.setAttribute("result", "notAdded");
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
