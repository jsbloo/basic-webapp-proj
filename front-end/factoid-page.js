const apiPath = "http://localhost:8080/"

let currentFactoid = [];

// POST REQUESTS  
const createFactoid = () => {
    axios.post(apiPath + "factoid/create/", {
            "content": document.getElementById("createFactoidContent").value,
            "axiom": $("#createAxiomSelect option:selected").text() === 'True',
            "explanation": document.getElementById("createFactoidExplanation").value
        })
        .then(message => document.getElementById("createFactoidMessage").innerHTML = (() => {
            return (message.status == "201" ? "Success!" : "Failure: " + message.status);
        })())
        .catch(err => console.error(err));
};

const explElement = document.getElementById("createFactoidExplanation");
document.getElementById("createFactoid").addEventListener("click", createFactoid);