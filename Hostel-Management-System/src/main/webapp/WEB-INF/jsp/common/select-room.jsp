<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Select room</title>
</head>
<body>
<h2>Select Room</h2>

<c:forEach var="room" items="${rooms}">
<div>
Room: ${room.roomNumber} |
Fee: ₹${room.fee}

<form action="select-room" method="post">
<input type="hidden" name="roomId" value="${room.id}">
<button>Select</button>
</form>
</div>
</c:forEach>
</body>
</html>