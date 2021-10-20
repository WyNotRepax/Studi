import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { FormsModule } from "@angular/forms";

import { AppComponent } from "./app.component";
import { Visualizer3dComponent } from "./component/visualizer3d/visualizer3d.component";
import { MenuComponent } from "./component/menu/menu.component";
import { HoverComponent } from "./component/hover/hover.component";

import { MaterialModule } from "./material/material.module";
import { ResizeObserverModule } from "@ng-web-apis/resize-observer";
import { HttpClientModule } from "@angular/common/http";
import { DetailComponent } from "./component/detail/detail.component";

@NgModule({
  declarations: [
    AppComponent,
    Visualizer3dComponent,
    MenuComponent,
    HoverComponent,
    DetailComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    BrowserAnimationsModule,
    MaterialModule,
    ResizeObserverModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent],
  schemas: [
    CUSTOM_ELEMENTS_SCHEMA
  ]
})

export class AppModule { }
