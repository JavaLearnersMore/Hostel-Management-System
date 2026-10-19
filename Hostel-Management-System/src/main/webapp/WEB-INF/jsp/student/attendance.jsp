<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Student Attendance</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
	rel="stylesheet">

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
	border-bottom: 1px solid rgba(255, 255, 255, 0.1);
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
	padding: 15px 25px;
	border-radius: 10px;
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
	margin-bottom: 25px;
}

/* Card */
.card-table {
	background: white;
	padding: 25px;
	border-radius: 15px;
	box-shadow: 0 6px 20px rgba(0, 0, 0, 0.1);
}

/* Table */
.data-table th {
	background: #0b1a33;
	color: white;
	font-weight: 600;
}

.data-table tr:hover {
	background: #f1f8ff;
}

.btn-mark {
	background: linear-gradient(45deg, #3a7bd5, #00d2ff);
	color: white;
	border: none;
	padding: 6px 12px;
	border-radius: 6px;
}

.btn-mark:hover {
	background: linear-gradient(45deg, #00d2ff, #3a7bd5);
}

.no-data {
	text-align: center;
	color: #888;
	padding: 20px;
}

select {
	border-radius: 6px;
	padding: 5px;
}
</style>
</head>
<body>

	<!-- Sidebar -->
	<div class="sidebar">
		<h4>
			<i class="fas fa-hotel"></i> HostelMS
		</h4>

		<a href="${pageContext.request.contextPath}/student/rooms"><i
			class="fas fa-bed me-2"></i> Available Rooms</a> <a
			href="${pageContext.request.contextPath}/student/my-room"><i
			class="fas fa-door-open me-2"></i> My Room</a> <a
			href="${pageContext.request.contextPath}/student/payments"><i
			class="fas fa-money-bill-wave me-2"></i> Pay Fees</a> <a
			href="${pageContext.request.contextPath}/student/attendance"><i
			class="fas fa-calendar-check me-2"></i> Attendance</a> <a
			href="${pageContext.request.contextPath}/student/leave"><i
			class="fas fa-file-alt me-2"></i> Apply Leave</a> <a
			href="${pageContext.request.contextPath}/student/profile"><i
			class="fas fa-user me-2"></i> Profile</a> <a
			href="${pageContext.request.contextPath}/logout"><i
			class="fas fa-sign-out-alt me-2"></i> Logout</a>
	</div>

	<!-- Main Content -->
	<div class="main">

		<!-- Topbar -->
		<div class="topbar">
			<h4>Welcome, ${sessionScope.loggedInUser}</h4>
		</div>

		<h2>
			<i class="fas fa-calendar-check me-2"></i>My Attendance
		</h2>

		<div class="card-table">

			<div class="table-responsive">
				<table
					class="table table-bordered table-hover data-table align-middle">
					<thead>
						<tr>
							<th>Student ID</th>
							<th>Student Name</th>
							<th>Date</th>
							<th>Status</th>
							<th class="text-center">Update</th>
						</tr>
					</thead>

					<tbody>
						<form method="post"
							action="${pageContext.request.contextPath}/student/mark">
							<tr>
								<!-- Student ID -->
								<td>${student.id}</td>

								<!-- Student Name -->
								<td>${student.name}</td>

								<!-- Date -->
								<td>${today}</td>

								<!-- Status -->
								<td><input type="hidden" name="studentId"
									value="${student.id}" /> <select name="status"
									class="form-select">

										<option value="PRESENT"
											${attendance != null && attendance.status == 'PRESENT' ? 'selected' : ''}>
											Present</option>

										<option value="ABSENT"
											${attendance != null && attendance.status == 'ABSENT' ? 'selected' : ''}>
											Absent</option>

								</select></td>

								<!-- Button -->
								<td class="text-center"><c:choose>
										<c:when test="${attendance != null}">
											<button class="btn btn-success" disabled>
												<i class="fas fa-check"></i> Already Marked
											</button>
										</c:when>

										<c:otherwise>
											<button type="submit" class="btn-mark">
												<i class="fas fa-check"></i> Mark
											</button>
										</c:otherwise>
									</c:choose></td>

							</tr>
						</form>
					</tbody>
				</table>
			</div>

		</div>

	</div>

</body>
</html>