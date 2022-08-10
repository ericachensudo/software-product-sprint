// adds photos to the page.
async function addRandomPhotos() {
    const imageContainer = document.getElementById("image-container");
    
    // clear previous message & image (if any)
    while (imageContainer.firstChild) {
        imageContainer.removeChild(imageContainer.firstChild);
    }

    const food_array = ['hamburger', 'hotpot', 'kdog', 'steak']
    const food = Math.floor(Math.random() * food_array.length);

    // set image source based on message
    let imgSrc;
    switch (food) {
      case 0:
        imgSrc = "/images/hamburger.jpeg";
        break;
      case 1:
        imgSrc = "/images/hotpot.jpeg";
        break;
      case 2:
        imgSrc = "/images/kdog.jpeg";
        break;
      case 3:
        imgSrc = "/images/steak.jpeg";
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
