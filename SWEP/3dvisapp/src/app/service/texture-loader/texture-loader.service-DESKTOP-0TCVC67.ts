import { Injectable } from "@angular/core";
import * as THREE from "three";

/**
 * Service to create the canvas texture of the earth and the background texture
 */
@Injectable({
  providedIn: "root"
})
export class TextureLoaderService {

  /**
   * Texture loader variable
   */
  private textureLoader:THREE.TextureLoader;
  /**
   * Path where textures are saved
   */
  private static TEXTURE_PATH:String = "../../assets/texture/";

  /**
   * Constructor initializes the texture loader
   */
  constructor() { 

    this.textureLoader = new THREE.TextureLoader();

  }

  // =======================================================
  // ================= PUBLIC FUNCTIONS ====================
  // =======================================================

  /**
   * Creates a detailed and a not detailed canvas texture and sets the uniforms of the shadermaterial
   * 
   * @param mesh - mesh to be textured
   * @param canvas - canvas object of the scene
   */
  public convertMapdataToTexture(mesh:THREE.Mesh, canvas:HTMLCanvasElement): void {

    let detailTex = new THREE.CanvasTexture(canvas);
    let backgroundTex = new THREE.CanvasTexture(canvas);
    (mesh.material as THREE.ShaderMaterial).uniforms.DetailTex.value = detailTex;
    (mesh.material as THREE.ShaderMaterial).uniforms.BackgroundTex.value = backgroundTex;

  }

  /**
   * Creates the background texture of the scene
   * 
   * @returns - background texture of the scene
   */
  public loadOrbitTextures(): THREE.Texture {

    const tex = this.textureLoader.load(`${TextureLoaderService.TEXTURE_PATH}background-stars.diffuse.8192x4096.jpg`);
    return tex;

  }

}
