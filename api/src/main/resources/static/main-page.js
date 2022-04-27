const apiPath = "/"

let currentFactoid = [];
let seconds = 15;
let myTimer = 0;

const startTimer = () => {
    myTimer = setInterval(updateCountdown, 1000);
}

const stopTimer = () => {
    clearInterval(myTimer);
    seconds = 15;
}

const updateCountdown = () => {
    countdownEl.innerHTML = `${seconds}`;
    seconds--;
    if (seconds == -1) {
        currentFactoid.axiom ? isFalse() : isTrue();
        stopTimer();
    }
}

//GET REQUESTS
const getFactoidById = () => {
    axios.get(apiPath + "factoid/getById/5", {})
        .then(factoid => showOutput(factoid))
        .catch(err => console.error(err));
};

const getRandomFactoid = () => {
    axios.get(apiPath + "factoid/getRandom", {})
        .then(factoid => showOutput(factoid))
        .catch(err => console.error(err));
};

const showOutput = (factoid) => {
    currentFactoid = factoid.data;
    document.getElementById('factoid').innerHTML = factoid.data.content;
};

const correct = () => {
    document.getElementById('factoid').innerHTML = "Correct!"
    document.getElementById('explanation').innerHTML = currentFactoid.explanation;
    document.body.style.background = "#00FF00";
}

const incorrect = () => {
    document.getElementById('factoid').innerHTML = "Wrong ):"
    document.getElementById('explanation').innerHTML = currentFactoid.explanation;
    document.body.style.background = "#FF0000"
}

const isTrue = () => {
    if (currentFactoid.axiom) {
        correct();
        return
    }
    incorrect();
    return
}

const isFalse = () => {
    if (!currentFactoid.axiom) {
        correct();
        return
    }
    incorrect();
    return
}

const clearExplanation = () => {
    document.getElementById('explanation').innerHTML = "";
}

document.addEventListener("DOMContentLoaded", () => {
    getRandomFactoid();
    startTimer();
});

const countdownEl = document.getElementById("countdown");

document.getElementById('tButton').addEventListener('click', () => {
    isTrue(),
        stopTimer();
});
document.getElementById('fButton').addEventListener('click', () => {
    isFalse(), stopTimer();
});
document.getElementById('nextButton').addEventListener('click', () => {
    getRandomFactoid(), clearExplanation(), stopTimer(), startTimer(), document.body.style.background = "";
});