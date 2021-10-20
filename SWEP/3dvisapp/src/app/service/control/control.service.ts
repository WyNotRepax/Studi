import { Injectable, ViewChild } from "@angular/core";
import * as THREE from "three";
import CameraControls from "camera-controls";
import { ElementRef } from "@angular/core";
import { ConfigData } from "@service/configuration/config-data.class";

CameraControls.install({ THREE: THREE });

/**
 * Service for controlling the position of the camera
 */
@Injectable({
  providedIn: "root"
})
export class ControlService {

  /**
   * HTML hover window
   */
  @ViewChild("refEl") el: ElementRef;
  /**
   * Orbit controls variable
   */
  private orbitControls: CameraControls;
  /**
   * Empty object to map keyboard inputs
   */
  private controls = {};
  /**
   * Empty object to map pressed keys
   */
  private keysPressed = {};
  /**
   * Surface altitude of the earth object
   */
  private surfaceAlt:number;
  /**
   * Factor to adjust ratation speed based on zoom level
   */
  private rotateFactor:number = 0.005;
  /**
   * Factor to adjust scroll speed based on zoom level
   */
  private scrollFactor:number = 0.005;
  /**
   * Factor to adjust rotate speed with keyboard input
   */
  private keyboardRotateFactor:number = 0.03;
  /**
   * Factor to adjust zoomspeed with keyboard input
   */
  private keyboardZoomFactor:number = 1;

  /**
   * Default constructor
   */
  constructor() { }

  // =======================================================
  // ================= PUBLIC FUNCTIONS ====================
  // =======================================================

   /**
   * Initializes the orbit controls
   * 
   * @param surfaceAlt - surfacealtitude of the orbit
   * @param camera -camera of the scene
   * @param canvas - canvas from the scene
   * @param configData - data from the config file
   */
  public initOrbitControls(surfaceAlt:number, camera: THREE.PerspectiveCamera, canvas: HTMLCanvasElement, configData:Promise<any>): void {

    this.surfaceAlt = surfaceAlt;
    this.orbitControls = new CameraControls(camera, canvas);
    this.orbitControls.minDistance = 90.01;
    this.orbitControls.maxDistance = 250;
    this.orbitControls.dampingFactor = 0.1;
    this.orbitControls.mouseButtons.left = CameraControls.ACTION.ROTATE;
    this.orbitControls.mouseButtons.wheel = CameraControls.ACTION.DOLLY;
    this.orbitControls.mouseButtons.right = CameraControls.ACTION.NONE;
    this.orbitControls.mouseButtons.middle = CameraControls.ACTION.NONE;

    this.updateSpeed();
    this.initKeyboardControls(configData);
    this.addKeyboardEvents(canvas);

  }

   /**
   * Creates an eventlistener for the mousewheel
   * 
   * @param canvas - canvas of the scene
   */
  public createEventListener(canvas: HTMLCanvasElement): void {

    canvas.addEventListener("wheel", (e) => this.onZoom(e));

  }

  /**
   * Getter for orbitcontrols
   * 
   * @returns - CameraControls orbtitcontrolls
   */
  public getOrbitControls(): CameraControls {

    return this.orbitControls;

  }

  /**
   * Updates the camera position when a key is pressed
   */
  public updateCamOnKeyInput(): void {

    let left = this.keysPressed[this.controls[ControlKeys.LEFT]];
    let right = this.keysPressed[this.controls[ControlKeys.RIGHT]];
    let up = this.keysPressed[this.controls[ControlKeys.UP]];
    let down = this.keysPressed[this.controls[ControlKeys.DOWN]];
    let zoomin = this.keysPressed[this.controls[ControlKeys.ZOOMIN]];
    let zoomout = this.keysPressed[this.controls[ControlKeys.ZOOMOUT]];

    if (left && !right && !up && !down) {
      this.orbitControls.rotate(-(this.orbitControls.azimuthRotateSpeed * this.keyboardRotateFactor), 0, true);
    } else if (left && up && !right && !down) {
      this.orbitControls.rotate(-(this.orbitControls.azimuthRotateSpeed * this.keyboardRotateFactor), -(this.orbitControls.polarRotateSpeed * this.keyboardRotateFactor), true);
    } else if (left && down && !right && !up) {
      this.orbitControls.rotate(-(this.orbitControls.azimuthRotateSpeed * this.keyboardRotateFactor), (this.orbitControls.polarRotateSpeed * this.keyboardRotateFactor), true);
    }
    //right multi-key controls
    else if (right && !left && !up && !down) {
      this.orbitControls.rotate((this.orbitControls.azimuthRotateSpeed * this.keyboardRotateFactor), 0, true);
    } else if (right && up && !left && !down) {
      this.orbitControls.rotate((this.orbitControls.azimuthRotateSpeed * this.keyboardRotateFactor), -(this.orbitControls.polarRotateSpeed * this.keyboardRotateFactor), true);
    } else if (right && down && !up && !left) {
      this.orbitControls.rotate((this.orbitControls.azimuthRotateSpeed * this.keyboardRotateFactor), (this.orbitControls.polarRotateSpeed * this.keyboardRotateFactor), true);
    }
    //up&down key-controls
    else if (up && !right && !left && !down) {
      this.orbitControls.rotate(0, -(this.orbitControls.polarRotateSpeed * this.keyboardRotateFactor), true);
    } else if (down && !right && !up && !left) {
      this.orbitControls.rotate(0, (this.orbitControls.polarRotateSpeed * this.keyboardRotateFactor), true);
    }
    //zoom key-controls
    else if (zoomin) {
      this.orbitControls.dolly(this.keyboardZoomFactor * this.orbitControls.dollySpeed);
      this.updateSpeed();
    } else if (zoomout) {
      this.orbitControls.dolly(-(this.keyboardZoomFactor * this.orbitControls.dollySpeed));
      this.updateSpeed();
    }

  }

  // =======================================================
  // ================= PRIVATE FUNCTIONS ===================
  // =======================================================

  /**
   * Initializes the keyboardcontrols
   * 
   * @param configData - data from the config file
   */
  private initKeyboardControls(configData: Promise<ConfigData>): void {

    configData.then(data => {
      this.controls[ControlKeys.LEFT] = data.left;
      this.controls[ControlKeys.RIGHT] = data.right;
      this.controls[ControlKeys.UP] = data.up;
      this.controls[ControlKeys.DOWN] = data.down;
      this.controls[ControlKeys.ZOOMIN] = data.zoomin;
      this.controls[ControlKeys.ZOOMOUT] = data.zoomout;
    });

  }

  /**
   * Adds an eventlistener for keyboardevents
   * 
   * @param canvas - canvas of the scene
   */
  private addKeyboardEvents(canvas: HTMLCanvasElement): void {

    canvas.addEventListener("keydown", (e) => this.isKeyDown(e));
    canvas.addEventListener("keyup", (e) => this.isKeyUp(e));

  }
  /**
   * Checks if key is pressed
   * 
   * @param e - triggered event
   */
  private isKeyDown(e): void {

    e.preventDefault();

    if (e.key === this.controls[ControlKeys.LEFT] || e.key === this.controls[ControlKeys.RIGHT] || e.key === this.controls[ControlKeys.UP] || e.key === this.controls[ControlKeys.DOWN] || e.key === this.controls[ControlKeys.ZOOMIN] || e.key === this.controls[ControlKeys.ZOOMOUT]) {
      this.keysPressed[e.key] = true;
    }

  }

   /**
   * Checks if key is not pressed
   * 
   * @param e - triggered eventtriggered event
   */
  private isKeyUp(e): void {

    e.preventDefault();

    if (e.key === this.controls[ControlKeys.LEFT] || e.key === this.controls[ControlKeys.RIGHT] || e.key === this.controls[ControlKeys.UP] || e.key === this.controls[ControlKeys.DOWN] || e.key === this.controls[ControlKeys.ZOOMIN] || e.key === this.controls[ControlKeys.ZOOMOUT]) {
      this.keysPressed[e.key] = false;
    }

  }

  /**
   * Checks if client zooms
   * 
   * @param e - triggered event
   */
  private onZoom(e): void {

    e.preventDefault();

    if (e.deltaY !== 0) {
      this.updateSpeed();
    }

  }

  /**
   * Updates the speed of the rotation and zoom based on camera distance
   */
  private updateSpeed(): void {

    let speed:number = (this.orbitControls.distance - this.surfaceAlt);
    this.orbitControls.azimuthRotateSpeed = speed * this.rotateFactor;
    this.orbitControls.polarRotateSpeed = speed * this.rotateFactor;
    this.orbitControls.dollySpeed = speed * this.scrollFactor;

  }

}

/**
 * Enum for ControlKeys
 */
export enum ControlKeys {
  LEFT = "left",
  RIGHT = "right",
  UP = "up",
  DOWN = "down",
  ZOOMIN = "zoomin",
  ZOOMOUT = "zoomout"
}
