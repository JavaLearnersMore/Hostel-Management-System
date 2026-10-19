<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Edit Fee</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <h2>Edit Fee</h2>

    <form action="${pageContext.request.contextPath}/admin/fees/update" method="post">

        <!-- ID -->
        <input type="hidden" name="id" value="${fee.id}" />

        <!-- Student ID -->
        <div class="mb-3">
            <label>Student ID</label>
            <input type="number" name="student_id" value="${fee.student_id}" class="form-control">
        </div>

        <!-- Amount -->
        <div class="mb-3">
            <label>Amount</label>
            <input type="number" name="amount" value="${fee.amount}" class="form-control">
        </div>

        <!-- Status -->
        <div class="mb-3">
            <label>Status</label>
            <select name="status" class="form-control">
                <option value="PAID" <c:if test="${fee.status == 'PAID'}">selected</c:if>>Paid</option>
                <option value="UNPAID" <c:if test="${fee.status == 'UNPAID'}">selected</c:if>>Unpaid</option>
            </select>
        </div>

        <!-- Date -->
        <div class="mb-3">
            <label>Date</label>
            <input type="date" name="pay_date" value="${fee.pay_date}" class="form-control">
        </div>

        <button class="btn btn-success">Update</button>
        <a href="${pageContext.request.contextPath}/admin/fees" class="btn btn-secondary">Cancel</a>

    </form>

</div>

</body>
</html>