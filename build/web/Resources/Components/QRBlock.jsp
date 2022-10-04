<%-- 
    Document   : QRBlock
    Created on : Apr 12, 2022, 4:06:37 PM
    Author     : WorldEdit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="modal fade"  id="QRModal" tabindex="-1" role="dialog" aria-labelledby="QRModalLabel" aria-hidden="true" data-backdrop="false">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h2>QR code</h2>
                <a href="#" class="button btn btn-primary" id="btn-download" download="">Download</a>
            </div>
            <div class="modal-body ">
                <canvas id="qr-code" class="rounded mx-auto d-block"></canvas>       

            </div>
        </div>
    </div>
    <script>var buttonD = document.getElementById('btn-download');
        buttonD.addEventListener('click', function (e) {
            $('#btn-download').attr("download", $("#name").val());
            buttonD.href = document.getElementById('qr-code').toDataURL('image/png');
        });</script>
</div>