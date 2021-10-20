import { identifierModuleUrl } from '@angular/compiler';
import { Injectable, OnDestroy, OnInit } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AufgabenStorageService {


  public static readonly localStorageKey = 'aufgaben_OPEN';
  public static readonly localStorageKeyDone = 'aufgaben_DONE';

  public aufgaben: Observable<string[]>;
  public aufgabenDone: Observable<string[]>;

  private aufgaben$: BehaviorSubject<string[]>;
  private aufgabenDone$: BehaviorSubject<string[]>;

  constructor() {
    this.loadAufgaben();
    this.loadAufgabenDone();
    this.aufgaben = this.aufgaben$.asObservable();
    this.aufgabenDone = this.aufgabenDone$.asObservable();
    this.aufgaben.subscribe((newArr) => localStorage.setItem(AufgabenStorageService.localStorageKey, JSON.stringify(newArr)));
    this.aufgabenDone.subscribe((newArr) => localStorage.setItem(AufgabenStorageService.localStorageKeyDone, JSON.stringify(newArr)));
  }

  public addAufgabe(aufgabe: string) {
    this.aufgaben$.next([...this.aufgaben$.value, aufgabe]);
  }

  public removeAufgabe(index: number) {
    this.aufgaben$.next([...this.aufgaben$.value.slice(0, index), ...this.aufgaben$.value.slice(index + 1)]);
  }

  public removeDone(index: number) {
    this.aufgabenDone$.next([...this.aufgabenDone$.value.slice(0, index), ...this.aufgabenDone$.value.slice(index + 1)]);
  }

  public addCompleted(aufgabe: string) {
    this.aufgabenDone$.next([...this.aufgabenDone$.value, aufgabe]);
  }

  public completeAufgabe(index: number) {
    this.addCompleted(this.aufgaben$.value[index]);
    this.removeAufgabe(index);
  }
  public clear() {
    this.aufgaben$.next(new Array<string>());
    this.aufgabenDone$.next(new Array<string>());
  }

  private save(): void {
    localStorage.setItem(AufgabenStorageService.localStorageKey, JSON.stringify(this.aufgaben$.value));
    localStorage.setItem(AufgabenStorageService.localStorageKeyDone, JSON.stringify(this.aufgabenDone$.value));
  }



  private loadAufgabenDone() {
    const save = localStorage.getItem(AufgabenStorageService.localStorageKeyDone);
    if (save !== null) {
      this.aufgabenDone$ = new BehaviorSubject(JSON.parse(save));
    } else {
      this.aufgabenDone$ = new BehaviorSubject(new Array<string>());
    }
  }
  private loadAufgaben() {
    const save = localStorage.getItem(AufgabenStorageService.localStorageKey);
    if (save !== null) {
      this.aufgaben$ = new BehaviorSubject(JSON.parse(save));
    } else {
      this.aufgaben$ = new BehaviorSubject(new Array<string>());
    }
  }

}
