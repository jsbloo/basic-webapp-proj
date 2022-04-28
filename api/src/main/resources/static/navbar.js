const logout = () => {
    window.location.replace("/logout");
}

document.getElementById("logoutBtn").addEventListener("click",logout);