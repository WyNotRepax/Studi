import { ComponentFixture, TestBed } from "@angular/core/testing";

import { Visualizer3dComponent } from "./visualizer3d.component";

describe("Visualizer3dComponent", () => {
  let component: Visualizer3dComponent;
  let fixture: ComponentFixture<Visualizer3dComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [Visualizer3dComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Visualizer3dComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
