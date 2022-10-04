<%-- 
    Document   : formPatron
    Created on : May 29, 2022, 9:02:22 AM
    Author     : WorldEdit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="./dashboard.jsp" />  
<div class="my-3 col-5 d-flex flex-column main-body p-3">
    <h2 >Profile</h2>
    <img src="<%=application.getContextPath()%>/Resources/Images/unknown-user.jpg" class="col-2 p-2 rounded-corner mx-auto" alt="alt"/>
    <form id="form-1" class="row g-3 needs-validation"  action="<%=application.getContextPath()%>/Forms/changePswd" method="POST" novalidate>
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
            <input type="text" class="form-control" id="roll" value="" name="roll" required readonly> 
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
                <input class="form-check-input" ty pe="checkbox" name="flagged" id="flagged" disabled readonly>
                <label class="form-check-label" for="flagged">
                    BlackList
                </label>
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
        <div class="col-md-12">
            <div class="custom-message">
            </div>
        </div>
        <button class="btn btn-primary col-2 mx-auto" id="submit" type="submit">Submit</button>
    </form>

</div>
<div></div>

<script src="<%=application.getContextPath()%>/Resources/script/builders.js"></script>    
<script>
    $(document).ready(function () {
        $('#profile').addClass("active");

        $.ajax({
            type: "GET",
            url: "<%=application.getContextPath()%>/Forms/PatronSearch?hash=none",
            data: "user",
            success: function (response) {
                patron = $.parseJSON(response);
                console.log(patron['user']);

                let data = patron['user'];
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


            },
            dataType: "html"
        });



        document.getElementById('form-1').onsubmit = function () {
            return isValidPassword($('#password').val(), $('#cpassword').val());
        };

        document.getElementById('form-1').onsubmit = function () {
            if ($('#id').val() != "0" && $('#password').val() == "" && $('#cpassword').val() == "") {
                return true;
            } else {
                return (isValidPassword($('#password').val(), $('#cpassword').val()));
            }
        };
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
