import { Component } from "@angular/core";
import { ControlService } from "@service/control/control.service";
import { UiService } from "@service/ui/ui.service";

/**
 * Main component end entrypoint of the application
 */
@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.scss"]
})
export class AppComponent {
  /**
   * Title of the application
   */
  title = "3D-vis-app";
  /**
   * Trigger if menu is visible
   */
  opened: boolean;

  /**
   * Constructor injects services
   * 
   * @param controlService - injected controlService
   * @param uiService - injected uiService
   */
  constructor(public controlService:ControlService, public uiService:UiService) { }

}
