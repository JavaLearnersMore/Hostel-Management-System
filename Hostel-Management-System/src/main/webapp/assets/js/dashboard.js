// Animated counters
document.querySelectorAll(".counter").forEach(counter => {
    let target = +counter.dataset.count;
    let count = 0;
    let step = target / 40;

    let interval = setInterval(() => {
        count += step;
        counter.innerText = Math.floor(count);
        if (count >= target) {
            counter.innerText = target;
            clearInterval(interval);
        }
    }, 40);
});

// Chart
new Chart(document.getElementById("adminChart"), {
    type: "bar",
    data: {
        labels: ["Students", "Rooms", "Fees", "Visitors"],
        datasets: [{
            data: Array.from(document.querySelectorAll(".counter"))
                .map(c => c.dataset.count)
        }]
    }
});
