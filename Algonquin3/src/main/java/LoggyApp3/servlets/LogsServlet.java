package LoggyApp3.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogsServlet
 */



@WebServlet("/LogsServlet")
public class LogsServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    
    
	@SuppressWarnings("unused")
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    			String title = request.getParameter("title");
    			String desc = request.getParameter("description");
                String msg = request.getParameter("message");
                String htmlReponse = "<html><h3>Success<h3><html>";
                PrintWriter writer = response.getWriter();
                writer.write(htmlReponse);
    }
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    			String title = request.getParameter("title");
    			String desc = request.getParameter("description");
                String msg = request.getParameter("message");
                
                PrintWriter writer = response.getWriter();
                writer.write("Content:" + msg);

                   
                response.getWriter().println("<html>");
                response.getWriter().println("<head>");
                response.getWriter().println("<title>Cool</title>");
                response.getWriter().println("</head>");
                response.getWriter().println("<body>");
                response.getWriter().println("<p>");
                response.getWriter().println("Title:" + title);
                response.getWriter().println("</p>");
                
                response.getWriter().println("<p>");
                response.getWriter().println("Description:" + desc);
                response.getWriter().println("</p>");
                
                response.getWriter().println("<p>");
                response.getWriter().println("Content:" + msg);
                response.getWriter().println("</p>");
                response.getWriter().println("</body>");
                response.getWriter().println("</html>");
                
    }
            /**
             * @see HttpServlet#HttpServlet()
             */
           public LogsServlet() {
                super();
            }
 
	}


