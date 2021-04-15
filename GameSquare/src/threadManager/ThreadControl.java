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

import threadManager.GameThread;

/**
 * Servlet implementation class ThreadControl
 */
@WebServlet("/Thread")
public class ThreadControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThreadControl() {
        super();
        // TODO Auto-generated constructor stub
    }

    static ThreadDAO threadModel = new ThreadDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		try {
			if(action != null) 
				{	
					if(action.equals("discussion"))
						{
								request.removeAttribute("thread");
				
								String threadID = request.getParameter("threadid");
								ArrayList<GameThread> app=threadModel.viewThread("idThread", threadID);
								request.setAttribute("thread", app.get(0));
						}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				}	
		
		
		RequestDispatcher dispatcher;
		if(action.equals("discussion")) 
			dispatcher = getServletContext().getRequestDispatcher("/pagina-thread.jsp");
		else if(action.equals("newdiscussion")) 
			dispatcher = getServletContext().getRequestDispatcher("/nuova-discussione.jsp");
		else
			dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
		
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
