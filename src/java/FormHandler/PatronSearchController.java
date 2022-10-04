/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FormHandler;

import Service.PatronService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author WorldEdit
 */
public class PatronSearchController extends HttpServlet {

    PatronService patronService = new PatronService();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        String hash = req.getParameter("hash");
        if(hash.equals("none")){
            HttpSession session = req.getSession();
            hash = session.getAttribute("patron_hash").toString();
            System.out.println(hash);
            resp.getWriter().print(patronService.getSpecificDataByHash(hash));    
        }else if(hash.equals("")){
            resp.sendRedirect(req.getContextPath() + "/Forms/Logout");
        }else{
            resp.getWriter().print(patronService.getSpecificDataByHash(hash));
        }
        
    }
}
