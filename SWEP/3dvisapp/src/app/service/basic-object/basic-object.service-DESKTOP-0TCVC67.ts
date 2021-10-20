import { Injectable } from "@angular/core";
import * as THREE from "three";

/**
 * vertex shader
 */
const _VS = `
  varying vec3 Normal;
  varying vec3 Position;
  varying vec2 VertexUV;

  void main() {
    VertexUV = uv;
    Normal = normalize(normalMatrix * normal);
    Position = vec3(modelViewMatrix * vec4(position, 1.0));
    gl_Position = projectionMatrix * modelViewMatrix * vec4(position, 1.0);
  }
`;

/**
 * fragment shader - phong shading
 */
const _FS = `
  varying vec3 Normal;
  varying vec3 Position;
  varying vec2 VertexUV;

  uniform sampler2D DetailTex;
  uniform sampler2D BackgroundTex;
  uniform vec2 Center;
  uniform float Scale;
  uniform vec3 LightPosition;
  uniform vec3 LightIntensity;
  uniform float Shininess;
  uniform float AmbientIntensity;
  uniform vec3 SpecColor;

  vec3 phong() {
    vec2 relPos = (VertexUV - Center);
    if(relPos.x > 0.5){
      relPos.x -= 1.0;
    }else if(relPos.x < -0.5) {
      relPos.x += 1.0;
    }
    relPos *= 2.0 * Scale;
    vec4 diffTex;
    if(relPos.x > 1.0 || relPos.x < -1.0 || relPos.y > 1.0 || relPos.y < -1.0){
      diffTex = texture2D(BackgroundTex, VertexUV);
    }else {
      diffTex = texture2D(DetailTex, relPos * 0.5 + 0.5);
    }
    vec3 n = normalize(Normal);
    vec3 s = normalize(LightPosition - Position);
    vec3 v = normalize(-Position);
    vec3 h = normalize(v + s);

    vec3 ambient = diffTex.rgb * AmbientIntensity;
    vec3 diffuse = diffTex.rgb * max(dot(s,n), 0.0) * LightIntensity;
    vec3 specular = SpecColor * pow(dot(n,h), Shininess) * LightIntensity;

    return (ambient + diffuse + specular);
  }

  void main() {
    gl_FragColor = vec4(phong(), 1.0);
  }
`;

/**
 * Service to generate basic geometric mesh objects
 */
@Injectable({
  providedIn: "root"
})
export class BasicObjectService {

  /**
   * Default constructor
   */
  constructor() { }

  // =======================================================
  // ================= PUBLIC FUNCTIONS ====================
  // =======================================================

  /**
   * Creates a sphere mesh
   * 
   * @param rad - radius of the sphere
   * @param wSeg - amount of width segments of the sphere
   * @param hSeg - amount of height segements of the sphere
   * @param diffMap - diffuse map of the sphere
   * @param alphaMap - alpha map of the sphere
   * @param normalMap - normal map of the sphere
   * @param specularMap - specular map of the sphere
   * @param matType - material type to be used
   * @param backSide - texture mapped on backside of the mesh
   * @returns - mesh of the sphere
   */
  public generateSphereObject(
    rad: number = 1, wSeg: number = 1, hSeg: number = 1,
    diffMap: THREE.Texture = null, alphaMap: THREE.Texture = null, normalMap: THREE.Texture = null, specularMap: THREE.Texture = null,
    matType: MaterialType = MaterialType.BASIC, backSide: boolean = false
  ): THREE.Mesh {

    const geo: THREE.SphereGeometry = new THREE.SphereGeometry(rad, wSeg, hSeg);
    let mat: THREE.Material;
    let mesh: THREE.Mesh<THREE.BufferGeometry, THREE.Material>;

    mat = this.createMaterial(matType, diffMap, alphaMap, normalMap, specularMap);
    mesh = this.createMesh(geo, mat, backSide);
    return mesh;

  }

  /**
   * Creates a box mesh
   * 
   * @param width - width of the box
   * @param height - height of the box
   * @param depth - depth of the box
   * @returns - mesh of the box
   */
  public generateBoxObject(width: number = 0.3, height: number = 0.3, depth: number = 0.3): THREE.Mesh {

    const geo: THREE.BufferGeometry = new THREE.BoxGeometry(width, height, depth);
    const mat: THREE.MeshBasicMaterial = new THREE.MeshPhongMaterial({ color: 0xff0000});
    const mesh: THREE.Mesh<THREE.BufferGeometry, THREE.Material> = new THREE.Mesh(geo, mat);
    return mesh;

  }

  /**
   * Creates a cynlinder mesh
   * 
   * @param radTop - radius of the top of the cylinder
   * @param radBot - radius of the bottom of the cylinder
   * @param height - height of the cylinder
   * @param radialSeg - amount of radial segements of the cylinder
   * @returns - mesh of the cylinder
   */
  public generateCylinderObject(radTop:number = 0.2, radBot:number = 0.2, height:number = 0.2, radialSeg:number = 32): THREE.Mesh {

    const geo:THREE.BufferGeometry = new THREE.CylinderGeometry(radTop, radBot, height, radialSeg);
    const mat:THREE.Material = new THREE.MeshPhongMaterial({ color: 0x00ff00 });
    const mesh:THREE.Mesh<THREE.BufferGeometry, THREE.Material> = new THREE.Mesh(geo, mat);
    return mesh;
 
  }

  /**
   * Ceates a cone mesh
   * 
   * @param rad - radius of the cone
   * @param height - height of the cone
   * @param radialSeg - amount of radial segemnts of the cone
   * @returns - mesh of the cone
   */
  public generateConeObject(rad:number=0.3, height:number=0.6, radialSeg:number=32): THREE.Mesh {

    const geo:THREE.BufferGeometry = new THREE.ConeGeometry(rad, height, radialSeg);
    const mat:THREE.Material = new THREE.MeshPhongMaterial({ color: 0xffff00 });
    const mesh:THREE.Mesh<THREE.BufferGeometry, THREE.Material> = new THREE.Mesh(geo, mat);
    return mesh;

  }

  // =======================================================
  // ================= PRIVATE FUNCTIONS ===================
  // =======================================================

  /**
   * Creates different materials based on material type
   * 
   * @param matType - material type to be created
   * @param diffMap - diffuse map for the material
   * @param alphaMap - alpha map for the material
   * @param normalMap - normal map for the material
   * @param specularMap - specular map for the material
   * @returns - new material
   */
  private createMaterial(matType: MaterialType, diffMap: THREE.Texture, alphaMap?: THREE.Texture, normalMap?: THREE.Texture, specularMap?: THREE.Texture): THREE.Material {

    let mat: THREE.Material;

    switch (matType) {
      case MaterialType.PHONG:
        mat = new THREE.MeshPhongMaterial({ map: diffMap, normalMap: normalMap, specularMap: specularMap });
        break;
      case MaterialType.LAMBERT:
        mat = new THREE.MeshLambertMaterial({ map: diffMap, alphaMap: alphaMap });
        break;
      case MaterialType.BASIC:
        mat = new THREE.MeshBasicMaterial({ map: diffMap });
        break;
      case MaterialType.SHADER:
        mat = new THREE.ShaderMaterial({
          uniforms: {
            DetailTex: { value: new THREE.Texture() },
            BackgroundTex: { value: new THREE.Texture() },
            LightPosition: { value: new THREE.Vector3(-200, 138, 200) },
            LightIntensity: { value: new THREE.Vector3(0.8,0.8,0.8) },
            Shininess: { value: 100.0 },
            SpecColor: { value: new THREE.Color(0.2,0.2,0.2) },
            Center: { value: new THREE.Vector2(0.5, 0.5) },
            Scale: { value: 1.0 },
            AmbientIntensity: { value: 0.2 }
          },
          vertexShader: _VS,
          fragmentShader: _FS,
        });
        break;
    }

    //set transparency if aplphaMap is set
    if (alphaMap !== null) {
      mat.transparent = true;
    }

    return mat;

  }

  /**
   * Creates a mesh with a texture mapped on the front or back side
   * 
   * @param geo - geomety from which a mesh is to be created
   * @param mat - texture of the mesh
   * @param backSide -  texture mapped on backside of the mesh
   * @returns - created mesh
   */
  private createMesh(geo: THREE.BufferGeometry, mat: THREE.Material, backSide?: boolean): THREE.Mesh<THREE.BufferGeometry, THREE.Material> {

    let model: THREE.Mesh<THREE.BufferGeometry, THREE.Material> = new THREE.Mesh(geo, mat);
    if (backSide) {
      model.material.side = THREE.BackSide;
    }
    return model;

  }

}

/**
 * Enum for the different material types
 */
export enum MaterialType {
  PHONG,
  LAMBERT,
  BASIC,
  SHADER
}