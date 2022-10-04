<%-- 
    Document   : error
    Created on : Apr 5, 2022, 10:30:04 PM
    Author     : WorldEdit
--%>


<%@ page isErrorPage="true" import="java.io.*" contentType="text/plain"%>

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
  
