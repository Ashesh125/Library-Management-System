<%-- 
    Document   : Book_List
    Created on : Mar 11, 2022, 10:23:22 AM
    Author     : WorldEdit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="./dashboard.jsp" /> 
<script src="<%=application.getContextPath()%>/Resources/script/builders.js"></script>
<div class="my-3 d-flex flex-column main-body">

    <h2 >Books</h2>

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
                url: "<%=application.getContextPath()%>/Forms/Book",
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
                    "targets": [0,4,5,8,9],
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

