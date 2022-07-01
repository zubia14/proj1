package LoggyApp3.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import LoggyApp3.beans.Log;
import LoggyApp3.beans.TextLog;
import LoggyApp3.inmemory.ApplicationInMemory;
import LoggyApp3.services.ApplicationService;


//Servlet implementation class LogsServlet
@WebServlet(description = "Loggy Logs", urlPatterns = { "/logsServlet" })
public class LogsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    ApplicationService logs;

    public LogsServlet() {
        super();
        this.logs = new ApplicationInMemory();
// this.logs = new ApplicationDao();
    }

   
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("delete".equals(action)) {
            delete(request);
        }

        String title = "";
        String content = "";
        String id = request.getParameter("id");
        if (id == null || "".equals(id)) {
            // Initialize id and continue with the rendering.
            id = "";
        } else {
            // Read the record from memory.
            Log log = this.logs.readLog(id);
            if (log == null) {
                // Log not found, initialize id and continue with the rendering.
                id = "";
            } else {
                // Log found, initialize title and content.
                title = log.getTitle();
                content = log.getContent();
            }
        }

        // Render response.
        String htmlResponse = printOutHead(request.getContextPath());
        htmlResponse += printOutBodyForm(id, title, content);
        // Read all logs, assign to local variable and sent to printOutBodyList
        Map<UUID, Log> logs = this.logs.readLogs();
        htmlResponse += printOutBodyList(logs);
        htmlResponse += printOutFoot();
        PrintWriter writer = response.getWriter();
        writer.write(htmlResponse);
    }

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Log log = null;
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        if (id == null || "".equals(id)) {
            // Create the log.
            log = new TextLog(title, content);
        } else {
            // Read the log.
            log = this.logs.readLog(id);
            log.setTitle(title);
            log.setContent(content);
        }
        // Update the log.
        this.logs.createOrUpdateLog(log);

        // Process GET for rendering the page with updates.
        doGet(request, response);
    }

    private void delete(HttpServletRequest request) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id != null && !id.equals("null")) {
            // Remove the log.
            this.logs.deleteLog(id);
        }
    }

    // This is the HTML code generated entirely from the Servlet.
    private String printOutHead(String root) {
        String out = "<!DOCTYPE html>\n" + "	<html lang=\"en\">\n" + "	    <head>\n"
                + "	        <title>Example</title>\n" + "         <body id=\"page-top\">\n";

        return out;

    }

    private String printOutBodyForm(String id, String title, String content) {
        String out = "        <!-- Form Section-->\n"
                + "        <header class=\"masthead bg-primary text-white text-center\">\n"
                + "        <h1>Log ahead</h1>\n" + "        <form action=\"logs\" method=\"post\">\n"
                + "          <input type=\"hidden\" name=\"id\" value=\"" + id + "\">"
                + "          <label for=\"fname\">Title:</label><br>\n"
                + "          <input type=\"text\" id=\"title\" name=\"title\" value=\"" + title + "\"><br>\n"
                + "          <label for=\"lname\">Content:</label><br>\n"
                + "          <input type=\"text\" id=\"content\" name=\"content\" value=\"" + content + "\"><br><br>\n"
                + "          <input type=\"submit\" value=\"Submit\">\n"
                + "          <input type=\"button\" value=\"Cancel\" onclick=\"window.location='logs'\">\n"
                + "        </form>\n" + "        </header>\n";
        return out;
    }

    private String printOutBodyList(Map<UUID, Log> logs) {
        // Body list top.
        String out = "\n" + "        <!-- Content Section-->\n" + "            <!-- Loggy Items-->\n"
                + "            <div class=\"row\">\n";
        // This is the actual List.
        out += "                  <table class=\"table\">\n" + "                    <thead>\n"
                + "                      <tr>\n" + "                        <th scope=\"col\" class=\"col-2\">#</th>\n"
                + "                        <th scope=\"col\" class=\"col-2\">Title</th>\n"
                + "                        <th scope=\"col\">Content</th>\n"
                + "                        <th scope=\"col\" class=\"col-2\">Actions</th>\n"
                + "                      </tr>\n" + "                    </thead>\n" + "                    <tbody>\n";

        for (Map.Entry<UUID, Log> logItem : logs.entrySet()) {
            out += printOutBodyItem(logItem.getValue().getId().toString(), logItem.getValue().getTitle(),
                    logItem.getValue().getContent());
        }
        out += "                    </tbody>\n" + "                  </table>\n";
        // Body list bottom.
        out += "            </div>\n";

        return out;
    }

    private String printOutBodyItem(String id, String title, String content) {
        String out = "                      <tr>\n" + "                        <th scope=\"row\">" + id.substring(0, 8)
                + "</th>\n" + "                        <td>" + title + "</td>\n" + "                        <td>"
                + content + "</td>\n" + "                        <td>" + "<a href=\"logs?id=" + id + "\">Edit</a>\n"
                + "<a href=\"logs?id=" + id + "&action=delete\">Delete</a>\n" + "                      </td>\n"
                + "                      </tr>\n";

        return out;
    }

    private String printOutFoot() {
        String out = "    </body>\n" + "</html>\n";

        return out;
    }

}



