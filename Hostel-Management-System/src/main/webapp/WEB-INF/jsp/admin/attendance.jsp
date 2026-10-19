<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Attendance</title>
      <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin.css">
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
</head>
<body>

<!-- NAVBAR -->
<jsp:include page="../common/navbar.jsp"/>

<!-- SIDEBAR -->
<jsp:include page="../admin/sidebar.jsp" />

<!-- MAIN CONTENT -->
<div class="main-content">
    <div class="page-header">
        <h2>Attendance</h2>
        <a href="${pageContext.request.contextPath}/admin/dashboard"
           class="btn-back">
            ← Back to Dashboard
        </a>
    </div>
    <div class="card-table">
        <table class="data-table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>ATTEND DATE</th>
                   <th>STATUS</th>
                    <th style="text-align:center;">Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${attendance}" var="s">
                    <tr>
                        <td>${s.id}</td>
                         <td>${s.attendDate}</td>
                          <td>${s.status}</td>
                        <td class="actions">
                            <!-- UPDATE -->
                            <a href="${pageContext.request.contextPath}/admin/attendance/edit/${s.id}"
                               class="btn-edit">
                                Edit
                            </a>
                            <!-- DELETE -->
                            <a href="${pageContext.request.contextPath}/admin/attendance/delete/${s.id}"
                               class="btn-delete"
                               onclick="return confirm('Are you sure you want to delete this visitors?');">
                                Delete
                            </a>
                        </td>
                    </tr>
                </c:forEach>

                <c:if test="${empty attendance}">
                    <tr>
                        <td colspan="6" class="no-data">
                            No attendance found
                        </td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </div>

</div>

<jsp:include page="../common/footer.jsp"/>
</body>
</html>