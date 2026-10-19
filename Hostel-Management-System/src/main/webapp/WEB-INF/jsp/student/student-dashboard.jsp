<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<title>Student Dashboard</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" rel="stylesheet">

<style>

body{
    background:#f4f6f9;
    font-family: 'Segoe UI', sans-serif;
}

/* Sidebar */
.sidebar{
    height:100vh;
    background:#0b1a33;
    color:white;
    position:fixed;
    width:240px;
}

.sidebar h4{
    padding:20px;
    text-align:center;
    border-bottom:1px solid rgba(255,255,255,0.1);
}

.sidebar a{
    color:#cfd8dc;
    display:block;
    padding:12px 20px;
    text-decoration:none;
    transition:0.3s;
}

.sidebar a:hover{
    background:#1f3c73;
    color:white;
}

/* Main content */
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
}

.card-blue{ background: linear-gradient(45deg,#3a7bd5,#00d2ff); }
.card-green{ background: linear-gradient(45deg,#11998e,#38ef7d); }
.card-orange{ background: linear-gradient(45deg,#f7971e,#ffd200); }
.card-pink{ background: linear-gradient(45deg,#ee0979,#ff6a00); }

.card-title{
    font-size:14px;
    opacity:0.9;
}

.card-value{
    font-size:28px;
    font-weight:bold;
}

.topbar{
    background:white;
    padding:25px 30px;
    border-radius:10px;
    box-shadow:0 2px 8px rgba(0,0,0,0.05);
    margin-bottom:20px;
}

</style>
</head>
<body>

<!-- NAVBAR -->
<jsp:include page="../common/navbar.jsp"/>

<!-- SIDEBAR -->
<div class="sidebar">
    <h4><i class="fas fa-hotel"></i> HostelMS</h4>

    <a href="${pageContext.request.contextPath}/student/dashboard">
        <i class="fas fa-home me-2"></i> Dashboard
    </a>

    <a href="${pageContext.request.contextPath}/student/rooms">
        <i class="fas fa-bed me-2"></i> Available Rooms
    </a>

    <a href="${pageContext.request.contextPath}/student/my-room">
        <i class="fas fa-door-open me-2"></i> My Room
    </a>

    <a href="${pageContext.request.contextPath}/student/payments">
        <i class="fas fa-money-bill-wave me-2"></i> Pay Fees
    </a>

    <a href="${pageContext.request.contextPath}/student/attendance">
        <i class="fas fa-calendar-check me-2"></i> Attendance
    </a>

    <a href="${pageContext.request.contextPath}/student/leave">
        <i class="fas fa-file-alt me-2"></i> Apply Leave
    </a>

    <a href="${pageContext.request.contextPath}/student/profile">
        <i class="fas fa-user me-2"></i> Profile
    </a>

    <a href="${pageContext.request.contextPath}/logout">
        <i class="fas fa-sign-out-alt me-2"></i> Logout
    </a>
</div>

<!-- Main Content -->
<div class="main">

    <!-- Top -->
    <div class="topbar">
        <h4 class="mb-0">
            Welcome, ${student.name}
        </h4>
    </div>

    <!-- Cards -->
    <div class="row g-4">

        <!-- Room -->
        <div class="col-md-3">
            <div class="dashboard-card card-blue">
                <div class="card-title">Room Number</div>
                <div class="card-value">
                    <c:choose>
                        <c:when test="${student.room != null}">
                            ${student.room.roomNumber}
                        </c:when>
                        <c:otherwise>
                            Not Allotted
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>

        <!-- Attendance -->
        <div class="col-md-3">
            <div class="dashboard-card card-green">
                <div class="card-title">Attendance</div>
                <div class="card-value">
                    ${attendancePercent}%
                </div>
            </div>
        </div>

        <!-- Fees -->
        <div class="col-md-3">
            <div class="dashboard-card card-orange">
                <div class="card-title">Pending Fees</div>
                <div class="card-value">
                    ₹ ${pendingFees}
                </div>
            </div>
        </div>

        <!-- Leaves -->
        <div class="col-md-3">
            <div class="dashboard-card card-pink">
                <div class="card-title">Approved Leaves</div>
                <div class="card-value">
                    ${leaveCount}
                </div>
            </div>
        </div>

    </div>

    <!-- Attendance Box -->
    <div class="mt-5">
        <div class="card shadow-sm border-0 rounded-4">
            <div class="card-body text-center">
                <h5>Attendance Overview</h5>
                <p class="text-muted">
                    Your current attendance is ${attendancePercent}%
                </p>
            </div>
        </div>
    </div>

</div>

</body>
</html>