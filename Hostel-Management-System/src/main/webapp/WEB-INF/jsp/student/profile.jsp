<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>Student Dashboard - Profile</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" rel="stylesheet">

<style>
body {
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

.topbar{
    background:white;
    padding:15px 25px;
    border-radius:10px;
    box-shadow:0 2px 8px rgba(0,0,0,0.05);
    margin-bottom:25px;
}

/* Profile Card */
.card-profile{
    background:white;
    padding:25px;
    border-radius:15px;
    box-shadow:0 6px 20px rgba(0,0,0,0.1);
    max-width:700px;
    margin:auto;
    text-align:center;
}

/* Profile Image */
.profile-pic{
    width:120px;
    height:120px;
    object-fit:cover;
    border-radius:50%;
    border:3px solid #3a7bd5;
    display:block;
    margin:auto;
}

/* Upload Button */
.btn-upload{
    background: linear-gradient(45deg,#3a7bd5,#00d2ff);
    color:white;
    border:none;
}

.btn-upload:hover{
    background: linear-gradient(45deg,#00d2ff,#3a7bd5);
    color:white;
}

/* Table */
.table-profile th{
    width:30%;
    background:#f1f8ff;
    font-weight:600;
}
</style>
</head>
<body>

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
    <!-- Topbar -->
    <div class="topbar">
        <h4 class="mb-0">Welcome, ${sessionScope.loggedInUser}</h4>
    </div>
    <!-- Profile Card -->
    <div class="card-profile">
        <!-- Profile Image -->
        <c:choose>
            <c:when test="${not empty student.profilePic}">
                <img src="${pageContext.request.contextPath}${student.profilePic}" class="profile-pic">
            </c:when>
            <c:otherwise>
                <img src="${pageContext.request.contextPath}/assets/images/default-profile.png" class="profile-pic">
            </c:otherwise>
        </c:choose>
        <!-- Upload Form -->
        <form action="${pageContext.request.contextPath}/student/profile/upload" 
              method="post" enctype="multipart/form-data" class="mt-3">
            <input type="file" name="profilePic" accept="image/*" required>
            <button type="submit" class="btn btn-upload mt-2">
                <i class="fas fa-upload me-1"></i> Upload
            </button>
        </form>
        <!-- Name -->
        <h3 class="mt-3">${student.name}</h3>
        <!-- Profile Table -->
        <table class="table table-bordered table-profile mt-3">
            <tr>
                <th>Student ID</th>
                <td>${student.id}</td>
            </tr>
            <tr>
                <th>Email</th>
                <td>${student.email}</td>
            </tr>
            <tr>
                <th>Mobile</th>
                <td>${student.mobileNumber}</td>
            </tr>

            <tr>
                <th>Room Number</th>
                <td>
                    <c:choose>
                        <c:when test="${student.room != null}">
                            ${student.room.roomNumber}
                        </c:when>
                        <c:otherwise>
                            Not Assigned
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>

            <tr>
                <th>Total Fee</th>
                <td>${student.totalFee}</td>
            </tr>
        </table>

    </div>

</div>

</body>
</html>