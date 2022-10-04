/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FormHandler;

import Exceptions.EmptyValueException;
import ObjClasses.Faculty;
import Service.FacultyService;
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
public class FacultyController extends HttpServlet {
    
    FacultyService facultyService = new FacultyService();
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String[] data = new String[2];
        
        data[0] = req.getParameter("id");
        data[1] = req.getParameter("name");
        try {
            if(!facultyService.check(data)){
                resp.sendRedirect(req.getContextPath() + "/Resources/Pages/Faculty_List.jsp");
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacultyController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EmptyValueException ex) {
            Logger.getLogger(FacultyController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");

       
        resp.getWriter().print(facultyService.getData());

    }

}
