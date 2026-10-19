<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>Admin Dashboard</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" rel="stylesheet">
<style>
body{
    background:#f4f6f9;
    font-family:'Segoe UI',sans-serif;
}

/* Sidebar spacing */
.main{
    margin-left:240px;
    padding:30px;
}

/* Cards */
.dashboard-card{
    border-radius:15px;
    color:white;
    padding:20px;
    box-shadow:0 6px 15px rgba(0,0,0,0.1);
    transition:0.3s;
}
.dashboard-card:hover{
    transform:translateY(-5px);
}

.card-blue{ background: linear-gradient(45deg,#3a7bd5,#00d2ff); }
.card-green{ background: linear-gradient(45deg,#11998e,#38ef7d); }
.card-orange{ background: linear-gradient(45deg,#f7971e,#ffd200); }
.card-pink{ background: linear-gradient(45deg,#ee0979,#ff6a00); }

.card-title{ font-size:14px; opacity:0.9; }
.card-value{ font-size:28px; font-weight:bold; }
</style>

<style>
.chart-box {
    background: white;
    padding: 20px;
    border-radius: 12px;
    box-shadow: 0 5px 15px rgba(0,0,0,0.1);
    max-width: 600px;

    margin-left: 20px;   /* 🔥 move slightly right */
    margin: 20px auto;
    max-width: 600px;
}

/* 🔥 CONTROL HEIGHT HERE */
.chart-wrapper{
    height:250px;
}
</style>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

</head>
<body>
<!-- NAVBAR -->
<jsp:include page="../common/navbar.jsp"/>
<!-- SIDEBAR -->
<jsp:include page="../admin/sidebar.jsp"/>
<div class="main">
    <h4 class="mb-4">Welcome, ${sessionScope.loggedInUser}</h4>
    <!-- CARDS -->
    <div class="row g-4 mb-4">
        <div class="col-md-3">
            <div class="dashboard-card card-blue text-center">
                <div class="card-title">Total Students</div>
                <div class="card-value">${totalStudents}</div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="dashboard-card card-green text-center">
                <div class="card-title">Total Rooms</div>
                <div class="card-value">${totalRooms}</div>
            </div>
        </div>

        <div class="col-md-3">
            <div class="dashboard-card card-orange text-center">
                <div class="card-title">Total Fees</div>
                <div class="card-value">₹ ${totalFees}</div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="dashboard-card card-pink text-center">
                <div class="card-title">Visitors</div>
                <div class="card-value">${totalVisitors}</div>
            </div>
        </div>
    </div>
    <!-- CHART -->
   <div class="chart-box">
    <h5>Hostel Overview</h5>
    <div class="chart-wrapper">
        <canvas id="chart"></canvas>
    </div>
</div>
</div>
<script>
new Chart(document.getElementById("chart"), {
    type: 'bar',
    data: {
        labels: ['Students','Rooms','Visitors'],
        datasets: [{
            data: [${totalStudents}, ${totalRooms}, ${totalVisitors}],
            backgroundColor: ['#3a7bd5','#11998e','#ee0979']
        }]
    },
    options: {
        responsive: true,
        maintainAspectRatio: false, // 🔥 IMPORTANT
        plugins:{legend:{display:false}},
        scales:{y:{beginAtZero:true}}
    }
});
</script>

<jsp:include page="../common/footer.jsp"/>
</body>
</html>