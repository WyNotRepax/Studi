import { Component, Input, OnInit } from '@angular/core';
import { Book } from '../services/book/book';
import { BookService } from '../services/book/book.service';

@Component({
  selector: 'app-book-table',
  templateUrl: './book-table.component.html',
  styleUrls: ['./book-table.component.css']
})
export class BookTableComponent implements OnInit {
  @Input() filter = "";

  constructor(public bookService: BookService) {

  }

  ngOnInit(): void {
  }


  public onAddClick(isbnValue: string, titleValue: string, authorValue: string, yearValue: string, pagesValue: string, publisherValue: string) {
    this.bookService.addBook(new Book(parseInt(isbnValue), titleValue, authorValue, parseInt(yearValue), parseInt(pagesValue), publisherValue));
    let inputElements = document.getElementsByClassName("clear") as HTMLCollectionOf<HTMLInputElement>;
    for (let i = 0; i < inputElements.length; i++) {
      inputElements[i].value = "";
    }
  }


}
