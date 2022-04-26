const apiPath = "http://localhost:8080/"

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


const appendFactoid = async () => {
    let id = document.getElementById("factoidId").value
    let factoid = await getFactoidByIdProm(id);
    let div = document.createElement('div');
    console.log(factoid);
    div.setAttribute('class', 'factoid block bc2')
    div.innerHTML = `<div class="card">
                        <div class="card-header">${id}</div>
                        <div class="card-body">
                            <h5 class="card-title">${factoid.content}</h5>
                            <p class="card-text">${factoid.explanation}</p>
                            <p class="card-text">${factoid.axiom}</p>
                        </div>
                    </div>`
    document.getElementById('factoids').appendChild(div);
    // (factoid => showOutput(factoid));
}

//GET REQUESTS
const getFactoidByIdProm = (id) => {
    return axios.get(apiPath + "factoid/getById/" + id, {})
    .then(factoid => factoid.data).catch(err => console.error(err));
};

const showOutput = (factoid) => {
    document.getElementById('factoid').innerHTML = factoid.data.content;
};

//TODO:close button resets form, lookup security risk of form input
const explElement = document.getElementById("createFactoidExplanation");

document.getElementById("createFactoid").addEventListener("click", createFactoid);
document.getElementById("submitBtn").addEventListener("click", appendFactoid);