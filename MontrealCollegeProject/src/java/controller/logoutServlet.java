
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logoutServlet")
public class logoutServlet extends HttpServlet{

      @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        HttpSession session = request.getSession(false);
        session.invalidate();
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
        response.setHeader("Pragma", "no-cache"); 
        response.setDateHeader("Expires", 0);
        
//        System.out.println("logoutServlet_username_r*****************************");
//        System.out.println(session.getAttribute("user_r"));
//        System.out.println(session.getAttribute("user_id"));
        request.getRequestDispatcher("/index.html").forward(request, response);        
    }
    
//    @Override
//    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
//        HttpSession session = request.getSession(false);
//        session.invalidate();
//        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
//        response.setHeader("Pragma", "no-cache"); 
//        response.setDateHeader("Expires", 0);
//        request.getRequestDispatcher("/index.html").forward(request, response);
//    }

}
