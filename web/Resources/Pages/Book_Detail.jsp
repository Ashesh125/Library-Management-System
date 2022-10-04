<%-- 
    Document   : Book_Detail
    Created on : Mar 19, 2022, 10:02:14 AM
    Author     : WorldEdit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="../Dashboard/Dashboard_Nav.jsp" /> 

<script src="<%=application.getContextPath()%>/Resources/script/script.js"></script>
<script src="<%=application.getContextPath()%>/Resources/script/builders.js"></script>
<div class="col-9 my-3 d-flex flex-column justify-content-center">
    <h2 >Book</h2>
    <!-- Button trigger modal -->
    <div>
        <a href="<%=application.getContextPath()%>/Resources/Pages/Book_List.jsp" class="btn btn-primary col-1">Back</a>
        <button class="btn btn-primary" id="deleteBtn">Delete</button>
        <button type="button" class="btn btn-primary col-1" data-bs-toggle="modal" data-bs-target="#QRModal" id="hashBtn">QR</button>
    </div>
    <jsp:include page="../Components/QRBlock.jsp" /> 
    <form id="form-1" class="row g-3 needs-validation"  action="<%=application.getContextPath()%>/Forms/BookDetail" method="POST" novalidate>
        <div class="col-md-3">
            <label for="name" class="form-label">Book name</label>
            <input type="hidden" class="form-control" id="hash" value="" name="hash">
            <input type="hidden" class="form-control" id="book-id" value="0" name="id">
            <input type="text" class="form-control" id="name" value="" name="name" required>
            <div class="valid-feedback">
                Looks good!
            </div>
            <div class="invalid-feedback">
                Cannot be Empty!
            </div>
        </div>
        <div class="col-md-3">
            <label for="isbn" class="form-label col-10">ISBN</label>
            <button id="APIserach" class="btn btn-primary text-center p-0"  style="width:20px;height:20px;font-size:8px;display:none;"><i class="fas fa-search"></i></button>
            <input type="text" class="form-control" id="isbn" value="" name="isbn" onkeyup="checkISBN();" required>
            <div class="valid-feedback">
                Looks good!
            </div>
            <div class="invalid-feedback">
                Cannot be Empty!
            </div>
        </div>
        <div class="col-md-3">
            <label for="qty" class="form-label">Quantity</label>
            <input type="text" class="form-control" id="qty" value="" name="qty" required>
            <div class="valid-feedback">
                Looks good!
            </div>
            <div class="invalid-feedback">
                Cannot be Empty!
            </div>
        </div>
        <div class="col-md-3">
            <label for="selectPublisher" class="form-label">Publisher</label>
            <select class="form-select"  data-live-search="true" id="selectPublisher" name="publisher" required>
                <option disabled value="">Choose...</option>

            </select>
            <div class="invalid-feedback">
                Please select a valid Publisher.
            </div>
        </div>

        <div class="col-6">
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="available" id="ava">
                <label class="form-check-label" for="available">
                    Book Available
                </label>
            </div>
        </div>
        <div class="col-6">
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="return" id="req" >
                <label class="form-check-label" for="reqReturn">
                    Request Book Return
                </label>
            </div>
        </div>
        <div class="col-12 d-flex flex-wrap" id="genres">

            <div class="col-12 fw-bold">Genres</div>


        </div>
        <div class="col-12 d-flex flex-wrap" id="authors">

            <div class="col-12 fw-bold">Authors</div>

        </div>

        <div class="col-12">
            <button class="btn btn-primary" type="submit">Submit</button>
        </div>
    </form>


</div>
<div></div>


<script>


    $(document).ready(function () {
        $('#books').addClass("active");
        $.ajax({
            type: "GET",
            url: "<%=application.getContextPath()%>/Forms/Publisher",
            data: "datas",
            success: function (response) {
                var json_obj = $.parseJSON(response);
                appendSelect(json_obj, "#selectPublisher");
            },
            dataType: "html"
        });
        $.ajax({
            type: "GET",
            url: "<%=application.getContextPath()%>/Forms/Genre",
            data: "datas",
            success: function (response) {

                var json_obj = $.parseJSON(response);
                appendCheckbox(1, json_obj);
            },
            dataType: "html"
        });
        $.ajax({
            type: "GET",
            url: "<%=application.getContextPath()%>/Forms/Author",
            data: "datas",
            success: function (response) {

                var json_obj = $.parseJSON(response);
                appendCheckbox(2, json_obj);
            },
            dataType: "html"
        });
        const id = new URLSearchParams(window.location.search).get('id');
        if (id !== null) {
            $.ajax({
                type: "GET",
                url: "<%=application.getContextPath()%>/Forms/BookDetail?id=" + id,
                data: "datas",
                success: function (response) {

                    var json_obj = $.parseJSON(response);
                    console.log(json_obj);
                    //                formFill($.parseJSON(response));
                    formFillBook(json_obj['datas']);
                },
                dataType: "html"
            });
        } else {
            $('#hashBtn').hide();
            $('#deleteBtn').hide();
        }



        $('#deleteBtn').on('click', function () {
            let check = confirm("Confirm Delete");
            if (check) {
                let del = $('#book-id').val();
                $.ajax({
                    method: "POST",
                    url: "<%=application.getContextPath()%>/Forms/BookDetail",
                    data: {id: del, name: ""}
                }).done(function (msg) {
                    alert("Data Deleted: " + msg);
                });
                window.location.href = "<%=application.getContextPath()%>/Resources/Pages/Book_List.jsp";
            }
        });
        $('#APIserach').on("click", function () {
            $.ajax({
                method: "GET",
                url: "https://openlibrary.org/isbn/" + $('#isbn').val() + ".json",
                success: function (response) {
                    var obj = $.parseJSON(response);
                    $('#name').val(obj.title);
                    console.log(obj.publishers[0]);
                    if (!checkPublisher(obj.publishers[0])) {
                        if (confirm("Add new Publisher!!")) {
                            $.ajax({
                                method: "POST",
                                url: "<%=application.getContextPath()%>/Forms/Publisher",
                                data: {id: '0', name: obj.publishers[0]}
                            }).done(function (msg) {
                                alert("Success");
                                selectDropdown(obj.publishers[0]);
                            });
                        }

                    } else {
                        selectDropdown(obj.publishers[0]);
                    }
                },
                dataType: "html"
            });
        });
    });
    function checkISBN() {
        let isbn = $('#isbn').val();
        if (($('#book-id').val() == "" || $('#book-id').val() == "0") && (isbn.length == 10 || isbn.length == 13)) {
            $('#APIserach').show();
            $('#isbn').addClass("");
        } else {
            $('#APIserach').hide();
        }
    }

    function checkPublisher(name) {
        $.ajax({
            method: "POST",
            url: "<%=application.getContextPath()%>/Forms/addPublisher",
            data: {name: name},
            success: function (response) {
                let stat = $.parseJSON(response);
                if (stat == "true") {
                    return true;
                } else {
                    return false;
                }
            },
            dataType: "html"
        });
    }

    function selectDropdown(data){
        $('#selectPublisher').val(data);
    }
</script>
<style>.modal-backdrop {
        z-index: -1;
    }</style>
</body>
</html>