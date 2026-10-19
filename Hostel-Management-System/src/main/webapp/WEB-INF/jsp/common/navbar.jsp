<nav class="navbar navbar-dark bg-dark fixed-top shadow-sm px-3">

    <a class="navbar-brand text-white" href="#">
        <i class="fas fa-hotel"></i> HostelManagementSystem
    </a>

    <div class="ms-auto text-white">
        <i class="fas fa-user-circle"></i>
        ${sessionScope.loggedInUser}

        <a href="${pageContext.request.contextPath}/logout"
           class="btn btn-warning btn-sm ms-3">
            Logout
        </a>
    </div>

</nav>

<style>
.navbar{
    height:60px;
    z-index:1000;
}
</style>