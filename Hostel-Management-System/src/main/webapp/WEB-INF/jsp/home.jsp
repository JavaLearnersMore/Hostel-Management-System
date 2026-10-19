<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>Home</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
html, body {
    height: 100%;
    }

body {
    display: flex;
    flex-direction: column;
}

.main-content {
    flex: 1;
}

.footer {
    margin-top: auto;
}
</style>
</head>
<body>

<jsp:include page="/WEB-INF/jsp/common/navbar.jsp"/>
<!-- ✅ HERO -->
<div class="container mt-5 text-center">
  <h2 class="mb-3">Welcome to Hostel Management System🌿</h2>
  <p class="text-muted">Maintain Hostel</p>

  <a href="${pageContext.request.contextPath}/dashboard"
     class="btn btn-success m-2">Hostel</a>
</div>
<%@ include file="common/footer.jsp" %>
</body>
</html>
