import { Injectable } from "@angular/core";
import CameraControls from "camera-controls";
import { Map, View } from "ol";
import TileLayer from "ol/layer/Tile";
import OSM from "ol/source/OSM";
import * as THREE from "three";

/**
 * Service responsible for all mapdata related tasks
 */
@Injectable({
  providedIn: "root"
})
export class MapdataService {

  /**
   * OpenLayers map
   */
  private olMap:Map;
  /**
   * OpenStreetMap layer
   */
  private detailedLayer:OSM;
  /**
   * OpenLayers view element
   */
  private olView:View;
  /**
   * Initial resolution of the rendered OL map
   */
  private initialResolution:number;
  /**
   * Raycaster for calculating center raycast
   */
  private centerRc:THREE.Raycaster;

  /**
   * Constructor initialises raycaster
   */
  constructor() {

    this.centerRc = new THREE.Raycaster();

  }

  // =======================================================
  // ================= PUBLIC FUNCTIONS ====================
  // =======================================================

  /**
   * Initializing the OpenLayers map
   * 
   * @returns - rejected or resolved promise with the canvas element
   */
  public async initOLMap(): Promise<HTMLCanvasElement>{

    let osm = new OSM();

    this.detailedLayer = new TileLayer({
      source: osm
    });

    this.olView = new View({
      projection: "EPSG:4326",
      center: [0,0],
      zoom: 1
    });

    this.olMap = new Map({
      target: "ol-map",
      layers: [
        this.detailedLayer
      ],
      view: this.olView
    });

    this.initialResolution = this.olView.getResolution();

    return new Promise((resolve, reject) => this.olMap.once("rendercomplete", () => {
      let canvas = document.querySelector(".ol-layer canvas") as HTMLCanvasElement;
      if(canvas === null){
        reject("Could not find Canvas-Element!");
      }else {
        resolve(canvas);
      }
    }));

  }

  /**
   * Getter for the OpenLayers map
   * 
   * @returns - OpenLayers map
   */
  public getOLMap(): Map {

    return this.olMap;

  }

  /**
   * Calculating the texturefactor based on the map-resolution
   * 
   * @returns - calculated texturefactor
   */
  public getTextureFactor(): number {

    return this.initialResolution / this.olView.getResolution();

  }

  /**
   * Adds an event listener to the cameracontrols to maintain the mapdata with different zoomlevels
   * 
   * @param controls - used camera controls
   * @param camera - main camera of the scene
   * @param mesh - mesh in which uniforms to be updated (earth-mesh)
   */
  public updateMapResolution(controls:CameraControls, camera:THREE.Camera, mesh:THREE.Mesh): void {

    controls.addEventListener("sleep", () => this.changeMapDataOnZoom(controls, camera, mesh));
  
  }

  // =======================================================
  // ================= Private FUNCTIONS ===================
  // =======================================================

  /**
   * Updating mapdata based on camera zoomlevel and distance to the earth
   * 
   * @param controls - used camera controls
   * @param camera - main camera of the scene
   * @param mesh - mesh in which uniforms to be updated (earth-mesh)
   */
  private changeMapDataOnZoom(controls:CameraControls, camera:THREE.Camera, mesh:THREE.Mesh): void {

    this.centerRc.setFromCamera({x: 0, y: 0}, camera);
    let intersectsCenter:THREE.Intersection[] = this.centerRc.intersectObject(mesh);
    let cameraDist = controls.distance;
    this.updateZoomLevel(cameraDist);

    if(intersectsCenter.length > 0){
      let uv = intersectsCenter[0].uv;

      this.olView.setCenter(MapdataService.UVToGPS(uv));
      let center = MapdataService.GPSToUV(this.olView.getCenter());

      this.olMap.once("rendercomplete", () => {
        (mesh.material as THREE.ShaderMaterial).uniforms.Center.value = center; 
        (mesh.material as THREE.ShaderMaterial).uniforms.Scale.value = this.getTextureFactor();
        (mesh.material as THREE.ShaderMaterial).uniforms.DetailTex.value.needsUpdate = true;
      });
    }

  }

  /**
   * Updates the OpenLayers map zoomlevel based on distance between earth and camera
   * 
   * @param cameraDist - distance between camera and center of the mesh
   */
  private updateZoomLevel(cameraDist:number): void {

    if(cameraDist > 200) {
      this.olView.setZoom(1);
    }else if(cameraDist > 180){
      this.olView.setZoom(2);
    }else if(cameraDist > 133){
      this.olView.setZoom(3);
    }else if(cameraDist > 111){
      this.olView.setZoom(4);
    }else if(cameraDist > 100){
      this.olView.setZoom(5);
    }else if(cameraDist > 95){
      this.olView.setZoom(6);
    }else if(cameraDist > 92.3){
      this.olView.setZoom(7);
    }else if(cameraDist > 91.5){
      this.olView.setZoom(8);
    }else if(cameraDist > 90.7){
      this.olView.setZoom(9);
    }else if(cameraDist > 90.3){
      this.olView.setZoom(10);
    }else if(cameraDist > 90.15){
      this.olView.setZoom(11);
    }else if(cameraDist > 90.08){
      this.olView.setZoom(12);
    }else if(cameraDist > 90.04){
      this.olView.setZoom(13);
    }else if(cameraDist > 90.02){
      this.olView.setZoom(14);
    }else if(cameraDist > 90.01){
      this.olView.setZoom(15);
    }else if(cameraDist > 90){
      this.olView.setZoom(16);
    }

  }

  /**
   * Converts uv-coordinates into gps-coordinates
   * 
   * @param uv - uv coordinates to be converted
   * @returns - gps-coordinates
   */
  private static UVToGPS(uv:THREE.Vector2): number[] {

    return [
      360 * uv.x - 180,
      180 * uv.y - 90
    ];

  }

  /**
   * Converts gps-coordinates into uv-coordinates
   * 
   * @param gps - gps-coordinates to be converted
   * @returns - uv-coordinates
   */
  private static GPSToUV(gps:number[]): THREE.Vector2 {

    gps[0] = ((((gps[0]) + 180) % 360) + 360) % 360 - 180;
    return new THREE.Vector2((gps[0] + 180) / 360, (gps[1] + 90) / 180);

  }

}