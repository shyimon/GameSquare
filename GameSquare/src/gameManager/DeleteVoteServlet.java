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
 * Servlet implementation class DeleteVoteServlet
 */
@WebServlet("/DeleteVote")
public class DeleteVoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static VotoDAO voteModel = new VotoDAO();
	
    public DeleteVoteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gameID = request.getParameter("game_id");
		String user = request.getParameter("username");
		System.out.println("ricevuta richiesta eliminazione voto gioco id: "+gameID);
		try {
			if(VotoDAO.deleteVote(Integer.parseInt(gameID), user))
				System.out.println("Voto eliminato");
			else {
				response.setStatus(500);
			}
			float media = voteModel.calculateAverage(Integer.parseInt(gameID));
			System.out.println("Media =" +media);
			GiocoDAO.updateAverage(Integer.parseInt(gameID), media);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

}
