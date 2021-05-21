package listManager;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ChangeCategoryServlet
 */
@WebServlet("/ChangeCateg")
public class ChangeCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static ElementoListaDAO model = new ElementoListaDAO();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeCategoryServlet() {
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
		category_value = request.getParameter("category_value");
		user_category=request.getParameter("user_category");
		score=Integer.parseInt(request.getParameter("score"));
		usrScore=Integer.parseInt(request.getParameter("usrScore"));
		System.out.println(username + " ha aggiunto " +gameID+" a "+category_value+" punteggio "+score+" partendo da un punteggio di "+usrScore+" e dalla categoria "+user_category);//test
		
		int oldScore =ScoreHelper.calcolaPunteggio(score, user_category);
		//System.out.println("il vecchio punteggio � "+ score);
		usrScore-=oldScore;
		//System.out.println("l'utente ora avr� "+ usrScore);
		score = ScoreHelper.calcolaPunteggio(score, category_value);
		//System.out.println("il nuovo punteggio � "+ score);
		
		usrScore+=score;
		System.out.println("l'utente ora avr� "+ usrScore);
		try {
			if(ElementoListaDAO.updateCategory(username, Integer.parseInt(gameID), category_value)) {
				ElementoListaDAO.updateUserScore(username, usrScore);
				response.getWriter().write("Categoria aggiornata, nuovo punteggio = "+usrScore);
			}
			else {
				response.getWriter().write("Errore");
				response.setStatus(500);
			}
		} catch (SQLException e) {
			response.getWriter().write("Errore SQL");
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
