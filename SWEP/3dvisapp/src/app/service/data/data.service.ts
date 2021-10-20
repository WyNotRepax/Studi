import { Injectable } from "@angular/core";
import { ApiService } from "@service/api/api.service";
import { BehaviorSubject, combineLatest, Observable } from "rxjs";
import { map } from "rxjs/operators";
import { HasUid } from "src/app/entity/data/has-uid.interface";
import { MapOverlay } from "src/app/entity/data/map-overlay.class";
import { Satellite } from "src/app/entity/data/satellite.class";
import { TerminalLiveData } from "src/app/entity/data/terminal-live-data.class";
import { Terminal } from "src/app/entity/data/terminal.class";
@Injectable({
  providedIn: "root"
})
export class DataService {

  public readonly terminals: Observable<Terminal[]>;
  public readonly terminalLiveData: Observable<TerminalLiveData[]>;
  public readonly satellites: Observable<Satellite[]>;
  public readonly data: Observable<HasUid[]>;
  public readonly mapOverlays: Observable<MapOverlay[]>;

  private terminals$: BehaviorSubject<Terminal[]> = new BehaviorSubject<Terminal[]>(new Array<Terminal>());
  private satellites$: BehaviorSubject<Satellite[]> = new BehaviorSubject<Satellite[]>(new Array<Satellite>());
  private terminalLiveData$: BehaviorSubject<TerminalLiveData[]> = new BehaviorSubject<TerminalLiveData[]>(new Array<TerminalLiveData>());
  private mapOverlays$: BehaviorSubject<MapOverlay[]> = new BehaviorSubject<MapOverlay[]>(new Array<MapOverlay>());

  constructor(private apiService: ApiService) {

    this.terminals = this.terminals$.asObservable();
    this.satellites = this.satellites$.asObservable();
    this.terminalLiveData = this.terminalLiveData$.asObservable();
    this.mapOverlays = this.mapOverlays$.asObservable();
    this.data = combineLatest([this.terminals, this.satellites]).pipe(map(([terminals, satellites]) => [...terminals, ...satellites]));
    this.updateData();

  }

  private updateData(): void {
    this.apiService.getSatellites().then(apiResponse => this.satellites$.next(apiResponse.data as Satellite[]));
    this.apiService.getTerminals().then(apiResponse => this.terminals$.next(apiResponse.data as Terminal[]));
    this.apiService.getTerminalLiveData().then(apiResponse => this.terminalLiveData$.next(apiResponse.data as TerminalLiveData[]));
    this.apiService.getMapOverlayData().then(apiResponse => this.mapOverlays$.next(apiResponse.data as MapOverlay[]));
  }

}
