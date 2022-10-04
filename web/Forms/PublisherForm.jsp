<%-- 
    Document   : PublisherForm
    Created on : Feb 16, 2022, 3:52:28 PM
    Author     : WorldEdit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<jsp:include page="../Resources/Dashboard/Dashboard_Nav.jsp" /> 

<div class="col-7 my-3 d-flex flex-column justify-content-center">
        <form action="<%=application.getContextPath()%>/Forms/Publisher" method="POST">
            <input type="text" placeholder="Enter Publisher name" name="publisher_Name">
            <input type="submit">
        </form>
            <table id="table_id" class="table table-striped"  class="display">
        <thead>
        </thead>
        <tbody>
           
        </tbody>
    </table>
            </div>
<div></div>
    </body>
    <script>
//        $(document).ready(function () {
//            
//            
//            $('#table_id').DataTable({
//                ajax:{
//                    method:"GET",
//                    url:"<%=application.getContextPath()%>/Forms/Publisher",
//                    dataSrc:"datas"
//                },
//                columns:[
//                    {data: "id"},
//                    {data : "name"}
//                ]
//            });
//            
//            
//                $("#signup").click(function () {
//                    $("#form1").toggle('slow');
//                });
//       
//        });
    
    </script>
</html>
