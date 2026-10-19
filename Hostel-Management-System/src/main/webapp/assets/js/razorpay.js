function payFee(studentId) {

    var options = {
        "key": "rzp_test_yourKeyHere",
        "amount": "200000", // 2000 INR
        "currency": "INR",
        "name": "Hostel Management System",
        "description": "Hostel Fee Payment",
        "handler": function (response) {

            // Send payment details to backend
            fetch("/payment/verify", {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                },
                body:
                    "studentId=" + studentId +
                    "&razorpay_order_id=" + response.razorpay_order_id +
                    "&razorpay_payment_id=" + response.razorpay_payment_id +
                    "&amount=2000"
            })
            .then(res => res.text())
            .then(msg => alert(msg));
        }
    };

    var rzp = new Razorpay(options);
    rzp.open();
}
