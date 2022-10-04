/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FormHandler;

import Exceptions.EmptyValueException;
import Service.PatronService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author WorldEdit
 */
public class PasswordController extends HttpServlet {
    
    PatronService patronService = new PatronService();
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] data = new String[3];
        data[0] = req.getParameter("id");
        data[1] = req.getParameter("roll");
        data[2] = req.getParameter("password");
       
        try {
            if(!patronService.changePassword(data)){
                resp.sendRedirect(req.getContextPath() + "/Resources/Patron/formPatron.jsp");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PasswordController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }
}
