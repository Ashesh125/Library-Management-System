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
public class PatronController extends HttpServlet {
    
    PatronService patronService = new PatronService();
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] data = new String[12];
        data[0] = req.getParameter("id");
        data[1] = req.getParameter("name");
        data[2] = req.getParameter("roll");
        data[3] = req.getParameter("email");
        data[4] = req.getParameter("phone");
        
        data[5] = req.getParameter("password");
        data[7] = req.getParameter("faculty");
        data[8] = req.getParameter("image");
        data[9] = req.getParameter("qty");
        data[10] = req.getParameter("fine");
        
        
        
        data[11] = req.getParameter("flagged");
        
        for(String item:data){
            resp.getWriter().write(item +"\n");
        }
        
        try {
            if(!patronService.check(data)){
                resp.sendRedirect(req.getContextPath() + "/Resources/Pages/Patron_List.jsp");
            }
        } catch (SQLException ex) {
           
        } catch (EmptyValueException ex) {
        }
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");

       
        resp.getWriter().print(patronService.getData());

    }

}

