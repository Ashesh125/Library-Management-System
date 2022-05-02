<%-- 
    Document   : Faculty_List
    Created on : Mar 17, 2022, 12:36:00 PM
    Author     : WorldEdit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../Dashboard/Dashboard_Nav.jsp" /> 
<% int pageno = 3;
    String current = application.getContextPath();
    String topic = "";%>
<%
    switch(pageno){
        case 0:
            current += "/Forms/Publisher";
            topic = "Publisher";
            break;
        
        case 1:
            current += "/Forms/Author";
            topic = "Author";
            break;
            
        case 2:
            current += "/Forms/Genre";
            topic = "Genre";
            break;
            
        case 3:
            current += "/Forms/Faculty";
            topic = "Faculty";
            break;
    }    

    out.println("<script>" + "var current = '"+current+"'; var topic = '"+topic+"';var selected = '"+pageno+"';</script>");
%>
<jsp:include page="Single_DataTables.jsp" />