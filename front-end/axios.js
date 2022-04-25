//import axios from 'axios';


//const { default: axios } = require("axios");

// GET REQUEST
/* function getTodos() {
    // console.log('GET Request');
    axios({
            method: 'get',
            url: "https://jsonplaceholder.typicode.com/todos"
        }).then(res => showOutput(res))
        .catch(err = alert(err));
} */

/* function getAllPosts(){
    //axios.get(url).then
}  */

//GET REQUEST
const getUserById = () =>{
    axios.get('http://localhost:8080/getUserById/' + document.getElementById("userIdInput").value,{
    })
    .then(res => showOutput(res))
    .catch(err => console.error(err));
};

const getAllUsers = () =>{
    axios.get('http://localhost:8080/getAllUsers',{
    })
    .then(res => showOutput(res))
    .catch(err => console.error(err));
};

// POST REQUEST  
const addTodo = () => {
    axios.post('http://localhost:8080/createUser',
    {
        "firstName": "Bob",
        "lastName": "Harrison",
        "userName": "BobBoy88"    
    })
    .then(res => showOutput(res))
    .catch(err => console.error(err));
};

// PUT/PATCH REQUEST  
function updateTodo() {
    console.log('PUT/PATCH Request');
};
// DELETE REQUEST  
function removeTodo() {
    console.log('DELETE Request');
};
// SIMULTANEOUS DATA  
function getData() {
    console.log('Simultaneous Request');
};
// CUSTOM HEADERS  
function customHeaders() {
    console.log('Custom Headers');
};
// TRANSFORMING REQUESTS & RESPONSES  
function transformResponse() {
    console.log('Transform Response');
};
// ERROR HANDLING  
function errorHandling() {
    console.log('Error Handling');
};
// CANCEL TOKEN  
function cancelToken() {
    console.log('Cancel Token');
};
// INTERCEPTING REQUESTS & RESPONSES  // AXIOS INSTANCES  // Show output in browser  
function showOutput(res) {
    document.getElementById('res').innerHTML = ` <div class="card card-body mb-4">
    <h5>Status: ${res.status}</h5>
</div>
<div class="card mt-3">
    <div class="card-header"> Headers </div>
    <div class="card-body">
        <pre>${JSON.stringify(res.headers, null, 2)}</pre>
    </div>
</div>
<div class="card mt-3">
    <div class="card-header"> Data </div>
    <div class="card-body">
        <pre>${JSON.stringify(res.data, null, 2)}</pre>
    </div>
</div>
<div class="card mt-3">
    <div class="card-header"> Config </div>
    <div class="card-body">
        <pre>${JSON.stringify(res.config, null, 2)}</pre>
    </div>
</div> `;
};


document.getElementById('getAll').addEventListener('click', getAllUsers);
document.getElementById('post').addEventListener('click', addTodo);
document.getElementById('update').addEventListener('click', updateTodo);
document.getElementById('delete').addEventListener('click', removeTodo);
document.getElementById('sim').addEventListener('click', getData);
document.getElementById('headers').addEventListener('click', customHeaders);
document.getElementById('transform').addEventListener('click', transformResponse);
document.getElementById('error').addEventListener('click', errorHandling);
document.getElementById('cancel').addEventListener('click', cancelToken);