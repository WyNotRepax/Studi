import { Injectable } from "@angular/core";
import * as THREE from "three";
import { ObjectGeneratorService } from "@service/object-generator/object-generator.service";
import { DataService } from "@service/data/data.service";
import { Satellite } from "src/app/entity/data/satellite.class";
import { TerminalLiveData } from "src/app/entity/data/terminal-live-data.class";
import { MapOverlay } from "src/app/entity/data/map-overlay.class";
import { GeoDataType } from "src/app/entity/geo/geo-data-type.enum";
import { FeatureCollection } from "src/app/entity/geo/feature-collection.class";
import { filter, map, take } from "rxjs/operators";

/**
 * Service for maintaining the 3D-Objects
 * 
 * placing, scaling and setting the visibility of the objects
 */
@Injectable({
  providedIn: "root"
})
export class MeshControllerService {

  /**
   * Factor for scaling objects based on zoom level 
   */
  private static readonly SCALE_FACTOR:number = 0.05;
  /**
   * Static satellite altitude
   */
  public static readonly SAT_ALT = 120;
  /**
   * Static surface altitude of the earth mesh
   */
  public static readonly SURF_ALT = 90;
  /**
   * Eath mesh variable
   */
  private earth:THREE.Mesh;
  /**
   * Orbit mesh variable
   */
  private orbit: THREE.Mesh;
  /**
   * Group of terminal meshes
   */
  private terminalGroup: THREE.Group = new THREE.Group();
  /**
   * Group of teleport meshes
   */
  private teleportGroup: THREE.Group = new THREE.Group();
  /**
   * Group of satellite meshes
   */
  private satelliteGroup: THREE.Group = new THREE.Group();
  /**
   * Group of mapoverlay meshes
   */
  private mapOverLayGroup: THREE.Group = new THREE.Group();
  /**
   * Group of hovered object meshes
   */
  private hoverGroup: THREE.Group = new THREE.Group();

  /**
   * Constructor injects services
   * 
   * @param objectGeneratorService - injected objectGeneratorService
   * @param dataService - injected dataService
   */
  constructor(private objectGeneratorService: ObjectGeneratorService, private dataService: DataService) {}

  // =======================================================
  // ================= PUBLIC FUNCTIONS ====================
  // =======================================================

  /**
   * Method to scale the placeables 3D-objects
   * 
   * @param camera - camera of the scene
   */
  public scalePlaceables(camera: THREE.Camera): void {

    let scale = this.calcScale(camera);
    this.scale(scale);

  }

  /**
   * Adds teleports to the group of objects which shall be shown on hover
   */
  public showTeleports(): void {

    this.hoverGroup.add(this.teleportGroup);

  }

  /**
   * Remove teleports from the group of objects which shall be shown on hover
   */
  public hideTeleports(): void {

    this.hoverGroup.remove(this.teleportGroup);

  }

  /**
   * Adds terminals to the group of objects which shall be shown on hover
   */
  public showTerminals(): void {

    this.hoverGroup.add(this.terminalGroup);

  }

  /**
   * Remove terminals from the group of objects which shall be shown on hover
   */
  public hideTerminals(): void {

    this.hoverGroup.remove(this.terminalGroup);

  }

  /**
   * Adds satellites to the group of objects which shall be shown on hover
   */
  public showSatellites(): void {

    this.hoverGroup.add(this.satelliteGroup);

  }

  /**
   * Remove satellites from the group of objects which shall be shown on hover
   */
  public hideSatellites(): void {

    this.hoverGroup.remove(this.satelliteGroup);

  }

  /**
   * Adds mapoverlays to the group of objects which shall be shown on hover
   */
  public showMapOverlays(): void {

    this.earth.add(this.mapOverLayGroup);

  }

  /**
   * Remove mapoverlays from the group of objects which shall be shown on hover
   */
  public hideMapOverlays(): void {

    this.earth.remove(this.mapOverLayGroup);

  }
  
   /**
   * Initializes group with the earth-mesh and orbit-mesh
   * 
   * @returns - mesh group containing the earth and orbit mesh
   */
  public getInitialObject(): THREE.Group {

    return new THREE.Group().add(this.earth, this.orbit);


  }
  /**
   * Getter for earth mesh
   * 
   * @returns earth mesh
   */
  public getEarth(): THREE.Mesh {

    return this.earth;

  }

  /**
   * Getter for hoverGroup mesh group
   * 
   * @returns - hover mesh group
   */
  public getHoverGroup(): THREE.Group {
    return this.hoverGroup;
  }

  /**
   * Adds the canvas texture to the earth mesh via objectGeneratorService 
   * 
   * @param canvas - canvas of the scene
   */
  public addMapToEarth(canvas:HTMLCanvasElement): void {

    this.objectGeneratorService.addMapToEarth(canvas, this.earth);

  }

  // =======================================================
  // ================= PRIVATE FUNCTIONS ===================
  // =======================================================

   /**
   * Initializes all the objects for the initial scene
   */
  public createInitialObjects(): void {

    this.orbit = this.objectGeneratorService.createOrbit(1000, 128, 128);
    this.earth = this.objectGeneratorService.createEarth(MeshControllerService.SURF_ALT, 1024, 1024);

    this.dataService.satellites.subscribe(data => this.placeSatellites(data));
    this.dataService.terminalLiveData.subscribe(data => this.placeTerminals(data));
    this.dataService.mapOverlays.subscribe(data => this.placeMapOverlays(data));
    this.showSatellites();
    this.showTeleports();
    this.showTerminals();
    this.showMapOverlays();
    this.earth.add(this.hoverGroup);

  }

  /**
   * Method to place the terminals on to the scene
   * 
   * @param terminals - array of terminal data
   */
  private placeTerminals(terminals: TerminalLiveData[]): void {

    this.terminalGroup.clear();
    for (let terminal of terminals) {
      let mesh = this.objectGeneratorService.createTerminal(MeshControllerService.SURF_ALT, terminal.latitude, terminal.longitude, terminal.altitude ?? 0);
      mesh.userData = terminal.getUid();
      this.terminalGroup.add(mesh);
    }

  }


  /**
   * Method to place the satellites on to the scene
   * 
   * @param satellites - array of satallite data
   */
  private placeSatellites(satellites: Satellite[]): void {

    this.satelliteGroup.clear();
    for (let satellite of satellites) {
      let mesh = this.objectGeneratorService.createSatellite(MeshControllerService.SAT_ALT, satellite.longitude);
      mesh.userData = satellite.getUid();
      this.satelliteGroup.add(mesh);
    }

  }

  /**
   * Method to place the mapoverlays on to the scene
   * 
   * @param mapOverlays - array of mapOverlay data
   */
  private placeMapOverlays(mapOverlays: MapOverlay[]): void {

    this.mapOverLayGroup.clear();
    for (let mapOverlay of mapOverlays) {
      mapOverlay.data.pipe(
        filter(apiGeoData => apiGeoData.geodata.type === GeoDataType.FeatureCollection),
        map(apiGeoData => apiGeoData.geodata as FeatureCollection),
        take(1),
      ).subscribe(featureCollection => {
        let obj = this.objectGeneratorService.createMapOverlay(featureCollection, MeshControllerService.SURF_ALT , mapOverlay.color);
        this.mapOverLayGroup.add(obj);
      });
      mapOverlay.loadData();
    }

  }


  /**
   * Calculates the scale-factor for the placeables
   * 
   * @param camera - camera of the scene
   * @returns - calculated scale
   */
  private calcScale(camera: THREE.Camera): number {

    let earthPos: THREE.Vector3 = this.earth.position;
    let cameraPos: THREE.Vector3 = camera.position;
    let distance: number = earthPos.distanceTo(cameraPos) - MeshControllerService.SURF_ALT;
    let scale: number = (distance * MeshControllerService.SCALE_FACTOR);
    return scale;

  }

  /**
   * Scales the placeables
   * 
   * @param scale - calculated scale-factor
   */
  private scale(scale: number): void {

    this.terminalGroup.children.map((x) => x.scale.set(scale, scale, scale));
    this.teleportGroup.children.map((x) => x.scale.set(scale, scale, scale));
    this.satelliteGroup.children.map((x) => x.scale.set(scale, scale, scale));

  }

}
