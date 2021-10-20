export class Book {
    public isbn: number;
    public titel: string;
    public author: string;
    public year: number;
    public pages: number;
    public publisher: string;

    constructor(isbn: number, titel: string, author: string, year: number, pages: number, publisher: string) {

        this.isbn = isbn;
        this.titel = titel;
        this.author = author;
        this.year = year;
        this.pages = pages;
        this.publisher = publisher;
    }
}
