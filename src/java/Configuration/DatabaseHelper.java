/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Configuration;

import static Utilities.StringUtility.removeLastChar;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author WorldEdit
 */
public class DatabaseHelper {

    protected String sql;
    private Statement stmt;
    PreparedStatement preSql = null;
    private final String db_name = "library_test";
    protected String table_name;
    protected Connection conn;

    public DatabaseHelper(String table_name) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        this.table_name = table_name;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db_name, "root", "");
        } catch (Exception e) {
            System.out.println("Something Went Wrong!!!\nError : ");
            System.out.println(e);
        }
    }

    public boolean insert(String[] values) throws SQLException {

        ArrayList<String> cols = getColumns();
        System.out.println(cols);
        int size = cols.size();
        String sql = "INSERT INTO " + table_name + " (";

        for (int i = 1; i < size; i++) {
            sql += cols.get(i) + ",";
        }
        sql = removeLastChar(sql);
        sql += ") values (";
        for (int i = 1; i < size; i++) {
            sql += " ? ,";
        }
        sql = removeLastChar(sql);
        sql += ")";

        preSql = conn.prepareStatement(sql);
        for (int i = 0; i < values.length; i++) {
            preSql.setString(i + 1, values[i]);
        }
        System.out.println(" Sql :" + preSql);
        return preSql.execute();

    }

    public ResultSet getMaxId() throws SQLException {
        ArrayList<String> cols = getColumns();
        sql = "SELECT MAX(" + cols.get(0) + ") as max FROM " + table_name;
//        System.out.println(sql);
        preSql = conn.prepareStatement(sql);
        return preSql.executeQuery();
    }

    public boolean update(String[] values) throws SQLException {
        ArrayList<String> cols = getColumns();
        int size = cols.size();

        String sql = "UPDATE " + table_name + " SET ";
        for (int i = 1; i < size; i++) {
            sql += cols.get(i) + "=" + "?" + ",";
            //book_Name = 'asdasdasd',
        }
        sql = removeLastChar(sql);
        sql += " WHERE " + cols.get(0) + "=" + "?";

        PreparedStatement preSql = conn.prepareStatement(sql);
        for (int i = 1; i < values.length; i++) {
            preSql.setString(i, values[i]);
        }
        preSql.setString(size, values[0]);

//        System.out.println(sql);
//        stmt = conn.createStatement();
//        return preSql.toString
        return preSql.execute();

    }

    public int delete(String id) throws SQLException {

        ArrayList<String> cols = getColumns();
        String sql = "DELETE FROM " + table_name;
        sql += " WHERE " + cols.get(0) + "= ?";

        PreparedStatement preSql = conn.prepareStatement(sql);
        preSql.setString(1, id);

        System.out.println(preSql);
//        System.out.println(sql);

        return preSql.executeUpdate();
    }

    public int deleteById(String col, String id) throws SQLException {

        String sql = "DELETE FROM " + table_name;
        sql += " WHERE " + col + "= ?";

        PreparedStatement preSql = conn.prepareStatement(sql);
        preSql.setString(1, id);

        System.out.println(preSql);
//        System.out.println(sql);

        return preSql.executeUpdate();
    }

    public ArrayList<String> getColumns() throws SQLException {
        sql = "SELECT COLUMN_NAME from INFORMATION_SCHEMA.COLUMNS where TABLE_NAME='" + table_name + "'  AND TABLE_SCHEMA='"+db_name+"'";
        stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(sql);
        //String cols[] = new String[10];
        ArrayList<String> cols = new ArrayList<>();

        while (result.next()) {
            cols.add(result.getString("COLUMN_NAME"));
        }
        return cols;
    }

    public ResultSet fetchAll() throws SQLException {
        sql = "Select * from " + table_name;
//        System.out.println(sql);
        preSql = conn.prepareStatement(sql);
        System.out.println(preSql);
        return preSql.executeQuery();
    }

    public ResultSet fetchById(String id) throws SQLException {
        sql = "Select * from " + this.table_name + " where " + this.table_name + "_Id = ?";

        preSql = conn.prepareStatement(sql);
        preSql.setString(1, id);
        System.out.println(preSql);
        return preSql.executeQuery();
    }

    public String checkHashExists(String value) throws SQLException {
        sql = "Select " + this.table_name + "_Id from " + this.table_name + " where " + this.table_name + "_Hash2 = ?";

        preSql = conn.prepareStatement(sql);
        preSql.setString(1, value);
        System.out.println(preSql);
        ResultSet results = preSql.executeQuery();
        String id = new String();
        while (results.next()) {
            id = results.getString(this.table_name + "_Id");
        }
        return id;
    }

    public ResultSet fetchBlocks(String tableName, String columnName, String value) throws SQLException {
        sql = "Select * from " + tableName + " where " + columnName + " = ?";
//        System.out.println(sql);
        preSql = conn.prepareStatement(sql);
        preSql.setString(1, value);
        System.out.println(preSql);

        return preSql.executeQuery();
    }

    public boolean increase(String col,String name, String id, int amount) throws SQLException {
        String sql = "UPDATE " + table_name + " SET " + col + " = " + col + " + " + amount + " where "+name+" = ?";

        PreparedStatement preSql = conn.prepareStatement(sql);
        preSql.setString(1, id);

        System.out.println(preSql);
        return preSql.execute();
    }

    public boolean decrease(String col,String name, String id, int amount) throws SQLException {
        String sql = "UPDATE " + table_name + " SET " + col + " = " + col + " - " + amount + " where "+name+" = ?";

        PreparedStatement preSql = conn.prepareStatement(sql);
        preSql.setString(1, id);

        System.out.println(preSql);
        return preSql.execute();
    }

    public void closeConnection() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }
}
