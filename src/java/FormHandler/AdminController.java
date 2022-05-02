/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FormHandler;

import Exceptions.EmptyValueException;
import Service.AdminService;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @admin WorldEdit
 */
public class AdminController extends HttpServlet {

    AdminService adminService = new AdminService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] data = new String[4];
        data[0] = req.getParameter("id");
        data[1] = req.getParameter("name");
        data[2] = req.getParameter("password");
        data[3] = req.getParameter("roll");

        try {
            if (!adminService.check(data)) {
                resp.sendRedirect(req.getContextPath() + "/Resources/Pages/Admin_List.jsp");
            }
        } catch (SQLException ex) {
        } catch (EmptyValueException ex) {
        } 

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");

        resp.getWriter().print(adminService.getData());

    }

}
