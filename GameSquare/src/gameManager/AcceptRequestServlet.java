package gameManager;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Questa classe è un control che si occupa di gestire l’accettazione di una richiesta per un nuovo gioco, passa l’id della richiesta a RichiestaGiocoDAO, che ne ricava i dati, passandoli a GiocoDAO per aggiungere il titolo, e successivamente elimina la richiesta.
 */
@WebServlet("/AcceptReq")
public class AcceptRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AcceptRequestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    static RichiestaGiocoDAO model = new RichiestaGiocoDAO();

    /**
 	 * @precondition request.getParameter(“reqid”)!=null 
 	 * @postcondition request.getAttribute("result")!=null
 	 * @throws ServletException, IOException
 	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.removeAttribute("result");
		
		try {
		String reqID = request.getParameter("reqid");
		if(model.viewRequestById(Integer.parseInt(reqID))==null) {
			request.setAttribute("result", "fail");
			response.getWriter().write("Richiesta inesistente");
		} else {
			System.out.println("ricevuta richiesta aggiunta id: "+reqID);
			RichiestaGioco app;
			app = model.viewRequestById(Integer.parseInt(reqID));
			Gioco g = new Gioco(app.getNomeGioco(), "Questo gioco è stato aggiunto al sito su suggerimento di: "+app.getUsernameUtente(), app.getPublisher(), app.getAnno(), app.getGenere());
			g.setPunteggio(100);
			g.setImgpath("img/Games/placeholder");
			g.setMediaVoti(0);
			System.out.println(g.toString());
			//GiocoDAO gameModel = new GiocoDAO();
				if(GiocoDAO.addGame(g)) {
					RichiestaGiocoDAO.deleteGameRequest(app.getIdRichiesta());
					request.setAttribute("result", "success");
				}
		}
		
			} catch (SQLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
