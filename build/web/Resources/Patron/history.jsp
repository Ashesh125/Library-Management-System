<%-- 
    Document   : Main
    Created on : Apr 7, 2022, 1:29:20 PM
    Author     : WorldEdit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="./dashboard.jsp" />  
<div class="my-3 d-flex flex-column main-body">

    <h2 >Borrowed Books</h2>

    <table id="data-table" class="display " style="width:100%" >
        <thead>
            <tr>
                <th>Id</th>    
                <th>Name</th>
                <th>Issue Date</th>
                <th>Due Date</th>
                <th>Return Date</th>
                <th>Issued By</th>
            </tr>
        </thead>
    </table>
</div>
<div></div>

<script src="<%=application.getContextPath()%>/Resources/script/builders.js"></script>    
<script>
    $(document).ready(function () {
        $('#history').addClass("active");

        var table = $('#data-table').DataTable({

            ajax: {
                url: "<%=application.getContextPath()%>/Forms/BorrowerSearch?type=sp",
                dataSrc: 'datas'
            },
            columns: [
//                {data: 'sn'},
                {data: 'borrower_Id'},
                {data: 'book_Name'},
                {data: 'borrowed_Date'},
                {data: 'due_Date'},
                {data: 'returned'},
                {data: 'admin_Name'}
            ],
            "columnDefs": [
                {
                    "targets": [0],
                    "visible": false,
                    "searchable": false
                }
            ]
        });
        
    });




</script>
<style>

    #red{
        color: red;
    }

    #green{
        color: green;
    }
</style>
</body>
</html>


</body>
</html>
