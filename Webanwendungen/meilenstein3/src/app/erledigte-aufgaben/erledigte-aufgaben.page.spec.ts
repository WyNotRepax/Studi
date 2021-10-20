import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { ErledigteAufgabenPage } from './erledigte-aufgaben.page';

describe('ErledigteAufgabenPage', () => {
  let component: ErledigteAufgabenPage;
  let fixture: ComponentFixture<ErledigteAufgabenPage>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ ErledigteAufgabenPage ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(ErledigteAufgabenPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
