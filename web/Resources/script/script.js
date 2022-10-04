$(document).ready(function () {

    $('#data-table tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }

    });
    $('#myModal').on('hidden.bs.modal', function () {
        $('#deleteBtn').hide();
        $('#hashBtn').hide();
    });
    

    $('#hashBtn').on("click", function () {
        $('#myModal').modal('hide');
        generateQRCode($('#hash').val());
    });
    
    $('#form-1').attr('action', current);
    $(".topic").append(topic);

    $('#btn-1').on('click', function () {
        $('#id').val("0");
        $('#name').val("");
        $('#roll').val("");
    });


});
