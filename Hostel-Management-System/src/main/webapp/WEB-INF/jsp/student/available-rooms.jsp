<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student Dashboard - Available Rooms</title>

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

        .topbar {
            background: white;
            padding: 35px 40px;
            border-radius: 10px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.05);
            margin-bottom: 25px;
        }

        h3 {
            margin-bottom: 25px;
            color: #0b1a33;
        }

        /* Room Cards */
        .room-card {
            border-radius: 15px;
            padding: 20px;
            background: white;
            box-shadow: 0 6px 20px rgba(0,0,0,0.1);
            margin-bottom: 20px;
            transition: transform 0.2s;
        }

        .room-card:hover {
            transform: translateY(-5px);
        }

        .room-title {
            font-size: 1.25rem;
            font-weight: 600;
            margin-bottom: 10px;
        }

        .room-info {
            font-size: 0.95rem;
            margin-bottom: 10px;
        }

        .btn-book {
            background: linear-gradient(45deg,#3a7bd5,#00d2ff);
            color: white;
            border: none;
        }

        .btn-book:hover {
            background: linear-gradient(45deg,#00d2ff,#3a7bd5);
            color: white;
        }

        .room-icon {
            font-size: 1.2rem;
            margin-right: 5px;
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

    <h3><i class="fas fa-bed me-2"></i>Available Rooms</h3>

    <div class="row">
        <c:forEach var="room" items="${rooms}">
            <div class="col-md-6 col-lg-4">
                <div class="room-card">
                    <div class="room-title">
                        <i class="fas fa-door-open room-icon"></i> Room ${room.roomNumber}
                    </div>
                    <div class="room-info"><strong>Type:</strong> ${room.roomType}</div>
                    <div class="room-info"><strong>Price/Day:</strong> ₹ ${room.fee}</div>
                    <div class="room-info"><strong>Beds Left:</strong> ${room.capacity - room.occupied}</div>

                    <form method="post" action="${pageContext.request.contextPath}/student/book/${room.id}">
                        <button class="btn btn-book w-100"><i class="fas fa-check me-2"></i>Book Now</button>
                    </form>
                </div>
            </div>
        </c:forEach>
    </div>

</div>

</body>
</html>