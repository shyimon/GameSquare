package gameManager;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;

/**
 * Servlet implementation class RequestControl
 */
@WebServlet("/Request")
public class RequestControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestControl() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    static RichiestaGiocoDAO model = new RichiestaGiocoDAO();
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
	
	try {	
		if(action!=null) {
			if(action.equals("refuseReq")) {
				String reqID = request.getParameter("reqid");
				System.out.println("ricevuta richiesta eliminazione id: "+reqID);
				ArrayList<RichiestaGioco> app = model.viewRequest("id", reqID);
				model.deleteGameRequest(app.get(0).getIdRichiesta());
				}
			else if(action.equals("acceptReq")) {
				String reqID = request.getParameter("reqid");
				System.out.println("ricevuta richiesta aggiunta id: "+reqID);
				RichiestaGioco app = model.viewRequest("id", reqID).get(0);
				Gioco g = new Gioco(app.getNomeGioco(), "Questo gioco è stato aggiunto al sito su suggerimento di: "+app.getUsernameUtente(), app.getPublisher(), app.getAnno(), app.getGenere());
				g.setPunteggio(100);
				g.setImgpath("img/Games/placeholder");
				g.setMediaVoti(0);
				System.out.println(g.toString());
				GiocoDAO gameModel = new GiocoDAO();
				if(gameModel.addGame(g))
					model.deleteGameRequest(app.getIdRichiesta());
				}
			}
		} catch (SQLException e) {
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
