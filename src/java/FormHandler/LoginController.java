/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FormHandler;

import Exceptions.EmptyValueException;
import Service.LoginService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author WorldEdit
 */
public class LoginController extends HttpServlet{
    
    LoginService loginService = new LoginService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] data = new String[2];
        data[0] = req.getParameter("userid");
        data[1] = req.getParameter("pswd");
        
        HttpSession session = req.getSession();
        
        try {
            String mode = loginService.check(data,session);
            if(mode.equals("admin")){
                resp.sendRedirect(req.getContextPath() + "/Resources/Dashboard/Dashboard.jsp");
            }
            else if(mode.equals("patron")){
                resp.sendRedirect(req.getContextPath() + "/Resources/Patron/Main.jsp");    
            }
            else{
                session.setAttribute("noUser", 1);
                resp.sendRedirect(req.getContextPath() + "/index.jsp");
            }
        } catch (EmptyValueException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
