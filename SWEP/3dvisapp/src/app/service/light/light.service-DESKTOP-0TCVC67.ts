import { Injectable } from "@angular/core";
import * as THREE from "three";

/**
 * Service for generating the light of the scene
 */
@Injectable({
  providedIn: "root"
})
export class LightService {

  /**
   * Default constructor
   */
  constructor() { }

  // =======================================================
  // ================= PUBLIC FUNCTIONS ====================
  // =======================================================

   /**
   * Generates a light based on lightType
   * 
   * @param type - determines the lighttype
   * @param color - determines the color of the light
   * @param intensity - determines the intensity of the light
   * @param x - sets x postion of the light
   * @param y - sets y postion of the light
   * @param z - sets z postion of the light
   * @param dist - maximum range of the light      
   * @param decay - amount the light dims along the distance of the light
   * @returns - created light
   */

  public generateLight(type:LightType, color:number, intensity:number=1, x:number=0, y:number=0, z:number=0, dist?:number, decay?:number): THREE.Light {

    let light:THREE.Light;
    
    switch (type) {
      case LightType.AMBIENT:
        light = new THREE.AmbientLight(color, intensity);
        break;
      case LightType.POINT:
        light = new THREE.PointLight(color, intensity, dist, decay);
        break;
      case LightType.DIRECTIONAL:
        light = new THREE.DirectionalLight(color, intensity);
        break;
    }

    light.position.set(x, y, z);
    
    return light;

  }

}

/**
 * Enum for different lightTypes
 */
export enum LightType {
  AMBIENT = 0,
  POINT = 1,
  DIRECTIONAL = 2
}
