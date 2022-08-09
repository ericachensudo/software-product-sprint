// Copyright 2020 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

// adds photos to the page.
async function addRandomPhotos() {
    const imageContainer = document.getElementById("image-container");
    
    // clear previous message & image (if any)
    while (imageContainer.firstChild) {
        imageContainer.removeChild(imageContainer.firstChild);
    }

    const food = Math.floor(Math.random() * 4);

    // set image source based on message
    let imgSrc;
    switch (food) {
      case 1:
        imgSrc = "/images/hamburger.jpeg";
        break;
      case 2:
        imgSrc = "/images/hotpot.jpeg";
        break;
      case 3:
        imgSrc = "/images/kdog.jpeg";
        break;
    }

    // display corresponding image
    let img = document.createElement("img");
    img.src = imgSrc;
    img.id = "random-img"
    imageContainer.appendChild(img);
}

function addRandomFacts() {
    const facts =
    ['I taught myself how to do the splits during the pandemic locakdown', 'I am currently learning to play the ukulele', 'I can speak Spanish and Mandarin', 'I attend an all womens college'];

    // Pick a random fact.
    const fact = facts[Math.floor(Math.random() * facts.length)];

    // Add it to the page.
    const factContainer = document.getElementById("fact-container");
    factContainer.innerText = fact;
}

//Adds current server time to the page inside dateContainer
async function showServerTime() {
    const responseFromServer = await fetch('/date');
    const textFromResponse = await responseFromServer.text();

    const dateContainer = document.getElementById('date-container');
    dateContainer.innerText = textFromResponse;
}


//Adds JSON data to the page inside jsonContainer
async function fetchJSON() {
    // Fetches JSON from server and turns it into JSON
    const responseFromServer = await fetch('/json');
    const jsonArray = await responseFromServer.json();

    // Prints out entire JSON array as well as 2nd element into console
    console.log(jsonArray);
    console.log(jsonArray[1]);

    // Sets text in jsonContainer to be entire JSON array
    const jsonContainer = document.getElementById('json-container');
    jsonContainer.innerText = jsonArray;
}

// Fetches messages from the server and adds them to the DOM
function loadMessages() {
    // fetch('/week2.html').then(response => response.json()).then((formResponses) => {
    fetch('/week2.html').then(response => response.json()).then((formResponses) => {
        const wishListElement = document.getElementById('week2');
        formResponses.forEach((oneMessage) => {
            wishListElement.appendChild(createMessageElement(oneMessage));
        })
    });
}
// Creates an element that represents a message, including its delete button
function createMessageElement(oneMessage) {
    const messageElement = document.createElement('li');
    messageElement.className = 'message';

    const titleElement = document.createElement('span');
    titleElement.innerText = oneMessage.textValue;

    const deleteButtonElement = document.createElement('button');
    deleteButtonElement.innerText = 'Delete';
    deleteButtonElement.className = 'fonts';
    deleteButtonElement.addEventListener('click', () => {
        deleteMessage(oneMessage);

        // Remove the task from the DOM.
        messageElement.remove();
    });

    messageElement.appendChild(titleElement);
    messageElement.appendChild(deleteButtonElement);
    return messageElement;
}

// Tells the server to delete the task
function deleteMessage(oneMessage) {
    const params = new URLSearchParams();
    params.append('id', oneMessage.id);
    fetch('/week2.html', { method: 'PUT', body: params });
}