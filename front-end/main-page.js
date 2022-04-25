const apiPath = "http://localhost:8080/"

let currentFactoid = [];

//GET REQUEST
const getFactoidById = () =>{
    axios.get(apiPath + "factoid/getById/5" ,{
    })
    .then(factoid => showOutput(factoid))
    .catch(err => console.error(err));
};

const showOutput = (factoid) => {
    currentFactoid = factoid.data;
    document.getElementById('factoid').innerHTML = factoid.data.content;
};

const isTrue = () => {
    if(currentFactoid.axiom){
        document.getElementById('factoid').innerHTML = "Correct!"
        return
    }
    document.getElementById('factoid').innerHTML = "Wrong ):"
    return
}

const isFalse = () => {
    if(!currentFactoid.axiom){
        document.getElementById('factoid').innerHTML = "Correct!"
        return
    }
    document.getElementById('factoid').innerHTML = "Wrong ):"
    return
}

document.addEventListener("DOMContentLoaded", () => {
    getFactoidById();
  });

document.getElementById('tButton').addEventListener('click', isTrue);
document.getElementById('fButton').addEventListener('click', isFalse);
document.getElementById('nextButton').addEventListener('click', getFactoidById);

  