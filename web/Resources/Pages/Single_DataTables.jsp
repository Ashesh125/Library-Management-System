<%-- 
    Document   : Single_DataTables
    Created on : Mar 15, 2022, 7:43:56 AM
    Author     : WorldEdit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="<%=application.getContextPath()%>/Resources/script/script.js"></script>
<div class="col-7 my-3 d-flex flex-column main-body">
    <h2 class="topic"></h2>
    <div>
        <!-- Button trigger modal -->
        <button type="button" id="btn-1" class="btn btn-primary col-1" data-bs-toggle="modal" data-bs-target="#myModal">
            New
        </button>
    </div>

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
                        <div class="col-md-4">
                            <label for="name" class="form-label"><div class="topic"></div> Name</label>
                            <input type="hidden" class="form-control" name="id" id="id" value="0" required>
                            <input type="text" class="form-control" name="name" id="name" value="" required>
                            <div class="valid-feedback">
                                Looks good!
                            </div>
                            <div class="invalid-feedback">
                                Cannot be Empty!
                            </div>
                        </div>
                        <div class="col-12">
                            <button class="btn btn-primary" type="submit">Submit</button>
                            <button class="btn btn-primary" id="deleteBtn" style="display:none;">Delete</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <table id="data-table" class="display hover" style="width:100%" >
        <thead>
            <tr>

                <th>Id</th>
                <!--                <th>S.N.</th>-->
                <th>Name</th>
            </tr>
        </thead>
    </table>
</div>
<div></div>


<script>
    $(document).ready(function () {

        $('#form-1').attr('action', current);

        switch (selected) {
            case '0':
            case '1':
                $('#dropdown5').addClass("active");
                break;

            case '2':
            case '3':
                $('#dropdown6').addClass("active");
                break;

            case '4':
                $('#books').addClass("active");
                break;
        }

        var table = $('#data-table').DataTable({

            ajax: {
                url: current,
                dataSrc: 'datas'
            },
            columns: [
//                {data: 'sn'},
                {data: 'id'},
                {data: 'name'}
            ],
            "columnDefs": [
                {
                    "targets": [0],
                    "visible": false,
                    "searchable": false
                }
            ]
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

        
        $('#data-table tbody').on('dblclick', 'tr', function () {
            var data = table.row(this).data();

            $('#deleteBtn').show();
            $('#id').val(data['id']);
            $('#name').val(data['name']);
            $('#myModal').modal('show');
        });


    });
    
</script>
<style>.modal-backdrop {
        z-index: -1;
    }</style>
</body>
</html>
