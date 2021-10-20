import { Component, ElementRef, OnInit, ViewChild, ViewChildren } from '@angular/core';
import { BookService } from '../services/book/book.service';
import { Book } from '../services/book/book'

@Component({
  selector: 'app-all-books',
  templateUrl: './all-books.component.html',
  styleUrls: ['./all-books.component.css']
})
export class AllBooksComponent implements OnInit {

  constructor(public bookService: BookService) {
  }

  ngOnInit(): void {
  }

}
