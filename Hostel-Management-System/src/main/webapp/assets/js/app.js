// Dark Mode Toggle
function toggleDarkMode() {
    document.body.classList.toggle("dark");
}

// Admin Chart
function loadAdminChart() {
    const ctx = document.getElementById("adminChart");
    if (!ctx) return;

    new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ['Students', 'Rooms', 'Fees Paid', 'Visitors'],
            datasets: [{
                label: 'Hostel Stats',
                data: [120, 80, 95, 40]
            }]
        }
    });
}
