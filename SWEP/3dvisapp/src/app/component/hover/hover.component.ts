import { Component } from "@angular/core";
import { trigger, state, style, animate, transition } from "@angular/animations";
import { UiService } from "@service/ui/ui.service";
import { DataService } from "@service/data/data.service";
import { Observable } from "rxjs";
import { map, withLatestFrom } from "rxjs/operators";
import { ApiObjectType } from "src/app/entity/data/api-object-type.enum";
import { Satellite } from "src/app/entity/data/satellite.class";
import { TerminalLiveData } from "src/app/entity/data/terminal-live-data.class";
import { Terminal } from "src/app/entity/data/terminal.class";


/**
 * Component to display basic information of an 3D-object on hover
 */
@Component({
  selector: "app-hover",
  animations: [
    trigger("state", [
      state("visible", style({
        opacity: "1"
      })),
      state("hidden", style({
        opacity: "0"
      })),
      transition("* => visible", [
        animate("0.1s 100ms ease-in")
      ])
    ])
  ],
  templateUrl: "./hover.component.html",
  styleUrls: ["./hover.component.scss"],
})
export class HoverComponent {

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
   * @param uiService - the UI-Service
   * @param dataService - the Data-Service
   */
  constructor(public uiService: UiService, private dataService: DataService) {

    this.satelliteData = this.uiService.hoveredObjectId.pipe(
      map(uid => uid?.type === ApiObjectType.Satellite ? uid : null),
      withLatestFrom(this.dataService.satellites),
      map(([uid, satellites]) => satellites.filter(satellite => uid?.equals(satellite.getUid()))[0] ?? null)
    );

    this.terminalData = this.uiService.hoveredObjectId.pipe(
      map(uid => uid?.type === ApiObjectType.TerminalLiveData ? uid : null),
      withLatestFrom(this.dataService.terminals),
      map(([uid, terminals]) => terminals.filter(terminal => uid?.id === terminal.id)[0] ?? null),
    );

    this.terminalLiveData = this.uiService.hoveredObjectId.pipe(
      map(uid => uid?.type === ApiObjectType.TerminalLiveData ? uid : null),
      withLatestFrom(this.dataService.terminalLiveData),
      map(([uid, terminals]) => terminals.filter(terminal => uid?.id === terminal.id)[0] ?? null),
    );
    
  }

  /**
   * Calculates the position from the hover window
   * 
   * @param position - x and y position
   * @returns - left and top position from the hover window
   */
   public calcPos(position: { x: number, y: number } | null):{} {
    if (position === null) {
      return {};
    }
    return { "left.px": position.x, "top.px": position.y };
  }
}
