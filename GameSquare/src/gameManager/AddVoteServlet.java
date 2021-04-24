package gameManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddVoteServlet
 */
@WebServlet("/AddVote")
public class AddVoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	static GiocoDAO gameModel = new GiocoDAO();
	static VotoDAO voteModel = new VotoDAO();
	
    public AddVoteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = null; 
		String gameID = null; 
		String vote_value = null; 
		float media = 0;
		username = request.getParameter("username");
		gameID = request.getParameter("game_id");
		vote_value = request.getParameter("vote_value");
		System.out.println(username + " ha votato " +gameID+" con "+vote_value);//test
		try {
			ArrayList<Voto> app = voteModel.getVote("Gioco", gameID, "utente", username);
			if(app.size()!=0) {
				if(VotoDAO.updateVote(username, Integer.parseInt(gameID), Integer.parseInt(vote_value))) {
						System.out.println("Valutazione aggiornata");
						
				}
			}
			else {
				System.out.println("Non c'� ancora questo voto."); //test
				Voto v  = new Voto();
				v.setIdGioco(Integer.parseInt(gameID));
				v.setUsernameUtente(username);
				v.setValutazione(Integer.parseInt(vote_value));
				if(VotoDAO.addVote(v))
					System.out.println("Valutazione aggiunta");
				}
			
			media = voteModel.calculateAverage(Integer.parseInt(gameID));
			System.out.println("Media =" +media);
			GiocoDAO.updateAverage(Integer.parseInt(gameID), media);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				doPost(request, response);
	}

}