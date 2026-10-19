<%@ page contentType="text/html;charset=UTF-8" %>

<div class="sidebar">

    <h4><i class="fas fa-user-shield"></i> Admin</h4>

    <a href="${pageContext.request.contextPath}/admin/dashboard">
        <i class="fas fa-chart-line me-2"></i> Dashboard
    </a>

    <a href="${pageContext.request.contextPath}/admin/students">
        <i class="fas fa-user-graduate me-2"></i> Students
    </a>

    <a href="${pageContext.request.contextPath}/admin/rooms">
        <i class="fas fa-bed me-2"></i> Rooms
    </a>

    <a href="${pageContext.request.contextPath}/admin/attendance">
        <i class="fas fa-calendar-check me-2"></i> Attendance
    </a>

    <a href="${pageContext.request.contextPath}/admin/fees">
        <i class="fas fa-money-bill-wave me-2"></i> Fees
    </a>

    <a href="${pageContext.request.contextPath}/admin/visitors">
        <i class="fas fa-users me-2"></i> Visitors
    </a>
    
    <a href="${pageContext.request.contextPath}/admin/leaves">
    <i class="fas fa-file-alt me-2"></i> Leaves
</a>

</div>

<style>
.sidebar {
    position: fixed;
    top: 60px;
    left: 0;
    width: 230px;
    height: 100%;
    background: #0b1a33;
    color: white;
    padding-top: 20px;
    z-index: 1000;
}

.sidebar h4 {
    text-align: center;
    margin-bottom: 20px;
}

.sidebar a {
    display: block;
    color: #cfd8dc;
    padding: 12px 20px;
    text-decoration: none;
    transition: 0.3s;
}

.sidebar a:hover {
    background: #1f3c73;
    color: white;
}
</style>