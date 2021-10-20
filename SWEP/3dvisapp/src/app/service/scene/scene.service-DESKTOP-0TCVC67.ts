import { Injectable } from "@angular/core";
import * as THREE from "three";
import { LightService, LightType } from "@service/light/light.service";
import { MeshControllerService } from "@service/mesh-controller/mesh-controller.service";
import { ControlService } from "@service/control/control.service";
import { ConfigurationService } from "@service/configuration/configuration.service";
import { UiService } from "@service/ui/ui.service";
import { MapdataService } from "@service/mapdata/mapdata.service";

/**
 * Calculation degree to radians
 */
const DEG_2_RAD = Math.PI / 180;
/**
 * Calculation radians to degree
 */
const RAD_2_DEG = 1 / DEG_2_RAD;

/**
 * Service for initializing the main scene
 * 
 * Creates all necessary objects for the scene
 * 
 * Provides responsive canvas
 */
@Injectable({
  providedIn: "root"
})
export class SceneService {

  /**
   * Main scene of the application
   */
  private scene: THREE.Scene;
  /**
   * Main camera of the application
   */
  private camera: THREE.PerspectiveCamera;
  /**
   * Renderer for display loop
   */
  private renderer: THREE.WebGLRenderer;
  /**
   * Width of the canvas
   */
  private width: number;
  /**
   * Height of the canvas
   */
  private height: number;
  /**
   * Timer for updating controls properly
   */
  private clock: THREE.Clock = new THREE.Clock();
  /**
   * Initial fov of the camera
   */
  private initialFov: number;
  /**
   * Offset of the canvas
   */
  private offset: THREE.Vector2 = new THREE.Vector2();
  /**
   * Mouse position of the client
   */
  private mouse: THREE.Vector2 = new THREE.Vector2();
  /**
   * Buffer limitation for resizing
   */
  private bufferLimit = 5000;

  /**
   * Constructor injects services
   * 
   * @param lightService - injected lightService
   * @param meshController - injected meshControlerService
   * @param controlService - injected controlerService
   * @param configService - injected configService
   * @param uiService - injected uiService
   * @param mapdataService - injected mapdataSevice
   */
  constructor(
    private lightService: LightService,
    private meshController: MeshControllerService,
    private controlService: ControlService,
    private configService: ConfigurationService,
    private uiService: UiService,
    private mapdataService:MapdataService
  ) { }

  // =======================================================
  // ================= PUBLIC FUNCTIONS ====================
  // =======================================================

  /**
   * Setup initial scene and objects
   * 
   * @param canvas - html canvas object of the scene
   * @param camFov - fov for the camera
   * @param camNplane - near plane for the camera
   * @param camFplane - far plane for the camera
   * @param camX - x position for the camera
   * @param camY - y position for the camera
   * @param camZ - z position for the camera
   */
  public setupScene(canvas: HTMLCanvasElement,
                    camFov: number, camNplane: number, 
                    camFplane: number, camX: number = 0, 
                    camY: number = 0, camZ: number = 0,): void {

    this.createScene(canvas);
    this.createCamera(camFov, camNplane, camFplane, camX, camY, camZ);
    this.createRenderer(canvas);
    this.createControls(this.camera, canvas);
    this.addObjectsToScene();
    this.mapdataService.initOLMap().then(canvas => this.meshController.addMapToEarth(canvas));
    this.mapdataService.updateMapResolution(this.controlService.getOrbitControls(), this.camera, this.meshController.getEarth());
    this.initialFov = camFov;

  }

  /**
   * Updates the size of the canvas and the renderer if the window changed
   * 
   * @param width - current width of canvas
   * @param height - current height of canvas
   * @param xOffset - x offset position of the canvas
   * @param yOffset - y offset position of the canvas
   */
  public updateSize(width: number, height: number, xOffset: number, yOffset: number): void {
    this.width = width;
    this.height = height;
    this.offset.set(xOffset, yOffset);

    this.camera.aspect = this.width / this.height;
    if (this.camera.aspect < 1) {
      this.camera.fov = SceneService.fovY2fovX(75, this.camera.aspect);
    } else {
      this.camera.fov = this.initialFov;
    }
    this.camera.updateProjectionMatrix();
    this.renderer.setSize(this.width, this.height);

    // Fix for limited buffer Size by no longer using correct pixel Ratios for large canvas sizes ( > 5000 )
    // This fix only works until canvas size itself goes over that limit
    const bufferLimit = Math.max(width, height) * window.devicePixelRatio > this.bufferLimit;
    this.renderer.setPixelRatio(bufferLimit ? 1 : window.devicePixelRatio);
  }

  /**
   * Update loop of the scene
   * 
   * Updates controls, scale of objects, hover objects
   * 
   * Renders the scene
   */
  public updateScene(): void {

    const delta = this.clock.getDelta();
    if (this.controlService.getOrbitControls() != null) {
      this.controlService.getOrbitControls().update(delta);
      this.controlService.updateCamOnKeyInput();
    }

    this.meshController.scalePlaceables(this.camera);

    this.uiService.hoverObject({ screenspace: this.getNormMouseCoords(), absolute: this.getRelativeMouseCoords() }, this.camera);

    window.requestAnimationFrame(this.updateScene.bind(this));
    this.render();

  }

  /**
   * Creates scene light via lightService
   * 
   * @param type - lighttype to create
   * @param x - x position of the light
   * @param y - y position of the light
   * @param z - z position of the light
   */
  public createAdvancedLighting(type: LightType, x: number, y: number, z: number): void {

    const light: THREE.Light = this.lightService.generateLight(type, 0xffffff, 1, x, y, z);
    if (this.camera != null) {
      this.camera.add(light);
    }

  }

  /**
   * Sets the mouse position of the client
   * 
   * @param mouseX - x coordinate of the client cursor
   * @param mouseY - y coordinate of the client cursor
   */
  public setMousePos(mouseX: number, mouseY: number): void {

    this.mouse.set(mouseX, mouseY);

  }

  /**
   * Gets the mouse position of the client
   * 
   * @returns - xy-coordinates
   */
  public getMousePos(): THREE.Vector2 {

    return this.mouse;

  }


  // =======================================================
  // ================ PRIVATE FUNCTIONS ====================
  // =======================================================

  /**
   * Creates a new scene and initializes width an height
   * 
   * @param canvas - canvas object of the scene
   */
  private createScene(canvas: HTMLCanvasElement): void {

    this.width = canvas.getBoundingClientRect().width;
    this.width = canvas.getBoundingClientRect().height;
    this.scene = new THREE.Scene();

  }

  /**
   * Creates the main camera of the scene
   * 
   * @param fov - fov of the camera
   * @param nPlane - near plane of the camera
   * @param fPlane - far plane of the camera
   * @param x - x position of the camera
   * @param y - y position of the camera
   * @param z - z position of the camera
   */
  private createCamera(fov: number, nPlane: number, fPlane: number, x: number = 0, y: number = 0, z: number = 0): void {

    this.camera = new THREE.PerspectiveCamera(fov, (this.width / this.height), nPlane, fPlane);

    this.camera.position.set(x, y, z);
    this.addToScene(this.camera);

  }

  /**
   * Creates the renderer of the scene
   * 
   * @param canvas - canvas object of the scene
   */
  private createRenderer(canvas: HTMLCanvasElement): void {

    this.renderer = new THREE.WebGLRenderer({ canvas: canvas });
    this.renderer.setSize(this.width, this.height);
    this.renderer.setPixelRatio(Math.min(window.devicePixelRatio, 2));

  }

  /**
   * Loads keyboard config and creates controls via controlService
   * 
   * @param camera - main camera of the scene
   * @param canvas - canvas object of the scene
   */
  private createControls(camera: THREE.PerspectiveCamera, canvas: HTMLCanvasElement): void {

    //controls-config-data
    const keyBoardConfig = this.configService.loadControlConfig();

    this.controlService.initOrbitControls(MeshControllerService.SURF_ALT, camera, canvas, keyBoardConfig);
    this.controlService.createEventListener(canvas);

  }

  /**
   * Creates initial objects via meshControllerService and adds them to the scene
   */
  private addObjectsToScene(): void {

    this.meshController.createInitialObjects();
    this.scene.add(this.meshController.getInitialObject());

  }

  /**
   * Render loop of the scene
   */
  private render(): void {

    if (this.scene != null && this.camera != null && this.renderer != null) {
      this.renderer.render(this.scene, this.camera);
    }

  }

  /**
   * Gets the relative mouse position of the client
   * 
   * @returns - relative mouse xy-coordinates
   */
  private getRelativeMouseCoords(): THREE.Vector2 {

    return new THREE.Vector2(this.getMousePos().x, this.getMousePos().y).sub(this.offset);

  }

  /**
   * Gets the normalized mouse position of the client
   * 
   * @returns - normalized mouse xy-coordiantes
   */
  private getNormMouseCoords(): THREE.Vector2 {

    const relativeMousePos = this.getRelativeMouseCoords();
    return new THREE.Vector2(relativeMousePos.x / this.width, -relativeMousePos.y / this.height).multiplyScalar(2).sub(new THREE.Vector2(1, -1));

  }

  /**
   * Adds objects to the scene
   * 
   * @param obj - object to be added to the scene
   */
  private addToScene(obj: THREE.Object3D): void {

    if (this.scene != null) {
      this.scene.add(obj);
    }

  }

  /**
   * Converts y-fov to x-fov
   * 
   * @param fovX - x field of view
   * @param aspect - aspect ratio of the camera
   * @returns - new fov
   */
  private static fovY2fovX(fovX: number, aspect: number): number {

    const fovXRad = DEG_2_RAD * fovX;
    const fovYRad = 2 * Math.atan(Math.tan(fovXRad / 2) / aspect);
    return fovYRad * RAD_2_DEG;

  }

}