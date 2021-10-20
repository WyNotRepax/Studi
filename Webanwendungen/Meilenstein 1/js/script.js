// Form Validation

function isValidIsbn(isbn) {
    if (isbn === undefined || typeof isbn !== "string") {
        return false;
    }
    let isbnN = parseInt(isbn)
    if (isNaN(isbnN) || isbnN < 0) {
        return false;
    }
    return (isbnN.toString().length === 13 || isbnN.toString().length === 10);
}

function isValidPageNumber(pageNumber) {
    if (pageNumber === undefined || typeof pageNumber !== "string") {
        return false;
    }
    let pageNumberN = parseInt(pageNumber);
    if (isNaN(pageNumberN)) {
        return false;
    }
    return pageNumberN > 0;
}

function isValidYear(year) {
    if (year === undefined || typeof year != "string") {
        return false;
    }
    let yearN = parseInt(year);
    if (isNaN(yearN)) {
        return false;
    }
    return year >= 1500 && yearN <= 2999;
}

function isValidTitle(title) {
    if (title === undefined || typeof title != "string" || title === "") {
        return false;
    }
    return true;
}

function isValidPublisher(publisher) {
    if (publisher === undefined || typeof publisher != "string" || publisher === "") {
        return false;
    }
    return true;
}

function addValidator(id, validator) {
    if (typeof id !== "string" || typeof validator !== "function") {
        return false;
    }
    $(id).on("input", (e) => {
        if (validator(e.target.value)) {
            e.target.setCustomValidity("")
        } else {
            e.target.setCustomValidity("Invalid")
        }
    })
    return true;
}

addValidator("#isbn", isValidIsbn);
addValidator("#seiten", isValidPageNumber);
addValidator("#titel", isValidTitle);
addValidator("#jahr", isValidYear);
addValidator("#verlag", isValidPublisher);


$('#isbn').on("input", (e) => {
    e.target.value = Math.abs(Math.round(e.target.value));
})

$('#seiten').on("input", (e) => {
    e.target.value = Math.abs(Math.round(e.target.value));
})
const addBookForm = $("#addBookForm")
addBookForm.submit((e) => {
    e.preventDefault()
    addBookForm.addClass("was-validated")
    if (e.target.checkValidity()) {
        let data = addBookForm.serializeArray()
        let obj = {}
        for(let entry of data){
            obj[entry.name] = entry.value;
        }
        userdata.push(
            {
                "isbn": obj.isbn,
                "title": obj.title,
                "author": obj.author,
                "year": obj.year,
                "pageNumber": obj.pageNumber,
                "publisher": obj.publisher
            }
        )
        addBook();

        $("#addBookModal").modal("hide")
    }
})
$("#addBookModal").on("hidden.bs.modal",()=>{
    addBookForm.element().reset();
})
$("#addBookButton").on("click", () => addBookForm.submit());

var userdata = [];

$(window).on("load",()=>{

    if(localStorage.getItem('book')){
        userdata = JSON.parse(localStorage.getItem('book'));
        addBook();
    }
    else{
        var data = [
            {
                "isbn": "3352000085",
                "title": "Don Quixote",
                "author": "Miguel de Cervantes",
                "year": "1605",
                "pageNumber": "123",
                "publisher": "Rütten & Loening Berlin"
            },
            {
                "isbn": "9780141439600",
                "title": "A Tale of Two Cities",
                "author": "Charles Dickens",
                "year": "1859",
                "pageNumber": "456",
                "publisher": "Penguin Books Ltd"
            }
        ]
        userdata = data;
        addBook();
        //addBook(3352000085,"Don Quixote","Miguel de Cervantes",1605,123," Rütten & Loening Berlin");
        //addBook(9780141439600,"A Tale of Two Cities","Charles Dickens",1859,456,"Penguin Books Ltd");
        //addBook(9780140449266,"The Count of Monte Cristo","Alexandre Dumas",1814,123,"TestPublisher1");
        //addBook(9780544003415,"The Lord of the Rings","J.R.R. Tolkien",1954,123,"TestPublisher1");
        //addBook(9781338299144,"Harry Potter and the Sorcerer’s Stone","J.K. Rowling",1997,123,"TestPublisher1");
    }

})

const bookTableBody = $("#bookTableBody")
function addBook() {
    console.log(userdata);
    bookTableBody.html('');
    for (var i = 0; i < userdata.length; i++) {
        let bookTableRowElement = document.createElement("tr");

        let cellElement = document.createElement("td");
        cellElement.innerText = String(userdata[i].isbn);
        let cellElement2 = document.createElement("td");
        cellElement2.innerText = String(userdata[i].title);
        let cellElement3 = document.createElement("td");
        cellElement3.innerText = String(userdata[i].author);
        let cellElement4 = document.createElement("td");
        cellElement4.innerText = String(userdata[i].year);
        let cellElement5 = document.createElement("td");
        cellElement5.innerText = String(userdata[i].pageNumber);
        let cellElement6 = document.createElement("td");
        cellElement6.innerText = String(userdata[i].publisher);
        bookTableRowElement.appendChild(cellElement);
        bookTableRowElement.appendChild(cellElement2);
        bookTableRowElement.appendChild(cellElement3);
        bookTableRowElement.appendChild(cellElement4);
        bookTableRowElement.appendChild(cellElement5);
        bookTableRowElement.appendChild(cellElement6);

        bookTableBody.append(bookTableRowElement);
    }

    localStorage.setItem('book', JSON.stringify(userdata));
}
