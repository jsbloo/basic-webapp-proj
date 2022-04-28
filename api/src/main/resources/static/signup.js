const apiPath = "/"


// POST REQUESTS  
const createUser = () => {
    if (document.getElementById("txtUsername").value.trim() == "") {
        alert("Please enter username!");
        return false;
    }
    if (document.getElementById("txtPassword").value.trim() == "") {
        alert("Please enter password!");
        return false;
    }
    axios.post(apiPath + "user/create/", {
            "username": document.getElementById("txtUsername").value,
            "password": document.getElementById("txtPassword").value,
            "active": 1,
            "roles":"USER"
        })
        .catch(err => console.error(err));
        window.location.replace(apiPath+"factoid-page.html");
};

document.getElementById("signBtn").addEventListener("click",createUser);