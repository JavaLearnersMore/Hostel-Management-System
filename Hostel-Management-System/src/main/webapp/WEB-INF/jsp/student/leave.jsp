	<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student Dashboard - Apply Leave</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" rel="stylesheet">

    <style>
        body {
            background: #f4f6f9;
            font-family: 'Segoe UI', sans-serif;
        }

        /* Sidebar */
        .sidebar {
            height: 100vh;
            background: #0b1a33;
            color: white;
            position: fixed;
            width: 240px;
        }

        .sidebar h4 {
            padding: 20px;
            text-align: center;
            border-bottom: 1px solid rgba(255,255,255,0.1);
        }

        .sidebar a {
            color: #cfd8dc;
            display: block;
            padding: 12px 20px;
            text-decoration: none;
            transition: 0.3s;
        }

        .sidebar a:hover {
            background: #1f3c73;
            color: white;
        }

        /* Main content */
        .main {
            margin-left: 240px;
            padding: 30px;
        }

        /* Form card */
        .form-card {
            background: white;
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 6px 20px rgba(0,0,0,0.1);
            max-width: 600px;
            margin: auto;
        }

        .form-card h3 {
            margin-bottom: 25px;
            color: #0b1a33;
        }

        .btn-apply {
            background: linear-gradient(45deg,#3a7bd5,#00d2ff);
            color: white;
            border: none;
        }

        .btn-apply:hover {
            background: linear-gradient(45deg,#00d2ff,#3a7bd5);
            color: white;
        }

        .form-label {
            font-weight: 600;
        }

    </style>
</head>
<body>

<!-- NAVBAR -->
<jsp:include page="../common/navbar.jsp"/>
<!-- SIDEBAR -->
<jsp:include page="../admin/sidebar.jsp"/>

<!-- Sidebar -->
<div class="sidebar">
    <h4><i class="fas fa-hotel"></i> HostelMS</h4>
    <a href="${pageContext.request.contextPath}/student/rooms"><i class="fas fa-bed me-2"></i> Available Rooms</a>
    <a href="${pageContext.request.contextPath}/student/my-room"><i class="fas fa-door-open me-2"></i> My Room</a>
    <a href="${pageContext.request.contextPath}/student/payments"><i class="fas fa-money-bill-wave me-2"></i> Pay Fees</a>
    <a href="${pageContext.request.contextPath}/student/attendance"><i class="fas fa-calendar-check me-2"></i> Attendance</a>
    <a href="${pageContext.request.contextPath}/student/leave"><i class="fas fa-file-alt me-2"></i> Apply Leave</a>
    <a href="${pageContext.request.contextPath}/student/profile"><i class="fas fa-user me-2"></i> Profile</a>
    <a href="${pageContext.request.contextPath}/logout"><i class="fas fa-sign-out-alt me-2"></i> Logout</a>
</div>

<!-- Main Content -->
<div class="main">

    <div class="topbar">
        <h4 class="mb-0">Welcome, ${sessionScope.loggedInUser}</h4>
    </div>

    <div class="form-card">
        <h3 class="text-center"><i class="fas fa-file-alt me-2"></i>Apply for Leave</h3>

        <form action="${pageContext.request.contextPath}/student/leave/apply" method="post">
            <div class="mb-3">
                <label class="form-label" for="fromDate">From Date</label>
                <input type="date" class="form-control" name="fromDate" id="fromDate" required>
            </div>

            <div class="mb-3">
                <label class="form-label" for="toDate">To Date</label>
                <input type="date" class="form-control" name="toDate" id="toDate" required>
            </div>

            <div class="mb-3">
                <label class="form-label" for="reason">Reason</label>
                <textarea name="reason" id="reason" class="form-control" rows="4" placeholder="Enter your reason here..." required></textarea>
            </div>

            <div class="text-center">
                <button type="submit" class="btn btn-apply btn-lg"><i class="fas fa-paper-plane me-2"></i>Apply Leave</button>
            </div>
        </form>
    </div>

</div>

</body>
</html>