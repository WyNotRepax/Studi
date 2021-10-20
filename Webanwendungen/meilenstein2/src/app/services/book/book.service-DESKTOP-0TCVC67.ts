import { Injectable, OnDestroy, OnInit } from '@angular/core';
import { Book } from './book';


const BOOKSERVICE_STRING = "BookService";

@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor() {
    let booksString = localStorage.getItem(BOOKSERVICE_STRING);
    if (booksString === null) {
      this.books = new Array<Book>();
    } else {
      this.books = JSON.parse(booksString);
    }
  }


  private books: Array<Book>;

  public save() {
    localStorage.setItem(BOOKSERVICE_STRING, JSON.stringify(this.books));
  }

  public getBooks(): Array<Book> {
    return this.books;
  }

  public getBooksByAuthor(author: string) {
    return this.books.filter(book => book.author === author);
  }

  public removeBook(book: Book) {
    this.books.splice(this.books.indexOf(book), 1)
    this.save();
  }

  public addBook(book: Book) {
    this.books.push(book);
    this.save();
  }
}
