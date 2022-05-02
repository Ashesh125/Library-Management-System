/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Configuration;

import static Utilities.StringUtility.removeLastChar;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author WorldEdit
 */
public class LibraryHelper extends DatabaseHelper {

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public LibraryHelper(String table_name) throws SQLException, ClassNotFoundException {
        super(table_name);
    }

    public ResultSet fetchPatron(String hash) throws SQLException {
        sql = "SELECT * from (patron inner join facultys on facultys.faculty_Id = patron.patron_Faculty) where patron.patron_Hash = ?";

        preSql = conn.prepareStatement(sql);
        preSql.setString(1, hash);
        System.out.println(preSql);
        return preSql.executeQuery();
    }

    public ResultSet fetchAllBooks() throws SQLException {
        sql = "SELECT * from (books inner join publishers on books.book_Publisher = publishers.publisher_Id)";

        preSql = conn.prepareStatement(sql);
        System.out.println(preSql);
        return preSql.executeQuery();
    }

    public boolean insertBorrower(String patron, String admin, String book) throws SQLException {

        String sql = "INSERT INTO " + table_name + " (patron_Id,book_Id,admin_Id,due_Date,returned) values (?,?,?,?, null)";

        preSql = conn.prepareStatement(sql);
        preSql.setString(1, patron);
        preSql.setString(2, book);
        preSql.setString(3, admin);
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 15);
        dt = c.getTime();
        preSql.setString(4, dateFormat.format(dt));

        System.out.println(" Sql :" + preSql);
        return preSql.execute();
    }

    public ResultSet fetchAllBorrowers() throws SQLException {
        sql = "SELECT borrower.*,patron.patron_Name,admin.admin_Name,books.book_Name FROM borrower JOIN patron ON patron.patron_Id = borrower.patron_Id JOIN admin ON admin.admin_Id = borrower.admin_Id JOIN books ON borrower.book_Id = books.books_Id where borrower.returned IS NULL AND patron.flagged = 0";

        preSql = conn.prepareStatement(sql);
        System.out.println(preSql);
        return preSql.executeQuery();
    }

    public ResultSet fetchFlaggedBorrowers() throws SQLException {
        sql = "SELECT borrower.*,patron.patron_Name,admin.admin_Name,books.book_Name FROM borrower JOIN patron ON patron.patron_Id = borrower.patron_Id JOIN admin ON admin.admin_Id = borrower.admin_Id JOIN books ON borrower.book_Id = books.books_Id where patron.flagged = 1";

        preSql = conn.prepareStatement(sql);
        System.out.println(preSql);
        return preSql.executeQuery();
    }

    public ResultSet fetchAllHistory() throws SQLException {
        sql = "SELECT borrower.*,patron.patron_Name,admin.admin_Name,books.book_Name FROM borrower JOIN patron ON patron.patron_Id = borrower.patron_Id JOIN admin ON admin.admin_Id = borrower.admin_Id JOIN books ON borrower.book_Id = books.books_Id where borrower.returned IS NOT NULL";

        preSql = conn.prepareStatement(sql);
        System.out.println(preSql);
        return preSql.executeQuery();
    }

    public ResultSet checkBookExists(String patron, String book) throws SQLException {
        sql = "SELECT EXISTS(SELECT * FROM borrower WHERE patron_Id = ? and book_Id= ? and returned is NULL) as num";
        preSql = conn.prepareStatement(sql);
        preSql.setString(1, patron);
        preSql.setString(2, book);
        System.out.println(preSql);
        return preSql.executeQuery();
    }

    public boolean updateBorrower(String patron, String book) throws SQLException {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String date = simpleDateFormat.format(new Date());
        String sql = "UPDATE " + table_name + " SET returned = ? where patron_Id = ? and book_Id = ?";

        preSql = conn.prepareStatement(sql);
        preSql.setString(1, date.toString());
        preSql.setString(2, patron);
        preSql.setString(3, book);

        System.out.println(preSql);
        return preSql.execute();

    }

    public ResultSet fetchBookQty(String book) throws SQLException {
        sql = "SELECT book_Quantity as qty from books where books_Id = ?";
        preSql = conn.prepareStatement(sql);
        preSql.setString(1, book);
        System.out.println(preSql);
        return preSql.executeQuery();
    }

    public ResultSet fetchBookTotal(String book) throws SQLException {
        sql = "SELECT book_Total as ava from books where books_Id = ?";
        preSql = conn.prepareStatement(sql);
        preSql.setString(1, book);
        System.out.println(preSql);
        return preSql.executeQuery();
    }

    public boolean triggerBookQty(String value, String type) throws SQLException {
        String sql = "UPDATE books SET book_Quantity = book_Quantity " + type + " 1 where books_Id = ?";

        PreparedStatement preSql = conn.prepareStatement(sql);
        preSql.setString(1, value);

        System.out.println(preSql);
        return preSql.execute();
    }

    public boolean trigger(String id, int status) throws SQLException {
        String sql = "UPDATE books SET book_Available = " + status + " where books_Id = ?";

        PreparedStatement preSql = conn.prepareStatement(sql);
        preSql.setString(1, id);

        System.out.println(preSql);
        return preSql.execute();
    }

    public ResultSet fetchCount() throws SQLException {
        sql = "SELECT count(*) as total from " + table_name;

        preSql = conn.prepareStatement(sql);
        System.out.println(preSql);
        return preSql.executeQuery();
    }

    public ResultSet fetchCount(String condition) throws SQLException {
        sql = "SELECT count(*) as total from " + table_name + " WHERE " + condition;

        preSql = conn.prepareStatement(sql);
        System.out.println(preSql);
        return preSql.executeQuery();
    }

    public ResultSet fetchIssueDates() throws SQLException {
        sql = "SELECT borrowed_Date as dates,count(*) as totals from borrower group by borrowed_Date order by borrowed_Date";

        preSql = conn.prepareStatement(sql);
        System.out.println(preSql);
        return preSql.executeQuery();
    }

    public ResultSet fetchReturnDates() throws SQLException {
        sql = "SELECT returned as dates,count(*) as totals from borrower where returned is not NULL group by returned order by returned";

        preSql = conn.prepareStatement(sql);
        System.out.println(preSql);
        return preSql.executeQuery();
    }

    public ResultSet fetchCount(String type, String condition, String unique) throws SQLException {
        switch (type) {
            case "distinct":
                sql = "SELECT count(DISTINCT " + unique + ") as total from " + table_name + " WHERE " + condition;
                break;

            case "sum":
                sql = "SELECT SUM(" + unique + ") as total from " + table_name;
                break;

            default:
                sql = "SELECT count(*) as total from " + table_name + " WHERE " + condition;
                break;

        }

        preSql = conn.prepareStatement(sql);
        System.out.println(preSql);
        return preSql.executeQuery();
    }

    public boolean updatePatron(ArrayList<String> values,boolean changePswd) throws SQLException {
        String sql = null;
        if(changePswd){
            sql = "UPDATE " + table_name + " SET patron_Name = ? , patron_Roll = ? ,patron_Email = ? , patron_Phone = ?, patron_Faculty = ? ,patron_Image = ?,patron_Qty = ?, fine = ?, flagged = ?, patron_Hash2 = ? where patron_Id = ?";
        }else{
            sql = "UPDATE " + table_name + " SET patron_Name = ? , patron_Roll = ? ,patron_Email = ? , patron_Phone = ?, patron_Faculty = ? ,patron_Image = ?,patron_Qty = ?, fine = ?, flagged = ? where patron_Id = ?";
        }
        preSql = conn.prepareStatement(sql);
        for (int i = 1; i < values.size(); i++) {
            preSql.setString(i, values.get(i));
        }
        preSql.setString(values.size(), values.get(0));
        System.out.println(preSql);
//        stmt = conn.createStatement();
//        return preSql.toString
        return preSql.execute();
    }
    
    public boolean updateAdmin(ArrayList<String> values,boolean changePswd) throws SQLException {
        String sql = null;
        if(changePswd){
            sql = "UPDATE " + table_name + " SET admin_Name = ? , admin_Roll = ? , admin_Hash2 = ? where admin_Id = ?";
        }else{
            sql = "UPDATE " + table_name + " SET admin_Name = ? , admin_Roll = ? where admin_Id = ?";
        }preSql = conn.prepareStatement(sql);
        for (int i = 1; i < values.size(); i++) {
            preSql.setString(i, values.get(i));
        }
        preSql.setString(values.size(), values.get(0));
        System.out.println(preSql);
//        stmt = conn.createStatement();
//        return preSql.toString
        return preSql.execute();
    }
}
