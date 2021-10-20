import { Component } from "@angular/core";
import { DataService } from "@service/data/data.service";
import { UiService } from "@service/ui/ui.service";
import { Observable } from "rxjs";
import { map, withLatestFrom } from "rxjs/operators";
import { ApiObjectType } from "src/app/entity/data/api-object-type.enum";
import { Satellite } from "src/app/entity/data/satellite.class";
import { TerminalLiveData } from "src/app/entity/data/terminal-live-data.class";
import { Terminal } from "src/app/entity/data/terminal.class";

/**
 * Componente to display detailed informations of an object
 */
@Component({
  selector: "app-detail",
  templateUrl: "./detail.component.html",
  styleUrls: ["./detail.component.scss"]
})
export class DetailComponent{

  /**
   * Observable for satellite data
   */
  satelliteData: Observable<Satellite | null>;
  /**
   * Observable for terminal data
   */
  terminalData: Observable<Terminal | null>;
  /**
   * Observable for terminal live data
   */
  terminalLiveData: Observable<TerminalLiveData | null>;

  /**
   * Constructor initializing satellite, terminal and terminalLiveDate observables
   * 
   * @param uiService - injected ui-service
   * @param dataService - injected data-service
   */
  constructor(public uiService: UiService, private dataService: DataService) {

    this.satelliteData = this.uiService.clickedObjectId.pipe(
      map(uid => uid?.type === ApiObjectType.Satellite ? uid : null),
      withLatestFrom(this.dataService.satellites),
      map(([uid, satellites]) => satellites.filter(satellite => uid?.equals(satellite.getUid()))[0] ?? null)
    );

    this.terminalData = this.uiService.clickedObjectId.pipe(
      map(uid => uid?.type === ApiObjectType.TerminalLiveData ? uid : null),
      withLatestFrom(this.dataService.terminals),
      map(([uid, terminals]) => terminals.filter(terminal => uid?.id === terminal.id)[0] ?? null),
    );

    this.terminalLiveData = this.uiService.clickedObjectId.pipe(
      map(uid => uid?.type === ApiObjectType.TerminalLiveData ? uid : null),
      withLatestFrom(this.dataService.terminalLiveData),
      map(([uid, terminals]) => terminals.filter(terminal => uid?.id === terminal.id)[0] ?? null),
    );

  }

  /**
   * Closes the detail-window via ui-service
   */
  public close(): void {

    this.uiService.detailsOpen = false;

  }

}
