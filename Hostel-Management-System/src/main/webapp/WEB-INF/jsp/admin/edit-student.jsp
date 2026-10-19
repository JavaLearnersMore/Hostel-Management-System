<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Student</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <h2>Edit Student</h2>

    <form action="${pageContext.request.contextPath}/admin/students/update" method="post">

        <input type="hidden" name="id" value="${student.id}" />

        <div class="mb-3">
            <label>Name</label>
            <input type="text" name="name" value="${student.name}" class="form-control">
        </div>

        <div class="mb-3">
            <label>Username</label>
            <input type="text" name="username" value="${student.username}" class="form-control">
        </div>

        <div class="mb-3">
            <label>Email</label>
            <input type="email" name="email" value="${student.email}" class="form-control">
        </div>

        <div class="mb-3">
            <label>Mobile</label>
            <input type="text" name="mobileNumber" value="${student.mobileNumber}" class="form-control">
        </div>

        <button class="btn btn-success">Update</button>
        <a href="${pageContext.request.contextPath}/admin/students" class="btn btn-secondary">Cancel</a>

    </form>

</div>

</body>
</html>