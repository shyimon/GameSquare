package threadManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewThreadServlet
 */
@WebServlet("/ViewThread")
public class ViewThreadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewThreadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    static ThreadDAO threadModel = new ThreadDAO();
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

				request.removeAttribute("thread");
				String threadID = request.getParameter("threadid");
				ArrayList<GameThread> app=threadModel.viewThread("id_thread", threadID);
				request.setAttribute("thread", app.get(0));
						
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		
		RequestDispatcher dispatcher;
		dispatcher = getServletContext().getRequestDispatcher("/pagina-thread.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
