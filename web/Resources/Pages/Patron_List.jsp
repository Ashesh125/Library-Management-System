<%-- 
    Document   : Patron_List
    Created on : Apr 9, 2022, 7:27:11 PM
    Author     : WorldEdit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../Dashboard/Dashboard_Nav.jsp" /> 

<script src="<%=application.getContextPath()%>/Resources/script/script.js"></script>
<script src="<%=application.getContextPath()%>/Resources/script/builders.js"></script>
<%
    String current = application.getContextPath() + "/Forms/Patron";
    String topic = "Patron";
    out.println("<script>" + "var current = '" + current + "'; var topic = '" + topic + "';</script>");
%>
<div class="col-7 my-3 d-flex flex-column main-body">
    <h2 class="topic"></h2>
    <div>
        <!-- Button trigger modal -->
        <button type="button" id="btn-1" class="btn btn-primary col-1" data-bs-toggle="modal" data-bs-target="#myModal">
            New
        </button>
    </div>
    <jsp:include page="../Components/QRBlock.jsp" /> 
    <!-- Modal -->
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
                            <label for="name" class="form-label">Full Name</label>

                            <input type="hidden" class="form-control" id="id" value="0" name="id">
                            <input type="hidden" class="form-control" id="hash" value="" name="hash">

                            <input type="text" class="form-control" id="name" value="" name="name" required>
                            <div class="valid-feedback">
                                Looks good!
                            </div>
                            <div class="invalid-feedback">
                                Cannot be Empty!
                            </div>
                        </div>
                        <div class="col-md-6">
                            <label for="roll" class="form-label">Roll</label>
                            <input type="text" class="form-control" id="roll" value="" name="roll" required>
                            <div class="valid-feedback">
                                Looks good!
                            </div>
                            <div class="invalid-feedback">
                                Cannot be Empty!
                            </div>
                        </div>
                        <div class="col-md-6">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" class="form-control" id="email" value="" name="email" required>
                            <div class="valid-feedback">
                                Looks good!
                            </div>
                        </div>
                        <div class="col-md-6">
                            <label for="phone" class="form-label">Phone</label>
                            <input type="number" class="form-control" id="contact" value="" name="phone" required>
                            <div class="valid-feedback">
                                Looks good!
                            </div>
                        </div>
                        <div class="col-md-6">
                            <label for="selectFaculty" class="form-label">Faculty</label>
                            <select class="form-select" id="selectFaculty" name="faculty" required>
                                <option disabled value="">Choose...</option>

                            </select>
                            <div class="invalid-feedback">
                                Please select a valid Faculty.
                            </div>
                        </div>
                        <div class="col-md-3">
                            <label for="qty" class="form-label">Borrowed Qty</label>
                            <input type="number" class="form-control" value="0" id="qty" value="" name="qty" required>
                            <div class="valid-feedback">
                                Looks good!
                            </div>
                            <div class="invalid-feedback">
                                Cannot be Empty!
                            </div>
                        </div>
                        <div class="col-md-3">
                            <label for="fine" class="form-label">Fine</label>
                            <input type="number" class="form-control" value="0" id="fine" value="" name="fine" required>
                            <div class="valid-feedback">
                                Looks good!
                            </div>
                            <div class="invalid-feedback">
                                Cannot be Empty!
                            </div>
                        </div>


                        <div class="col-md-6" id="password-block">
                            <label for="password" class="form-label">Password</label>
                            <input type="password" class="form-control" id="password" value="" name="password" required>

                            <div class="invalid-feedback">
                                Cannot be Empty!
                            </div>
                        </div>
                        <div class="col-md-6" id="cpassword-block">
                            <label for="cpassword" class="form-label">Confirm Password</label>
                            <input type="password" class="form-control" id="cpassword" value="" name="password" required>

                            <div class="invalid-feedback">
                                Cannot be Empty!
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" name="flagged" id="flagged" >
                                <label class="form-check-label" for="flagged">
                                    BlackList
                                </label>
                            </div>
                        </div>

                        <div class="col-md-12">
                            <div class="custom-message">

                            </div>
                        </div>
                        <div class="col-12">
                            <button class="btn btn-primary" type="submit">Submit</button>
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
                <th>Name</th>
                <th>Roll</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Qty</th>
            </tr>
        </thead>
    </table>
</div>
<div></div>


<script>
    $(document).ready(function () {

        $('#patron').addClass("active");

        document.getElementById('form-1').onsubmit = function () {
            let status = isValidEmail($('#email').val()) && isValidContact($('#contact').val());
            if ($('#id').val() != "0" && $('#password').val() == "" && $('#cpassword').val() == "") {
                return status;
            } else {
                return (status && isValidPassword($('#password').val(), $('#cpassword').val()));
            }
        };


        var table = $('#data-table').DataTable({

            ajax: {
                url: current,
                dataSrc: 'datas'
            },
            columns: [
//                {data: 'sn'},
                {data: 'id'},
                {data: 'name'},
                {data: 'roll'},
                {data: 'email'},
                {data: 'phone'},
                {data: 'qty'}



            ],
            "columnDefs": [
                {
                    "targets": [0],
                    "visible": false,
                    "searchable": false
                }
            ]
        });

        $.ajax({
            type: "GET",
            url: "<%=application.getContextPath()%>/Forms/Faculty",
            data: "datas",
            success: function (response) {
                var json_obj = $.parseJSON(response);
                appendSelect(json_obj, "#selectFaculty");
            },
            dataType: "html"
        });

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



        $('#btn-1').on('click', function () {
            $('#id').val("0");
            $('#name').val("");
            $('#roll').val("");
            $('#hash').val("");
            $('#email').val("");
            $('#contact').val("");
            $('#qty').val("0");
            $('#fine').val("0");
            $("#password").attr("required");
            $("#cpassword").attr("required");
            document.getElementById('flagged').checked = false;
            $('#hashBtn').hide();

        });

        $('#data-table tbody').on('dblclick', 'tr', function () {
            var data = table.row(this).data();

            $('#deleteBtn').show();
            $('#id').val(data['id']);
            $('#name').val(data['name']);
            $('#contact').val(data['phone']);
            $('#email').val(data['email']);
            $('#qty').val(data['qty']);
            $('#fine').val(data['fine']);
            $('#roll').val(data['roll']);
            $('#hash').val(data['hash']);
            $("#password").removeAttr("required");

            $("#cpassword").removeAttr("required");
            $(".custom-message").hide();

            $('#hashBtn').show();
            $('#myModal').modal('show');
        });

    });

</script>
<style>.modal-backdrop {
        z-index: -1;
    }</style>
</body>
</html>