/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Modal.DashboardModal;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author WorldEdit
 */
public class DashboardService {

    DashboardModal dashboardModal = new DashboardModal();

    public String getData() {
        try {
            com.google.gson.JsonObject gObj = new JsonObject();
            JsonObject item = new JsonObject();
            String tmp = dashboardModal.fetchTotal("booksTotal");
            item.addProperty("booksTotal", tmp.substring(0, tmp.indexOf(".")));
            item.addProperty("booksUnique", dashboardModal.fetchTotal("books"));
            item.addProperty("patrons", dashboardModal.fetchTotal("patrons"));
            item.addProperty("borrowers", dashboardModal.fetchTotal("borrowers"));
            item.addProperty("issued", dashboardModal.fetchTotal("bookIssued"));
            item.addProperty("blacklist", dashboardModal.fetchTotal("blacklist"));
            
            JsonArray arrM = new JsonArray();
            
            arrM.add(item);
            
            JsonArray arr1 = new JsonArray();
            ArrayList<String[]> list1 = dashboardModal.fetchDateData("issue");
            ArrayList<String[]> list2 = dashboardModal.fetchDateData("returned");
            for (String[] strings : list1) {
                JsonObject item2 = new JsonObject();

                item2.addProperty("x", strings[0]);
                item2.addProperty("y", strings[1]);
                arr1.add(item2);
            }
            
            JsonArray arr2 = new JsonArray();
            arrM.add(arr1);
            for (String[] strings : list2) {
                JsonObject item2 = new JsonObject();

                item2.addProperty("x", strings[0]);
                item2.addProperty("y", strings[1]);
                arr2.add(item2);
            }
            arrM.add(arr2);
            gObj.add("datas", arrM);
            return gObj.toString();
        } catch (SQLException ex) {
        }
        return null;
    }
}
