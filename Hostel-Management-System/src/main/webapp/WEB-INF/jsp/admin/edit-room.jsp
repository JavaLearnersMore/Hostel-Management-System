<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Edit Room</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <h2>Edit Room</h2>

    <form action="${pageContext.request.contextPath}/admin/rooms/update" method="post">

        <input type="hidden" name="id" value="${room.id}" />

        <div class="mb-3">
            <label>Room Number</label>
            <input type="text" name="roomNumber" value="${room.roomNumber}" class="form-control">
        </div>

        <div class="mb-3">
            <label>Room Type</label>
            <input type="text" name="roomType" value="${room.roomType}" class="form-control">
        </div>

        <div class="mb-3">
            <label>Capacity</label>
            <input type="number" name="capacity" value="${room.capacity}" class="form-control">
        </div>

        <div class="mb-3">
            <label>Occupied</label>
            <input type="number" name="occupied" value="${room.occupied}" class="form-control">
        </div>

        <div class="mb-3">
            <label>Status</label>
            <select name="status" class="form-control">
                <option value="AVAILABLE" <c:if test="${room.status == 'AVAILABLE'}">selected</c:if>>Available</option>
                <option value="FULL" <c:if test="${room.status == 'FULL'}">selected</c:if>>Full</option>
            </select>
        </div>

        <button class="btn btn-success">Update</button>
        <a href="${pageContext.request.contextPath}/admin/rooms" class="btn btn-secondary">Cancel</a>

    </form>

</div>

</body>
</html>