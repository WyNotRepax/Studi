import { AfterViewInit, Component, ElementRef, HostListener, ViewChild } from "@angular/core";

import { SceneService } from "@service/scene/scene.service";


/**
 * Main component custom component of the application
 */
@Component({
  selector: "app-visualizer3d",
  templateUrl: "./visualizer3d.component.html",
  styleUrls: ["./visualizer3d.component.scss"],
})
export class Visualizer3dComponent implements AfterViewInit {

  /**
   * HTML canvas element of the scene
   */
  @ViewChild("canvas")
  private canvas: ElementRef;
  /**
   * Observer for resizing window
   */
  private observer: ResizeObserver;

  /**
   * Constructor injects service and elementRef API
   * 
   * @param sceneService - injected sceneSevice
   * @param rootElement - injected elementRef
   */
  constructor(private sceneService: SceneService, private rootElement: ElementRef) { }

  // =======================================================
  // ================= PUBLIC FUNCTIONS ====================
  // =======================================================

  /**
   * Calls initializing methods for the scene
   */
  ngAfterViewInit(): void {

    this.initScene();
    this.animate();
    this.onResize();
    this.observer = new ResizeObserver(() => this.onResize());
    this.observer.observe(this.rootElement.nativeElement);

  }

  /**
   * Resizes canvas via sceneService
   */
  public onResize() {
    let rect = (this.rootElement.nativeElement as HTMLElement).getBoundingClientRect();
    this.sceneService.updateSize(rect.width, rect.height, rect.x, rect.y);

  }

  /**
   * Sets the mouse position via sceneService on mousemove in the window
   * 
   * @param e - triggered event of mousemove
   */
  @HostListener("window:mousemove", ["$event"])
  public onMouseMove(e: { clientX: number; clientY: number; }) {

    this.sceneService.setMousePos(e.clientX, e.clientY);

  }

  // =======================================================
  // ================= PRIVATE FUNCTIONS ===================
  // =======================================================

  /**
   * Calls scene-setup of the sceneService and adds ligthing
   */
  private initScene(): void {

    this.sceneService.setupScene(this.canvas.nativeElement, 75, 0.008, 1500, 250, 0, 0);
    this.sceneService.createAdvancedLighting(1, -200, 138, 200);

  }

  /**
   * Calls the update loop od the sceneService
   */
  private animate(): void {

    this.sceneService.updateScene();

  }

}
