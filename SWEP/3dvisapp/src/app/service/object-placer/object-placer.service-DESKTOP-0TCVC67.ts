import { Injectable } from "@angular/core";
import * as THREE from "three";

/**
 * Service for converting gps-coordiantes into xyz-cooridnates
 */
@Injectable({
  providedIn: "root"
})
export class ObjectPlacerService {

  /**
   * Default constructor
   */
  constructor() { }

  // =======================================================
  // ================= PUBLIC FUNCTIONS ====================
  // =======================================================

  /**
   * Calculates coordinates in radians from gps-coordinates
   * 
   * @param coords - gps coordiantes to be used for calculation
   * @returns - coordinates in radians
   */
  public transfromLatLon(coords: THREE.Vector3): THREE.Vector3 {

    const tCoords = new THREE.Vector3();
    tCoords.x = coords.x * (Math.PI / 180);
    tCoords.y = -coords.y * (Math.PI / 180);
    tCoords.z = coords.z;
    return tCoords;

  }

  /**
   * Calculates spherical xyz-coordinates
   * 
   * @param coords - coordinates in radians to be used for calculation
   * @returns - spherical xyz-coordinates
   */
  public calcCoords(coords: THREE.Vector3): THREE.Vector3 {

    let tLat = coords.x;
    let tLon = coords.y;
    let radius = coords.z;

    let x = radius * Math.cos(tLat) * Math.cos(tLon);
    let y = radius * Math.sin(tLat);
    let z = radius * Math.cos(tLat) * Math.sin(tLon);

    return new THREE.Vector3(x, y, z);

  }

  /**
   * Converts gps-coordinates into xyz-coordinates
   * 
   * @param coords - latitude and longitude of the object
   * @param height - height of the object or 0
   * @returns - spherical xyz-coordinates
   */
  public gps2Vec3(coords: THREE.Vector2, height?: number): THREE.Vector3 {

    return this.calcCoords(this.transfromLatLon(new THREE.Vector3(coords.x, coords.y, height ?? 0)));

  }

}
