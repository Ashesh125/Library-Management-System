<%-- 
    Document   : Dashboard_Main
    Created on : Mar 4, 2022, 10:13:03 AM
    Author     : WorldEdit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="col-lg-8 col-md-6 mt-5 d-flex flex-column container-fluid main-body">
    <div class="col-12"></div>
    <div class="row">
        <div class="col-xl-4 col-md-12 mb-4 dashboard-card goto-books">
            <div class="card border-start border-bottom-0 border-top-0 border-end-0 border-5 border-primary  shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                Books (All)</div>
                            <div class="h5 mb-0 font-weight-bold text-gray-800" id="total_books"></div>
                        </div>
                        <div class="col-auto">
                            <i class="fas fa-qrcode fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xl-4 col-md-12 mb-4 dashboard-card">
            <div class="card border-start border-bottom-0 border-top-0 border-end-0 border-5 border-primary shadow h-100 py-2 goto-books" >
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                Books (Unique)</div>
                            <div class="h5 mb-0 font-weight-bold text-gray-800" id="unique_books"></div>
                        </div>
                        <div class="col-auto">
                            <i class="fa-solid fa-book-open fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-xl-4 col-md-12 mb-4 dashboard-card" id="goto-patrons">
            <div class="card border-start border-bottom-0 border-top-0 border-end-0 border-5 border-primary  shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                Patrons</div>
                            <div class="h5 mb-0 font-weight-bold text-gray-800" id="total_patrons"></div>
                        </div>
                        <div class="col-auto">
                            <i class="far fa-id-card fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-xl-4 col-md-12 mb-4 dashboard-card" id="goto-borrowers">
            <div class="card border-start border-bottom-0 border-top-0 border-end-0 border-5 border-primary shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                Borrowers</div>
                            <div class="h5 mb-0 font-weight-bold text-gray-800" id="total_borrowers"></div>
                        </div>
                        <div class="col-auto">
                            <i class="fas fa-address-book fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>



        <div class="col-xl-4 col-md-12 mb-4 dashboard-card" id="goto-issued">
            <div class="card border-start border-bottom-0 border-top-0 border-end-0 border-5 border-primary  shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                Issued</div>
                            <div class="h5 mb-0 font-weight-bold text-gray-800" id="total_issued"></div>
                        </div>
                        <div class="col-auto">
                            <i class="fas fa-calendar fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xl-4 col-md-12 mb-4 dashboard-card" id="goto-blacklist">
            <div class="card border-start border-bottom-0 border-top-0 border-end-0 border-5 border-primary  shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-danger text-uppercase mb-1">
                                BlackList</div>
                            <div class="h5 mb-0 font-weight-bold text-gray-800" id="total_blacklist"></div>
                        </div>
                        <div class="col-auto">
                            <i class="fas fa-ban fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <div class="row p-2 d-flex justify-content-evenly">
        <div class="card shadow col-lx-5 col-md-5 m-2 pb-2">
            <canvas id="bookChart"  style="background-color:#ffffff;"></canvas>
        </div>
        <div class="card shadow col-lx-5 col-md-5 m-2 pb-2" >
            <canvas id="patronChart"  style="background-color:#ffffff;"></canvas>
        </div>
    </div>
    <div class="row p-2 d-flex justify-content-evenly">
        <div class="card shadow col-lx-5 col-md-10 m-2 pb-2">
            <canvas id="activityChart"  style="background-color:#ffffff;"></canvas>
        </div>
    </div>
</div>
<div></div>
<script>
    $(document).ready(function () {
        $('#home').addClass("active");
        $.ajax({
            type: "GET",
            url: "<%=application.getContextPath()%>/Forms/DashboardMain",
            data: "datas",
            success: function (response) {
                var json_obj = $.parseJSON(response);
                appendDashboardMain(json_obj["datas"][0]);
                generateBookChart(getBookData(json_obj["datas"][0]));
                generatePatronChart(getPatronData(json_obj["datas"][0]));
                generateBarGraph(json_obj["datas"][1], json_obj["datas"][2]);
            },
            dataType: "html"
        });
    });
    function generateBookChart(values) {

        let data = {
            labels: [
                'Available',
                'Issued'
            ],
            datasets: [{
                    label: 'Books',
                    backgroundColor: [
                        'rgb(54, 162, 235)',
                        'rgb(255, 99, 132)',
                    ],
                    hoverOffset: 4,
                    data: values,
                }]
        };
        const myChart = new Chart(
                document.getElementById('bookChart'),
                {
                    type: 'doughnut',
                    data: data,
                    options: {plugins: {
                            title: {
                                display: true,
                                text: 'Books'
                            }
                        }}
                }
        );
    }

    function generatePatronChart(values) {


        let data = {
            labels: [
                'Non-Borrowers',
                'Borrowers',
                'BlackList'
            ],
            datasets: [{
                    label: 'Books',
                    backgroundColor: [
                        'rgb(69, 229, 33)',
                        'rgb(54, 162, 235)',
                        'rgb(255, 99, 132)'
                    ],
                    hoverOffset: 4,
                    data: values,
                }]
        };
        const myChart = new Chart(
                document.getElementById('patronChart'),
                {
                    type: 'pie',
                    data: data,
                    options: {plugins: {
                            title: {
                                display: true,
                                text: 'Patrons',
                            }
                        }}
                }
        );
    }

    function generateBarGraph(values1, values2) {
        let labels = [];
        values1.forEach(element => labels.push(element.x));
        values2.forEach(element => labels.push(element.x));
        labels.sort();
        labels = [...new Set(labels)];
        console.log(labels);


        let data = {
            datasets: [{
                    label: 'Issued',
                    data: values1,
                    fill: false,
                    borderColor: 'rgb(75, 192, 192)',
                    tension: 0.1
                }, {
                    label: 'Returned',
                    data: values2,
                    fill: false,
                    borderColor: 'rgb(255, 99, 132)',
                    tension: 0.1
                }],
            labels: labels
        };
        let myChart = new Chart(
                document.getElementById('activityChart'),
                {
                    type: 'line',
                    data: data,
                    options: {
                        scales: {
                            y: {
                                beginAtZero: true
                            }
                        }, plugins: {
                            title: {
                                display: true,
                                text: 'Activity',
                            }
                        }}
                }
        );
    }

    function appendDashboardMain(data) {
        $('#total_books').append(data.booksTotal);
        $('#unique_books').append(data.booksUnique);
        $('#total_patrons').append(data.patrons);
        $('#total_borrowers').append(data.borrowers);
        $('#total_issued').append(data.issued);
        $('#total_blacklist').append(data.blacklist);
        $('.goto-books').on("click", function () {
            callRelocate("Resources/Pages/Book_List.jsp");
        });
        $('#goto-patrons').on("click", function () {
            callRelocate("Resources/Pages/Patron_List.jsp");
        });
        $('#goto-borrowers').on("click", function () {
            callRelocate("Resources/Pages/Patron_Search.jsp?type=b");
        });
        $('#goto-issued').on("click", function () {
            callRelocate("Resources/Pages/Patron_Search.jsp?type=b");
        });
        $('#goto-blacklist').on("click", function () {
            callRelocate("Resources/Pages/Patron_Search.jsp?type=f");
        });
        $('#goto-scan').on("click", function () {
            callRelocate("Resources/Pages/QRScanner.jsp");
        });
    }

    function callRelocate(loc) {
        let url = "<%=application.getContextPath()%>/" + loc;
        location.href = url;
    }

    function getBookData(data) {
        let temp = [data.booksTotal - data.issued, parseInt(data.issued)];
        return temp;
    }
    function getPatronData(data) {
        let temp = [data.patrons - data.borrowers - data.blacklist, parseInt(data.borrowers), parseInt(data.blacklist)];
        return temp;
    }
</script>
</body>
</html>
