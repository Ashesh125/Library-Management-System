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
                <th >Name</th>
                <th>ISBN</th>
                <th>Issue Date</th>
                <th>Due Date</th>
                <th>Return</th>  
                <th>Image</th>
                <th>PDF</th>
            </tr>
        </thead>
    </table>
</div>
<div></div>

<script src="<%=application.getContextPath()%>/Resources/script/builders.js"></script>    
<script>
    $(document).ready(function () {
        $('#home').addClass("active");

        var table = $('#data-table').DataTable({

            ajax: {
                url: "<%=application.getContextPath()%>/Forms/Borrowed",
                dataSrc: 'datas'
            },
            columns: [
//                {data: 'sn'},
                {data: 'id'},
                {data: 'name'},
                {data: 'isbn'},
                {data: 'issue_date'},
                {data: 'due_date'},
                {data: 'return', render: function (data, type, row) {
                        return renderCheck(data);
                    }, title: 'Return', className: "dt-body-center"},
//            {data: 'return'},
                {data: 'image'},
                {data: 'pdf'}
            ],
            "columnDefs": [
                {
                    "targets": [0,5,7,6],
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
