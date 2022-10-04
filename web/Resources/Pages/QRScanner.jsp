<%-- 
    Document   : QRScanner
    Created on : Apr 9, 2022, 11:27:06 AM
    Author     : WorldEdit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../Dashboard/Dashboard_Nav.jsp" /> 
<script src="<%=application.getContextPath()%>/Resources/script/builders.js"></script>
<script src="<%=application.getContextPath()%>/Resources/script/html5-qrcode.min.js"></script>
<%
    String current = application.getContextPath() + "/Forms/Book";
    String topic = "Scan";
    out.println("<script>" + "var current = '" + current + "'; var topic = '" + topic + "';</script>");
%>


<div class="col-7 my-3 d-flex flex-column main-body " style="overflow-y:scroll;">
    <div class="modal fade"  id="alertModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" data-backdrop="false">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title " id="exampleModalLabel">Perform Action </h5>
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

                            <input type="text" class="form-control" id="name" value="" name="name" required disabled readonly>
                            <div class="valid-feedback">
                                Looks good!
                            </div>
                            <div class="invalid-feedback">
                                Cannot be Empty!
                            </div>
                        </div>
                        <div class="col-md-6">
                            <label for="roll" class="form-label">Roll</label>
                            <input type="text" class="form-control" id="roll" value="" name="roll" required disabled readonly>
                            <div class="valid-feedback">
                                Looks good!
                            </div>
                            <div class="invalid-feedback">
                                Cannot be Empty!
                            </div>
                        </div>
                        <div class="col-md-6">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" class="form-control" id="email" value="" name="email" required disabled readonly>
                            <div class="valid-feedback">
                                Looks good!
                            </div>
                            <div class="invalid-feedback">
                                Cannot be Empty!
                            </div>
                        </div>
                        <div class="col-md-6">
                            <label for="phone" class="form-label">Phone</label>
                            <input type="number" class="form-control" id="phone" value="" name="phone" required disabled readonly>
                            <div class="valid-feedback">
                                Looks good!
                            </div>
                            <div class="invalid-feedback">
                                Cannot be Empty!
                            </div>
                        </div>
                        <div class="col-md-6">
                            <label for="faculty" class="form-label">Faculty</label>
                            <input type="faculty" class="form-control" id="faculty" value="" name="faculty" required disabled readonly>
                        </div>
                        <div class="col-md-3">
                            <label for="qty" class="form-label">Borrowed Qty</label>
                            <input type="number" class="form-control" value="0" id="qty" value="" name="qty" required disabled readonly>
                            <div class="valid-feedback">
                                Looks good!
                            </div>
                            <div class="invalid-feedback">
                                Cannot be Empty!
                            </div>
                        </div>
                        <div class="col-md-3">
                            <label for="fine" class="form-label">Fine</label>
                            <input type="number" class="form-control" value="0" id="fine" value="" name="fine" required disabled readonly>
                            <div class="valid-feedback">
                                Looks good!
                            </div>
                            <div class="invalid-feedback">
                                Cannot be Empty!
                            </div>
                        </div>

                        <div class="col-12">
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" name="flagged" id="flagged" disabled readonly>
                                <label class="form-check-label" for="flagged">
                                    BlackList
                                </label>
                            </div>
                        </div>
                        <button type="button" class="btn btn-primary" id="btn-9">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div style="width: 500px" class="my-5" id="reader"></div>
    <div id="magic-table" style="width:100%"   data-bs-spy="scroll">
        <button type="button" id="btn-x" class="btn btn-primary">
            Cancel
        </button>
        <table id="data-table" class="display table table-striped" style="width:100%">
            <thead>
                <tr>
                    <th>Id</th>    
                    <th>Name</th>
                    <th>ISBN</th>
                    <th>Publisher</th>  
                    <th>Available</th>  
                    <th>Remove</th>
                </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
</div>

<div></div>
</body>
<Script>
    $(document).ready(function () {
        var t = $('#data-table').DataTable({
            "columnDefs": [{
                    "targets": 5,
                    "data": null,
                    "defaultContent": "<button class='btn btn-primary removeRow'>Remove</button>"
                }]
        });
        var json_obj;
        var counter = 1;

        $('#qr').addClass("active");

        $(".topic").append(topic);
        $('#btn-x').hide();
        $('#btn-x').on("click", function () {
            if (confirm("All scanned books will be lost!!!")) {
                window.location.reload();
            }
        });

        $.ajax({
            type: "GET",
            url: "<%=application.getContextPath()%>/Forms/Book",
            data: "datas",
            success: function (response) {

                books = $.parseJSON(response);

            },
            dataType: "html"
        });

        $('#data-table tbody').on('dblclick', 'tr', function () {
            var data = t.row(this).data();
            let url = "<%=application.getContextPath()%>/Resources/Pages/Book_Detail.jsp?id=" + data[0];
            document.location.href = url;
        });

        $("#data-table tbody").on('click', '.removeRow', function () {
            t.row($(this).parents('tr')).remove().draw(false);
        });

        function onScanSuccess(decodedText, decodedResult) {
            // Handle on success condition with the decoded text or result.
            console.log(`Scan result: ${decodedText}`, decodedResult);
            var hash = decodedResult.decodedText;


            var found = getValueByKey(books['datas'], "hash", hash);

            if (found.id == null) {
                $.ajax({
                    type: "GET",
                    url: "<%=application.getContextPath()%>/Forms/PatronSearch?hash=" + hash,
                    data: "user",
                    success: function (response) {
                        patron = $.parseJSON(response);
                        console.log(patron['user']);
                        if (patron['user'].id == null) {
                            alert("NO Such Book or User Exists!");
                        } else {
                            let data = patron['user'];
                            $('#alertModal').modal('show');
                            $('#btn-x').show();
                            $('#id').val(data['id']);
                            $('#name').val(data['name']);
                            $('#phone').val(data['phone']);
                            $('#email').val(data['email']);
                            $('#qty').val(data['qty']);
                            $('#faculty').val(data['faculty']);

                            $('#fine').val(data['fine']);
                            $('#roll').val(data['roll']);
                            $('#hash').val(data['hash']);

                            $('#hashBtn').show();
                            $('#myModal').modal('show');

                        }
                    },
                    dataType: "html"
                });


            } else {
                t.row.add([
                    found.id,
                    found.name,
                    found.isbn,
                    found.publisher,

                    found.available
                ]).draw(false);
                counter++;


            }

            

        }

        function getValueByKey(data, key, value) {
            var i, len = data.length;
            for (i = 0; i < len; i++) {
                if (data[i] && data[i].hasOwnProperty(key) && data[i].hash === value) {

                    console.log(data[i]);
                    return data[i];
                }
            }

            return -1;
        }

        Html5Qrcode.getCameras().then(cameras => {
            /**
             * devices would be an array of objects of type:
             * { id: "id", label: "label" }
             */
            if (devices && devices.length) {
                var cameraId = devices[0].id;
                // .. use this to start scanning.
            }
        }).catch(err => {
            // handle err   
        });
        var html5QrcodeScanner = new Html5QrcodeScanner(
                "reader", {
                    fps: 10,
                    qrbox: 250
                });
        html5QrcodeScanner.render(onScanSuccess, onScanError);
        function onScanError(errorMessage) {

        }

        $('#btn-9').on("click", function () {
            let b = t.column(0).data();
            let bookArr = "";
            for (let i = 0; i < b.length; i++) {
                bookArr += b[i] + ",";
                console.log(bookArr);
            }

            $.ajax({
                type: "POST",
                url: "<%=application.getContextPath()%>/Forms/BookProcess",
                data: {patron_Id: $('#id').val(), books: bookArr},
                success: function (response) {
                    if (!alert("Success")) {
                        location.reload();
                    }
                },
                error: function (xhr, ajaxOptions, thrownError) {
                    alert(xhr.status);
                    alert(thrownError);
                }
            });

        });
    });


</Script>
</html>
