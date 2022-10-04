<%-- 
    Document   : BookForm
    Created on : Feb 16, 2022, 3:25:21 PM
    Author     : WorldEdit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Page</title>
    </head>
    <body>
        <form method="POST" action="<%=application.getContextPath()%>/Forms/Book">
            <label for="book_Name">Book Name</label><br>
            <input type="text" placeholder="Book Name" name="book_Name"><br>
            <label for="book_Name">Book Qty</label><br>
            <input type="text" placeholder="Book Qty" name="book_Quantity"><br>
            <label for="book_Name">Book Available</label><br>
            <input type="checkbox" placeholder="Book Available" name="book_Available">
            <br><label for="book_Name">Book Publisher</label><br>
            <input type="text" placeholder="Book Publisher" name="book_Publisher">
            <br><label for="book_Name">Book ISBN</label><br>
            <input type="text" placeholder="Book ISBN" name="book_ISBN">
            <br><input type="file" name="book_PDF">
            <br><input type="file" name="book_Image">
            <br><input type="submit">
        </form>
    </body>
</html>
