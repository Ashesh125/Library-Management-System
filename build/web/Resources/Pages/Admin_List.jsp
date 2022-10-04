<%-- 
    Document   : Admin_List
    Created on : Apr 6, 2022, 7:04:42 PM
    Author     : WorldEdit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../Dashboard/Dashboard_Nav.jsp" /> 
<%
    String current = application.getContextPath() + "/Forms/Admin";
    String topic = "Admin";
    out.println("<script>" + "var current = '" + current + "'; var topic = '" + topic + "';</script>");
%>

<script src="<%=application.getContextPath()%>/Resources/script/builders.js"></script>
<script src="<%=application.getContextPath()%>/Resources/script/script.js"></script>
<div class="col-7 my-3 d-flex flex-column main-body">

    <h2 >Admin</h2>
    <!-- Button trigger modal -->
    <div>
        <button type="button" id="btn-1" class="btn btn-primary col-1" data-bs-toggle="modal" data-bs-target="#myModal">
            New
        </button>

    </div>
    <jsp:include page="../Components/QRBlock.jsp" /> 
    <div class="modal fade"  id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" data-backdrop="false">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title " id="exampleModalLabel">Add<div class="topic"></div> </h5>
                    <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="form-1" class="row g-3 needs-validation"  action="" method="POST" novalidate>
                        <div class="col-md-6">
                            <label for="name" class="form-label">Name</label>

                            <input type="hidden" class="form-control" id="id" value="0" name="id">
                            <input type="hidden" class="form-control" id="hash" value="" name="hash">

                            <input type="text" class="form-control" id="name" value="" name="name" required>

                            <div class="invalid-feedback">
                                Cannot be Empty!
                            </div>
                        </div>
                        <div class="col-md-6">
                            <label for="roll" class="form-label">Roll no</label>
                            <input type="text" class="form-control" id="roll" value="" name="roll" required>
                            <div class="invalid-feedback">
                                Cannot be Empty!
                            </div>
                        </div>
                        <div class="col-md-12">
                            <label for="password" class="form-label">Password</label>
                            <input type="password" class="form-control" id="password" value="" name="password" required>

                            <div class="invalid-feedback">
                                Cannot be Empty!
                            </div>
                        </div>
                        <div class="col-md-12">
                            <label for="cpassword" class="form-label">Confirm Password</label>
                            <input type="password" class="form-control" id="cpassword" value="" name="password" required>

                            <div class="invalid-feedback">
                                Cannot be Empty!
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="custom-message">

                            </div>
                        </div>

                        <div class="col-12">
                            <button class="btn btn-primary" id="submit" type="submit">Submit</button>

                            <button class="btn btn-primary" id="deleteBtn" style="display:none;">Delete</button>
                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#QRModal" id="hashBtn">QR</button>

                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <table id="data-table" class="display" style="width:100%" >
        <thead>
            <tr>

                <th>Id</th>
                <!--                <th>S.N.</th>-->
                <th>Name</th>
                <th>Roll</th>
            </tr>
        </thead>
    </table>
</div>
<div></div>


<script>


    $(document).ready(function () {
        $('#admin').addClass("active");


        document.getElementById('form-1').onsubmit = function () {
            return isValidPassword($('#password').val(),$('#cpassword').val());
        };

        document.getElementById('form-1').onsubmit = function () {
            if ($('#id').val() != "0" && $('#password').val() == "" && $('#cpassword').val() == "") {
                return true;
            } else {
                return (isValidPassword($('#password').val(), $('#cpassword').val()));
            }
        };

        var table = $('#data-table').DataTable({
            dom: 'frtipB',
            buttons: [
                'print', 'pdf'
            ],
            ajax: {
                url: current,
                dataSrc: 'datas'
            },
            columns: [
//                {data: 'sn'},
                {data: 'id'},

                {data: 'name'},
                {data: 'roll'},
            ],
            "columnDefs": [
                {
                    "targets": [0],
                    "visible": false,
                    "searchable": false
                }
            ]
        });

        $(".buttons-print").addClass("btn btn-secondary ms-2 my-2");
        $(".buttons-pdf").addClass("btn btn-secondary my-2");
        
        $('#deleteBtn').on('click', function () {
            let check = confirm("Confirm Delete");
            if (check) {
                let del = $('#id').val();
                $.ajax({
                    method: "POST",
                    url: current,
                    data: {id: del, name: ""}
                }).done(function (msg) {
                    alert("Data Deleted: " + msg);
                });
                table.row('.selected').remove().draw(true);
            }
        });


        $('#data-table tbody').on('dblclick', 'tr', function () {
            var data = table.row(this).data();
            $('#deleteBtn').show();
            $('#hashBtn').show();
            $('#id').val(data['id']);
            $('#name').val(data['name']);
            $('#roll').val(data['roll']);

            $('#hash').val(data['hash']);
            
            $("#password").removeAttr("required");

            $("#cpassword").removeAttr("required");
            $(".custom-message").hide();

            $('#myModal').modal('show');
        });
        $('#btn-1').on("click", function () {
            $('#hashBtn').hide();
            
            $("#password").attr("required");
            $("#cpassword").attr("required");
        });


        $('#deleteBtn').on('click', function () {
            let check = confirm("Confirm Delete");
            if (check) {
                let del = $('#id').val();
//                alert(del);
                $.ajax({
                    method: "POST",
                    url: "<%=application.getContextPath()%>/Forms/Admin",
                    data: {id: del, name: "",password:"",roll:""}
                }).done(function (msg) {
                    alert("Data Deleted: " + msg);
                });
                window.location.replace("<%=application.getContextPath()%>/Resources/Pages/Admin_List.jsp");
            }
        });
    }
    );

</script>
</body>
</html>
