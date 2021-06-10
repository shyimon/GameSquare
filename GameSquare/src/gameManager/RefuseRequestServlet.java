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
 * Questa classe è un control che si occupa di gestire il rifiuto di una richiesta per un nuovo gioco, passa l’id della richiesta a RichiestaGiocoDAO, che si occuperà di eliminarla dal database.
 */
@WebServlet("/RefuseReq")
public class RefuseRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public RefuseRequestServlet() {
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
				System.out.println("ricevuta richiesta eliminazione id: "+reqID);
				RichiestaGiocoDAO.deleteGameRequest(Integer.parseInt(reqID));
				if(model.viewRequestById(Integer.parseInt(reqID))==null)
					request.setAttribute("result", "success");
			}
			
			} catch (SQLException e) {
				response.sendError(500);
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
