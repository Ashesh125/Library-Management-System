/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modal;

import Configuration.DatabaseHelper;
import Configuration.LibraryHelper;
import ObjClasses.Patron;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author WorldEdit
 */
public class PatronModal {

    DatabaseHelper dbH;
    String table_name = "patron";

    public boolean insertPatron(Patron patron) throws SQLException {
        try {
            dbH = new DatabaseHelper(table_name);

            String values[] = new String[11];
            values[0] = patron.getName();
            values[1] = patron.getRoll();
            values[2] = patron.getEmail();
            values[3] = patron.getPhone();
            values[4] = patron.getHash();
            values[5] = patron.getHash2();
            values[6] = patron.getFaculty();
            values[7] = patron.getImage();
            values[8] = patron.getQty();
            Integer i = patron.getFine();
            values[9] = i.toString();
            if (patron.isFlagged()) {
                values[10] = "1";

            } else {
                values[10] = "0";
            }
            boolean isInserted = dbH.insert(values);
            if (isInserted) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
            return false;
        } finally {
//            dbH.closeConnection();
        }
    }

    public boolean updatePatron(Patron patron) throws SQLException {
        boolean changePswd = false;
        try {
            LibraryHelper dbHL = new LibraryHelper(table_name);
            ArrayList<String> values = new ArrayList();
            values.add(patron.getId());
            
            values.add(patron.getName());
            values.add(patron.getRoll());
            values.add(patron.getEmail());
            values.add(patron.getPhone());
            values.add(patron.getFaculty());
            values.add(patron.getImage());
            values.add(patron.getQty());
            Integer i = patron.getFine();
            values.add(i.toString());
            if (patron.isFlagged()) {
                values.add("1");
            } else {
                values.add("0");
            }
            if(!patron.getHash2().equals("")){
                values.add(patron.getHash2());
                changePswd = true;
            }
            boolean isUpdated = dbHL.updatePatron(values,changePswd);
            if (isUpdated) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
            return false;
        } finally {
//            dbH.closeConnection();
        }
    }

    public boolean deletePatron(Patron patron) throws SQLException {
        try {
            dbH = new DatabaseHelper(table_name);

            int isDeleted = dbH.delete(patron.getId());
            if (isDeleted == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
            return false;
        } finally {
//            dbH.closeConnection();
        }
    }

    public ArrayList<Patron> fetchAll() throws SQLException {
        ArrayList<Patron> patrons = new ArrayList<>();
        try {
            dbH = new DatabaseHelper(table_name);
            ResultSet results = dbH.fetchAll();
            while (results.next()) {
                Patron p = new Patron();
                p.setId(results.getString("patron_Id"));
                p.setName(results.getString("patron_Name"));
                p.setRoll(results.getString("patron_Roll"));
                p.setEmail(results.getString("patron_Email"));
                p.setPhone(results.getString("patron_Phone"));
                p.setHash(results.getString("patron_Hash"));

                p.setFaculty(results.getString("patron_Faculty"));
                p.setImage(results.getString("patron_Image"));
                p.setQty(results.getString("patron_Qty"));
                p.setFine(Integer.parseInt(results.getString("fine")));
                p.setFlagged(results.getString("flagged").equals("1")?true:false);
                patrons.add(p);
            }
            return patrons;

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Error occurred: " + ex.getMessage());
        } finally {
            dbH.closeConnection();
        }
        return null;
    }
    
    public Patron fetchSpecific(String col,String value) throws SQLException {
        Patron p = new Patron();
        try {
            dbH = new DatabaseHelper(table_name);
            ResultSet results = dbH.fetchBlocks(table_name, col, value);
            while (results.next()) {
                
                p.setId(results.getString("patron_Id"));
                p.setName(results.getString("patron_Name"));
                p.setRoll(results.getString("patron_Roll"));
                p.setEmail(results.getString("patron_Email"));
                p.setPhone(results.getString("patron_Phone"));
                p.setHash(results.getString("patron_Hash"));

                p.setFaculty(results.getString("patron_Faculty"));
                p.setImage(results.getString("patron_Image"));
                p.setQty(results.getString("patron_Qty"));
                p.setFine(Integer.parseInt(results.getString("fine")));
                p.setFlagged(results.getString("flagged").equals("1")?true:false);

            }
            return p;

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Error occurred: " + ex.getMessage());
        } finally {
            dbH.closeConnection();
        }
        return null;
    }
    
    public Patron fetchByHash(String hash) throws SQLException {
        Patron p = new Patron();
        LibraryHelper dbHF;
        try {
            dbHF = new LibraryHelper(table_name);
            ResultSet results = dbHF.fetchPatron(hash);
            while (results.next()) {
                p.setId(results.getString("patron_Id"));
                p.setName(results.getString("patron_Name"));
                p.setRoll(results.getString("patron_Roll"));
                p.setEmail(results.getString("patron_Email"));
                p.setPhone(results.getString("patron_Phone"));
                p.setHash(results.getString("patron_Hash"));

                p.setFaculty(results.getString("faculty_Name"));
                p.setImage(results.getString("patron_Image"));
                p.setQty(results.getString("patron_Qty"));
                p.setFine(Integer.parseInt(results.getString("fine")));
                p.setFlagged(results.getString("flagged").equals("1")?true:false);

            }
            return p;

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Error occurred: " + ex.getMessage());
        } finally {
        }
        return null;
    }
    
    public ArrayList<String[]> fetchAllBorrowers(String type) throws SQLException {
        LibraryHelper dbHF;
        ArrayList<String[]> all = new ArrayList();
        ResultSet results = null;
        System.out.println(type);
        try {
            dbHF = new LibraryHelper(table_name);
            
            switch(type){
                case "b":
                    results = dbHF.fetchAllBorrowers();
                    break;
                    
                case "f":
                    results = dbHF.fetchFlaggedBorrowers();
                    break;
                    
                case "a":
                    results = dbHF.fetchAllHistory();
                    break;
                   
                default:
                    results = dbHF.fetchHistoryOf(type);
                    break;
                    
            }
            
            while (results.next()) {
                String[] temp = new String[10];
            
                temp[0] = results.getString("borrower_Id");
                temp[1] = results.getString("patron_Id");
                temp[2] = results.getString("book_Id");
                temp[3] = results.getString("admin_Id");
                temp[4] = results.getString("borrowed_Date");
                temp[5] = results.getString("due_Date");
                temp[6] = results.getString("returned");
                temp[7] = results.getString("patron_Name");
                temp[8] = results.getString("admin_Name");
                temp[9] = results.getString("book_Name");
                
                all.add(temp);
            }
            return all;

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Error occurred: " + ex.getMessage());
        } finally {
        }
        return null;
    }
    
     public boolean updatePassword(String id, String value) throws SQLException {
        try {
            LibraryHelper dbHL = new LibraryHelper(table_name);
            
            boolean isUpdated = dbHL.updatePassword(id,value);
            if (isUpdated) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
            return false;
        } finally {
//            dbH.closeConnection();
        }
    }
    
}
