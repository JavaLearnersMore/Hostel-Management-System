document.addEventListener("DOMContentLoaded", function () {

    const form = document.getElementById("loginForm");
    const btn = document.getElementById("loginBtn");
    const errorBox = document.getElementById("errorMsg");

    if (!form || !btn) return;

    form.addEventListener("submit", function (e) {

        const username = document.getElementById("username").value.trim();
        const password = document.getElementById("password").value.trim();

        // Clear previous error
        if (errorBox) errorBox.innerText = "";

        // Validation
        if (username === "" || password === "") {
            e.preventDefault();
            showError("Username and Password are required");
            return;
        }

        // Disable button & show loading
        btn.disabled = true;
        btn.innerHTML = `<span class="spinner"></span> Logging in...`;
        btn.classList.add("loading");

    });

    function showError(msg) {
        if (errorBox) {
            errorBox.innerText = msg;
            errorBox.style.display = "block";
        }
    }
});
