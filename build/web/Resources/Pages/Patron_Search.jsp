<%-- 
    Document   : Patron_Search
    Created on : Apr 19, 2022, 2:42:43 PM
    Author     : WorldEdit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../Dashboard/Dashboard_Nav.jsp" /> 
<script src="<%=application.getContextPath()%>/Resources/script/script.js"></script>
<script src="<%=application.getContextPath()%>/Resources/script/builders.js"></script>
<%
    String current = application.getContextPath() + "/Forms/BorrowerSearch";
    out.println("<script>" + "var current = '" + current + "';</script>");
%>
<div class="col-7 my-3 d-flex flex-column main-body">
    <h2 class="topic"></h2>
    <table id="data-table" class="display" style="width:100%" >
        <thead>
            <tr>

                <th>Borrower Id</th>
                <th>Patron Id</th>
                <th>Book Id</th>
                <th>Admin Id</th>
                <th>Name</th>
                <th>Book</th>
                <th>Admin</th>
                <th>Issue Date</th>
                <th>Due Date</th>
                <th>Returned</th>
            </tr>
        </thead>
    </table>
</div>
<div></div>
<script>
    $(document).ready(function () {

        var urlParams = new URLSearchParams(window.location.search);
        var type = urlParams.get('type'); // true (string)
        var topic = "";
        var hiddenCols = [0, 1, 2, 3];
        if(type != "a"){
            hiddenCols.push(9);
        }
        switch (type) {
            case "b":
                topic = "Borrowers";
                current += "?type=" + type;
                break;

            case "f":
                topic = "Black List";
                current += "?type=" + type;
                break;

            case "a":
                topic = "History";
                current += "?type=" + type;
                break;


        }
        $(".topic").append(topic);
        $('#patron').addClass("active");
        var table = $('#data-table').DataTable({

            ajax: {
                url: current,
                dataSrc: 'datas'
            },
            columns: [
//                {data: 'sn'},
                {data: 'borrower_Id'},
                {data: 'patron_Id'},
                {data: 'book_Id'},
                {data: 'admin_Id'},
                {data: 'patron_Name'},
                {data: 'book_Name'},
                {data: 'admin_Name'},
                {data: 'borrowed_Date'},
                {data: 'due_Date'},
                {data: 'returned'}
            ],
            "columnDefs": [
                {
                    "targets": hiddenCols,
                    "visible": false,
                    "searchable": false
                }
            ]
        });

      

        $('#data-table tbody').on('dblclick', 'tr', function () {

        });

    });

</script>



</body>
</html>
