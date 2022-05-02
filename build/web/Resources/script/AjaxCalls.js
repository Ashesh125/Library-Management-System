/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function getPublisher(){
    $.ajax({
            type: "GET",
            url: "<%=application.getContextPath()%>/Forms/Publisher",
            data: "datas",
            success: function (response) {
                var json_obj = $.parseJSON(response);
                appendSelect(json_obj);
            },
            dataType: "html"
        });
    
    
}