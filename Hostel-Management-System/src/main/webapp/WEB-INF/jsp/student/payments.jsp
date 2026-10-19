<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student Dashboard - Payment</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" rel="stylesheet">

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
            border-bottom: 1px solid rgba(255,255,255,0.1);
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

        /* Payment Card */
        .payment-card {
            background: white;
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 6px 20px rgba(0,0,0,0.1);
            max-width: 500px;
            margin: 50px auto;
        }

        .payment-card h3 {
            margin-bottom: 20px;
            color: #0b1a33;
            text-align: center;
        }

        .amount-display {
            background: linear-gradient(45deg,#11998e,#38ef7d);
            color: white;
            font-weight: bold;
            font-size: 1.5rem;
            padding: 15px;
            border-radius: 10px;
            text-align: center;
            margin-bottom: 25px;
            box-shadow: 0 4px 10px rgba(0,0,0,0.1);
        }

        .btn-pay {
            background: linear-gradient(45deg,#3a7bd5,#00d2ff);
            color: white;
            border: none;
        }

        .btn-pay:hover {
            background: linear-gradient(45deg,#00d2ff,#3a7bd5);
            color: white;
        }

        .form-label {
            font-weight: 600;
        }
        
        
      
    </style>
</head>


<script>
setTimeout(function(){
    let msg = document.querySelector(".alert");
    if(msg){
        msg.style.display = "none";
    }
}, 30000);
</script>
<body>

<!-- Sidebar -->
<div class="sidebar">
    <h4><i class="fas fa-hotel"></i> HostelMS</h4>
    <a href="${pageContext.request.contextPath}/student/rooms"><i class="fas fa-bed me-2"></i> Available Rooms</a>
    <a href="${pageContext.request.contextPath}/student/my-room"><i class="fas fa-door-open me-2"></i> My Room</a>
    <a href="${pageContext.request.contextPath}/student/payments"><i class="fas fa-money-bill-wave me-2"></i> Pay Fees</a>
    <a href="${pageContext.request.contextPath}/student/attendance"><i class="fas fa-calendar-check me-2"></i> Attendance</a>
    <a href="${pageContext.request.contextPath}/student/leave"><i class="fas fa-file-alt me-2"></i> Apply Leave</a>
    <a href="${pageContext.request.contextPath}/student/profile"><i class="fas fa-user me-2"></i> Profile</a>
    <a href="${pageContext.request.contextPath}/logout"><i class="fas fa-sign-out-alt me-2"></i> Logout</a>
</div>

<!-- Main Content -->
<div class="main">
    <div class="topbar mb-4">
        <h4 class="mb-0">Welcome, ${sessionScope.loggedInUser}</h4>
    </div>
    <div class="payment-card">
        <h3><i class="fas fa-money-bill-wave me-2"></i>Pay Hostel Fees</h3>

        <div class="amount-display">
            Pending Amount: ₹ ${due}
        </div>
        <form action="${pageContext.request.contextPath}/student/pay" method="post">
            <div class="mb-3">
                <label for="amount" class="form-label">Enter Amount</label>
                <input type="number" name="amount" id="amount" class="form-control" placeholder="₹ 0.00" required>
            </div>

            <div class="d-grid">
                <button type="submit" class="btn btn-pay btn-lg"><i class="fas fa-credit-card me-2"></i>Pay Now</button>
            </div>
            
   
  <c:if test="${param.success == 'true'}">
    <div class="alert alert-success text-center">
        ✅ Payment Successfully!
    </div>
</c:if>
<c:if test="${param.success == 'false'}">
    <div class="alert alert-success text-center">
        ✅ Payment Fee should not greater than Room Fee. Please check pending fee and pay again..!
    </div>
</c:if>
        </form>
    </div>

</div>

</body>
</html>