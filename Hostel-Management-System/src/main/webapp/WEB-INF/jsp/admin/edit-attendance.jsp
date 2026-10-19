<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Attendance</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <h2>Edit Attendance</h2>

    <form action="${pageContext.request.contextPath}/admin/attendance/update" method="post">

        <!-- Hidden ID -->
        <input type="hidden" name="id" value="${attendance.id}" />

        <!-- Date -->
        <div class="mb-3">
            <label>Date</label>
            <input type="date" name="attendDate"
                   value="${attendance.attendDate}" class="form-control" required />
        </div>

        <!-- Status -->
        <div class="mb-3">
            <label>Status</label>
            <select name="status" class="form-control">
                <option value="PRESENT" ${attendance.status == 'PRESENT' ? 'selected' : ''}>Present</option>
                <option value="ABSENT" ${attendance.status == 'ABSENT' ? 'selected' : ''}>Absent</option>
            </select>
        </div>

        <button type="submit" class="btn btn-success">Update</button>

        <a href="${pageContext.request.contextPath}/admin/attendance"
           class="btn btn-secondary">Cancel</a>

    </form>
</div>

</body>
</html>