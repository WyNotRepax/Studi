import { Component } from "@angular/core";
import { MeshControllerService } from "@service/mesh-controller/mesh-controller.service";

/**
 * Component to display the menu
 * 
 * Toggles visible objects
 */
@Component({
  selector: "app-menu",
  templateUrl: "./menu.component.html",
  styleUrls: ["./menu.component.scss"]
})
export class MenuComponent {

  /**
   * Trigger to show teleports
   */
  public showTeleports: boolean = true;
  /**
   * Trigger to show terminals
   */
  public showTerminals: boolean = true;
  /**
   * Trigger to show satellites
   */
  public showSatellites: boolean = true;
  /**
   * Trigger to show beams/overlays
   */
  public showBeams: boolean = true;

  /**
   * Constructor injects services
   * 
   * @param meshControllerService - injected meshControlerService
   */
  constructor(private meshControllerService: MeshControllerService) { }

  // =======================================================
  // ================= PUBLIC FUNCTIONS ====================
  // =======================================================
  
  /**
   * Method to hide or show the teleports on the earth
   */
  public toggleTeleports(): void {

    if (this.showTeleports) {
      this.meshControllerService.showTeleports();
    } else {
      this.meshControllerService.hideTeleports();
    }

  }

  /**
   * Method to hide or show the terminals on the earth
   */
  public toggleTerminals(): void {

    if (this.showTerminals) {
      this.meshControllerService.showTerminals();
    } else {
      this.meshControllerService.hideTerminals();
    }

  }

  /**
   * Method to hide or show the satellites on the earth
   */
  public toggleSatellites(): void {

    if (this.showSatellites) {
      this.meshControllerService.showSatellites();
    } else {
      this.meshControllerService.hideSatellites();
    }

  }

  /**
   * Method to hide or show the beams on the earth
   */
  public toggleBeams(): void {

    if (this.showBeams) {
      this.meshControllerService.showMapOverlays();
    } else {
      this.meshControllerService.hideMapOverlays();
    }

  }

}
