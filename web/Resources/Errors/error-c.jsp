<%-- 
    Document   : error-c
    Created on : Apr 6, 2022, 2:40:50 PM
    Author     : WorldEdit
--%>


<%@page import="java.io.*"%>
<%@ page isErrorPage="true" import="Exceptions.*" contentType="text/plain"%>

        Message:
        <%=exception.getMessage()%>
       
        Exception :
        <%
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            exception.printStackTrace(printWriter);
            out.println(stringWriter);
            printWriter.close();
            stringWriter.close();
        %>
  
