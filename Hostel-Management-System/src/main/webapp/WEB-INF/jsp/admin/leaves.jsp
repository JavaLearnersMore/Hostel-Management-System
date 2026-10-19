<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Leave Management</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    
    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" rel="stylesheet">

    <style>
        body{
            background:#f4f6f9;
            font-family:'Segoe UI',sans-serif;
        }

        /* FIX NAVBAR OVERLAP */
        .content-wrapper{
            margin-top:70px; /* height of navbar */
            margin-left:240px; /* sidebar width */
            padding:25px;
            min-height:calc(100vh - 120px);
        }

        .page-header{
            display:flex;
            justify-content:space-between;
            align-items:center;
            margin-bottom:20px;
        }

        .card-box{
            background:white;
            padding:25px;
            border-radius:15px;
            box-shadow:0 8px 20px rgba(0,0,0,0.08);
        }

        .table th{
            background:#0b1a33;
            color:white;
            text-align:center;
        }

        .table td{
            text-align:center;
            vertical-align:middle;
        }

        .badge-status{
            padding:6px 12px;
            border-radius:20px;
            font-size:12px;
        }

        .approved{ background:#d4edda; color:#155724; }
        .pending{ background:#fff3cd; color:#856404; }
        .rejected{ background:#f8d7da; color:#721c24; }

        .btn-action{
            padding:5px 10px;
            font-size:12px;
            border-radius:6px;
        }

        .btn-approve{ background:#28a745; color:white; }
        .btn-reject{ background:#dc3545; color:white; }

        .btn-back{
            background:#0b1a33;
            color:white;
            padding:8px 15px;
            border-radius:8px;
            text-decoration:none;
        }

        .btn-back:hover{
            background:#162d5c;
        }

        /* FOOTER FIX */
        footer{
            margin-left:240px;
        }
    </style>
</head>

<body>

<!-- NAVBAR -->
<jsp:include page="../common/navbar.jsp"/>

<!-- SIDEBAR -->
<jsp:include page="../admin/sidebar.jsp"/>

<!-- MAIN CONTENT -->
<div class="content-wrapper">

    <div class="page-header">
        <h3><i class="fa fa-calendar-check"></i> Leave Requests</h3>

        <a href="${pageContext.request.contextPath}/admin/dashboard" class="btn-back">
            ← Dashboard
        </a>
    </div>

    <div class="card-box">

        <table class="table table-bordered table-hover">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Student ID</th>
                    <th>Name</th>
                    <th>From</th>
                    <th>To</th>
                    <th>Status</th>
                    <th>Applied</th>
                    <th>Action</th>
                </tr>
            </thead>

            <tbody>

                <c:forEach items="${leaves}" var="l">
                    <tr>
                        <td>${l.id}</td>
                        <td>${l.student.id}</td>
                        <td>${l.student.name}</td>
                        <td>${l.fromDate}</td>
                        <td>${l.toDate}</td>

                        <td>
                            <c:choose>
                                <c:when test="${l.status == 'APPROVED'}">
                                    <span class="badge-status approved">APPROVED</span>
                                </c:when>
                                <c:when test="${l.status == 'PENDING'}">
                                    <span class="badge-status pending">PENDING</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="badge-status rejected">REJECTED</span>
                                </c:otherwise>
                            </c:choose>
                        </td>

                        <!-- ✅ FIXED -->
                        <td>${l.applied_date}</td>

                        <td>
                            <a href="${pageContext.request.contextPath}/admin/leaves/approve/${l.id}"
                               class="btn btn-action btn-approve">
                                <i class="fa fa-check"></i>
                            </a>

                            <a href="${pageContext.request.contextPath}/admin/leaves/reject/${l.id}"
                               class="btn btn-action btn-reject">
                                <i class="fa fa-times"></i>
                            </a>
                        </td>
                    </tr>
                </c:forEach>

                <c:if test="${empty leaves}">
                    <tr>
                        <td colspan="8" class="text-center text-danger">
                            ❌ No Leave Requests Found
                        </td>
                    </tr>
                </c:if>

            </tbody>
        </table>

    </div>

</div>

<!-- FOOTER -->
<jsp:include page="../common/footer.jsp"/>

</body>
</html>