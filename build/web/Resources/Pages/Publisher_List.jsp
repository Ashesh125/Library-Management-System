<%-- 
    Document   : Publisher_List
    Created on : Mar 3, 2022, 3:58:39 PM
    Author     : WorldEdit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../Dashboard/Dashboard_Nav.jsp" /> 
<% int pageno = 0;
    String current = "";
    String topic = "";%>
<%
    switch(pageno){
        case 0:
            current = application.getContextPath() + "/Forms/Publisher";
            topic = "Publisher";
            break;
        
        case 1:
            current = application.getContextPath() + "/Forms/Author";
            topic = "Author";
            break;
            
        case 2:
            current = application.getContextPath() + "/Forms/Genre";
            topic = "Genre";
            break;
            
        case 3:
            current = application.getContextPath() + "/Forms/Faculty";
            topic = "Faculty";
            break;
    }
    

    out.println("<script>" + "var current = '"+current+"'; var topic = '"+topic+"';var selected = '"+pageno+"';</script>");
%>
<jsp:include page="Single_DataTables.jsp" />