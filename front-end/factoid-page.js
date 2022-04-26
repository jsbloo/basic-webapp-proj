const apiPath = "http://localhost:8080/"

//TODO: put all rest functions in their own file
//TODO: added error message when finding ID that doesnt exist

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

// PUT REQUESTS  
const updateFactoid = () => {
    let id = document.getElementById("factoidUpdateId").value; 
    console.log(id);
    axios.put(apiPath + "factoid/update/"+id, {
            "content": document.getElementById("updateFactoidContent").value,
            "axiom": $("#updateAxiomSelect option:selected").text() === 'True',
            "explanation": document.getElementById("updateFactoidExplanation").value
        })
        .then(message => document.getElementById("updateFactoidMessage").innerHTML = (() => {
            return (message.status == "200" ? "Success!" : "Failure: " + message.status);
        })())
        .catch(err => console.error(err));
};

// DELETE REQUESTS  
const deleteFactoid = () => {
    let id = document.getElementById("factoidDeleteId").value; 
    console.log(id);
    axios.delete(apiPath + "factoid/delete/"+id)
        .then(message => document.getElementById("deleteFactoidMessage").innerHTML = (() => {
            return (message.status == "204" ? "Success!" : "Failure: " + message.status);
        })())
        .catch(err => console.error(err));
};

//GET REQUESTS
const getFactoidByIdProm = (id) => {
    return axios.get(apiPath + "factoid/getById/" + id, {})
    .then(factoid => factoid.data).catch(err => console.error(err));
};

const getAllFactoid = () => {
    return axios.get(apiPath + "factoid/getAll", {})
    .then(factoid => factoid.data).catch(err => console.log(err));
};


const appendFactoid = async () => {
    let id = document.getElementById("factoidId").value
    let factoid = await getFactoidByIdProm(id);
    let div = document.createElement('div');
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

const appendFactoids = async (factoid) => {
    let div = document.createElement('div');
    div.setAttribute('class', 'factoid block bc2')
    div.innerHTML = `<div class="card">
                        <div class="card-header">${factoid.id}</div>
                        <div class="card-body">
                            <h5 class="card-title">${factoid.content}</h5>
                            <p class="card-text">${factoid.explanation}</p>
                            <p class="card-text">${factoid.axiom}</p>
                        </div>
                    </div>`
    document.getElementById('factoids').appendChild(div);
}

const showAll = async () => {
    const factoidList = await getAllFactoid();

    factoidList.forEach(element => {
        appendFactoids(element);
    });
}

// const showOutput = (factoid) => {
//     console.log(factoid)
//     document.getElementById('factoid').innerHTML = factoid.data.content;
// };

//TODO:close button resets form, lookup security risk of form input
const explElement = document.getElementById("createFactoidExplanation");

document.getElementById("createFactoid").addEventListener("click", createFactoid);
document.getElementById("updateFactoid").addEventListener("click", updateFactoid);
document.getElementById("deleteFactoid").addEventListener("click", deleteFactoid);
document.getElementById("getAllBtn").addEventListener("click", showAll);
document.getElementById("submitBtn").addEventListener("click", appendFactoid);