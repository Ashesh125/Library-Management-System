<%-- 
    Document   : Book_List
    Created on : Mar 11, 2022, 10:23:22 AM
    Author     : WorldEdit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../Dashboard/Dashboard_Nav.jsp" /> 
<script src="<%=application.getContextPath()%>/Resources/script/builders.js"></script>
<div class="my-3 d-flex flex-column main-body">

    <h2 >Books</h2>

    <a href="<%=application.getContextPath()%>/Resources/Pages/Book_Detail.jsp" class="btn btn-primary col-1">Add</a>

    <table id="book-table" class="display " style="width:100%" >
        <thead>
            <tr>
                <th>Id</th>    
                <th >Name</th>
                <th>ISBN</th>

                <th>Publisher</th>
                <th>Total</th>
                <th>Quantity</th>
                
                <th>Available</th>
                <th>Return</th>          
                <th>Image</th>
                <th>PDF</th>
            </tr>
        </thead>
    </table>
</div>
<div></div>


<script>
    $(document).ready(function () {
        $('#books').addClass("active");
        
      
        var table = $('#book-table').DataTable({

            ajax: {
                url: "http://localhost:8080/LibraryTest/Forms/Book",
                dataSrc: 'datas'
            },
            columns: [
//                {data: 'sn'},
                {data: 'id'},
                {data: 'name'},
                {data: 'isbn'},
                {data: 'publisher'},
                
                {data: 'total'},
                {data: 'qty'},
                {data: 'available', render: function (data, type, row) {
                        return renderCheck(data);
                    }, title: 'Available', className: "dt-body-center"},
                {data: 'return', render: function (data, type, row) {
                        return renderCheck(data);
                    }, title: 'Return', className: "dt-body-center"},
//            {data: 'return'},
                {data: 'image'},
                {data: 'pdf'}
            ],
            "columnDefs": [
                {
                    "targets": [0],
                    "visible": false,
                    "searchable": false
                },
                {
                    "targets": [3],
                    "visible": false,
                }
            ]
        });
          $('#book-table tbody').on('click', 'tr', function () {
            var data = table.row(this).data();
            let url = "<%=application.getContextPath()%>/Resources/Pages/Book_Detail.jsp?id=" + data['id'];
            document.location.href = url;
            
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

