document.addEventListener("DOMContentLoaded", function () {

    if (typeof totalStudents === "undefined") return;

    const ctx = document.getElementById("studentChart");
    if (!ctx) return;

    new Chart(ctx, {
        type: "bar",
        data: {
            labels: ["Students", "Rooms", "Visitors"],
            datasets: [{
                data: [totalStudents, totalRooms, totalVisitors],
                backgroundColor: ["#2563eb", "#16a34a", "#db2777"]
            }]
        },
        options: {
            responsive: true,
            scales: { y: { beginAtZero: true } }
        }
    });
});
