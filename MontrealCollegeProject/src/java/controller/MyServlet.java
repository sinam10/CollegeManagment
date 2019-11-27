
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.dao.DAOLogin;
import model.dao.DAOLoginImpl;

@WebServlet("/myServlet")
public class MyServlet extends HttpServlet{
    
//      private HashMap<String, String> users;
      private DAOLogin dao= new DAOLoginImpl();
    
//    public void init(){
//        users = new HashMap();
//        users.put("admin", "admin");
//        users.put("member", "member");
//        users.put("guest", "guest");
//    }
//      @Override
//    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
//        HttpSession session = request.getSession(false);                
//        response.getWriter().print("Hello " + request.getRemoteUser());
//    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
//        System.out.println("in doPost*********************************");
//        System.out.println(request.isUserInRole("admin"));
//        System.out.println(request.getAuthType());
//        System.out.println(request.getUserPrincipal());
//        session.setAttribute("username", request.getParameter("username"));
//if (request.isUserInRole("admin")) {
//    request.getRequestDispatcher("/MainMenu.html").forward(request, response);
//    }
        
        PrintWriter out = response.getWriter();        
        HttpSession session = request.getSession(true);
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
          try {
              if(dao.userPass(username, password).equals("T")){                  
//                  request.setAttribute("username_r", dao.userRole(username));
                  session.setAttribute("user_r", dao.userRole(username));
                  session.setAttribute("user_id", username);
                request.getRequestDispatcher("/MainMenu.jsp").forward(request, response);
              } else {
                  out.println("Wrong user/password");
                  request.getRequestDispatcher("/error.html").forward(request, response);
              } } catch (SQLException ex) {       
              Logger.getLogger(MyServlet.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
    
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        System.out.println("in doGet*********************************");
//        HttpSession session = request.getSession(false);                
//        response.getWriter().print("Hello " + request.getRemoteUser());
    }
}
