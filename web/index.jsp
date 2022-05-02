<%-- 
    Document   : index
    Created on : Feb 16, 2022, 3:21:16 PM
    Author     : WorldEdit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import= "java.util.*"%>
<%  
    HttpSession ses = request.getSession();
    if(ses != null && ses.getAttribute("admin_Name") != null){
        response.sendRedirect(request.getContextPath() + "/Dashboard/Dashboard.jsp");
    }else if(ses != null && ses.getAttribute("patron_Name") != null){
        response.sendRedirect(request.getContextPath() + "/Resources/Patron/Main.jsp");
    }else if(ses != null && ses.getAttribute("noUser") != null){
        out.print("<script>alert('No such Accounts Exists!');</script>");
        ses.setAttribute("noUser", null);
    }

%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>

<body style="background-color: #0cbaba;
background-image: linear-gradient(315deg, #0cbaba 0%, #380036 74%);
">
    <div class="row d-flex justify-content-evenly" style="width: 100vw;height: 100vh;">
        <div class="my-auto col-md-6 col-sm-12">
            <img class="mx-auto  d-block" src="<%=application.getContextPath()%>/Resources/Images/logo.png">
        </div>
        <div class="my-auto col-md-6 col-sm-8">

            <form method="POST" action="<%=application.getContextPath()%>/Forms/LoginCheck" class="p-5 border rounded mx-auto col-md-6 shadow-lg  needs-validation"   novalidate>
                <div class="text-center">
                    <h1>LOGIN</h1>
                </div>
                <div class="col-md-12 my-1">
                    <label for="username" class="form-label text-light">User name</label>
                    <input type="text " class="form-control " name="userid" id="username" required>
                    <div class="valid-feedback ">
                        Looks good!
                    </div>
                </div>
                <div class="col-md-12 my-1">
                    <label for="pswd" class="form-label text-light">Password</label>
                    <input type="password " class="form-control " name="pswd" id="pswd" required>
                    <div class="valid-feedback " style="background-color: red;">
                        Looks good!
                    </div>
                </div>
                <div class="d-flex justify-content-center pt-2">
                    <button class="btn btn-primary " type="submit ">Login</button>
                </div>
            </form>
        </div>
    </div>
    </body>
</html>
