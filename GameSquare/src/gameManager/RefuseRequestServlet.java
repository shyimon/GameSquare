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
 * Servlet implementation class RefuseRequestServlet
 */
@WebServlet("/RefuseReq")
public class RefuseRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RefuseRequestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    static RichiestaGiocoDAO model = new RichiestaGiocoDAO();
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String reqID = request.getParameter("reqid");
			System.out.println("ricevuta richiesta eliminazione id: "+reqID);
			RichiestaGioco app = model.viewRequestById(Integer.parseInt(reqID));
			RichiestaGiocoDAO.deleteGameRequest(app.getIdRichiesta());
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
