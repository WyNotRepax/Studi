import { Injectable } from "@angular/core";
import { BasicObjectService, MaterialType } from "@service/basic-object/basic-object.service";
import { TextureLoaderService } from "@service/texture-loader/texture-loader.service";
import { ObjectPlacerService } from "@service/object-placer/object-placer.service";
import * as THREE from "three";
import { FeatureCollection } from "src/app/entity/geo/feature-collection.class";
import { Coordinate } from "src/app/entity/geo/coordinate.class";
import { GeometryType } from "src/app/entity/geo/geometry-type.enum";
import { Polygon } from "src/app/entity/geo/polygon.class";
import { MultiPolygon } from "src/app/entity/geo/multi-polygon.class";

/**
 * Service to create all specific objects displayed in the scene
 */
@Injectable({
  providedIn: "root"
})
export class ObjectGeneratorService {

  /**
   * Correction number
   */
  private static readonly EPSILON: number = 0.4;
  /**
   * Prototype mesh for a cylinder
   */
  private cylinderPrototype: THREE.Mesh;
  /**
   * Prototye mesh for a box
   */
  private boxPrototype: THREE.Mesh;
  /**
   * Prototype mesh for a cone
   */
  private conePrototype: THREE.Mesh;

  /**
   * Construtor initializes the prototype meshes
   * 
   * @param basicObjectService - injected basicObjectService
   * @param textureLoaderService - injected textureLoaderService
   * @param objectPlacerService - injected objectPlacerSevice
   */
  constructor(private basicObjectService: BasicObjectService, private textureLoaderService: TextureLoaderService, private objectPlacerService: ObjectPlacerService) {

    this.cylinderPrototype = this.basicObjectService.generateCylinderObject();
    this.boxPrototype = this.basicObjectService.generateBoxObject();
    this.conePrototype = this.basicObjectService.generateConeObject();

  }

  // =======================================================
  // ================= PUBLIC FUNCTIONS ====================
  // =======================================================

  /**
   * Creates the background of the scene
   * 
   * @param rad - radius of the mesh
   * @param wSeg - width segments of the mesh
   * @param hSeg - height segements of the mesh
   * @returns - sphere mesh
   */
  public createOrbit(rad: number, wSeg: number, hSeg: number): THREE.Mesh {

    const texture: THREE.Texture = this.textureLoaderService.loadOrbitTextures();
    const orbit: THREE.Mesh = this.basicObjectService.generateSphereObject(rad, wSeg, hSeg, texture, null, null, null, MaterialType.BASIC, true);
    return orbit;

  }

  /**
   * Creates the erath sphere
   * 
   * @param surfAlt - surface altitude/radius of the earth sphere
   * @param wSeg - width segements of the earth sphere
   * @param hSeg - height segments of the earth sphere
   * @returns - earth mesh
   */
  public createEarth(surfAlt: number, wSeg: number, hSeg: number): THREE.Mesh {

    const earth: THREE.Mesh = this.basicObjectService.generateSphereObject(surfAlt, wSeg, hSeg, null, null, null, null, MaterialType.SHADER);
    return earth;

  }

  /**
   * Adds the earth texture to the earth mesh via textureLoaderService
   * 
   * @param canvas - canvas object of the scene
   * @param mesh - mesh to be textured
   */
  public addMapToEarth(canvas: HTMLCanvasElement, mesh: THREE.Mesh): void {

    this.textureLoaderService.convertMapdataToTexture(mesh, canvas);

  }

  /**
   * Creates terminal mesh
   * 
   * @param surfAlt - surface altitude/radius of the earth mesh
   * @param lat - latitude of the mesh
   * @param lng - longitude of the mesh
   * @param alt - altitude of the mesh
   * @returns - terminal mesh
   */
  public createTerminal(surfAlt: number, lat: number, lng: number, alt: number): THREE.Mesh {

    let mesh = this.cylinderPrototype.clone();
    let coords: THREE.Vector3 = new THREE.Vector3();
    coords.set(lat, lng, surfAlt + alt);
    this.setPositionRotation(mesh, coords);
    return mesh;

  }

  /**
   * Creates teleport mesh
   * 
   * @param surfAlt - surface altitude/radius of teh earth mesh
   * @param lat - latitude of the mesh
   * @param lng - longitude of the mesh
   * @returns - teleport mesh
   */
  public createTeleport(surfAlt: number, lat: number, lng: number): THREE.Mesh {

    let mesh = this.boxPrototype.clone();
    let coords = new THREE.Vector3(lat, lng, surfAlt);
    this.setPositionRotation(mesh, coords);
    return mesh;

  }

  /**
   * Creates satellite mesh
   * 
   * @param satAlt - surface altitude/radius of earth mesh
   * @param lng - longitude of the mesh
   * @returns - satellite mesh
   */
  public createSatellite(satAlt: number, lng: number): THREE.Mesh {

    let mesh = this.conePrototype.clone();
    let coords = new THREE.Vector3(0, lng, satAlt);
    this.setPositionRotation(mesh, coords);
    return mesh;

  }

  /**
   * Creates mapoverlays
   * 
   * @param featureCollection - geodata of the overlays to be created
   * @param surfAlt - surface altitude/radius of earth mesh
   * @param color - color of the overlay
   * @returns - mapoverlay mesh group
   */
  public createMapOverlay(featureCollection: FeatureCollection, surfAlt: number, color?: string): THREE.Object3D {

    let overlayMeshGroup = new THREE.Group;
    let material = new THREE.MeshBasicMaterial();
    material.color = new THREE.Color(color ?? 0xffffff);
    material.opacity = 0.3;
    material.transparent = true;

    for (let feature of featureCollection.features) {
      let geometry = feature.geometry;
      if (geometry.type === GeometryType.Polygon) {
        let polygon = geometry as Polygon;
        let mesh = this.createSurfacePolygonMesh(polygon, surfAlt);
        mesh.material = material;
        overlayMeshGroup.add(mesh);
      } else if (geometry.type === GeometryType.MultiPolygon) {
        let multiPolygon = geometry as MultiPolygon;
        let multiPolygonGroup = new THREE.Group();
        for (let polygon of multiPolygon.polygons) {
          let mesh = this.createSurfacePolygonMesh(polygon, surfAlt);
          mesh.material = material;
          multiPolygonGroup.add(mesh);
        }
        overlayMeshGroup.add(multiPolygonGroup);
      } else {
        console.error("Unknown Geometry type ", geometry.type);
      }
    }

    return overlayMeshGroup;

  }

  // =======================================================
  // ================= PRIVATE FUNCTIONS ===================
  // =======================================================

  /**
   * Sets the position and correct rotation of a mesh
   * 
   * @param mesh - mesh to be positioned and rotated
   * @param coords - latitude, longitude, altitude of the mesh
   */
  private setPositionRotation(mesh, coords: THREE.Vector3): void {

    const tLatLon = this.objectPlacerService.transfromLatLon(coords);
    const tCoords = this.objectPlacerService.calcCoords(tLatLon);
    mesh.position.set(tCoords.x, tCoords.y, tCoords.z);
    mesh.rotation.set(0.0, -tLatLon.y, tLatLon.x + Math.PI * 0.5);

  }

  /**
   * Converts a coordinate into a vector2
   * 
   * @param coordinate - coordiante to be converted
   * @returns - xy-coordinates
   */
  private static coordinate2Vector(coordinate: Coordinate): THREE.Vector2 {

    return new THREE.Vector2(coordinate.x, coordinate.y);

  }

  /**
   * Creates a mesh at a specific height from a Polygon
   * 
   * @param polygon - The Polygon to create a Mesh from
   * @param surfAlt - The surface altitude of the Polygon
   * @returns A THREE.Mesh representing the Polygon
   */
  private createSurfacePolygonMesh(polygon: Polygon, surfAlt: number): THREE.Mesh {

    let vertices = polygon.coordinates.map(vertice => ObjectGeneratorService.coordinate2Vector(vertice));
    let indices = THREE.ShapeUtils.triangulateShape(vertices, []);
    let geometry = new THREE.BufferGeometry();

    const stepSize = 5;

    let remeshedIndices = ObjectGeneratorService.divideTriangles(indices, stepSize, vertices);

    let verticesTransformed = vertices.map(vertex => this.objectPlacerService.gps2Vec3(vertex, surfAlt + ObjectGeneratorService.EPSILON));
    geometry.setFromPoints(verticesTransformed);
    geometry.setIndex([].concat(...remeshedIndices).reverse());

    let mesh = new THREE.Mesh();
    mesh.geometry = geometry;
    return mesh;

  }

  /**
   * Divides an array of triangles into a new array of triangles where each triangle has at most the specified sidelength
   * 
   * @param indices - An array of triangle indices
   * @param stepSize - The maximum length each side should be after subdivision
   * @param vertices - An array of vertices. New vertices will be added to this
   * @returns An array of subdivided Triangle indices
   */
  private static divideTriangles(indices: number[][], stepSize: number, vertices: THREE.Vector2[]): number[][] {

    // loop Over all triangles and divide each one
    let newTriangles: number[][] = [];
    let divisionCache = new Map<string, number[]>();
    for (let [p1, p2, p3] of indices) {
      let trianglesToAdd = this.divideTriangle(p1, p2, p3, stepSize, vertices, divisionCache);
      newTriangles.push(...trianglesToAdd);
    }

    return newTriangles;

  }

  /**
   * Divides a triangle into an array of triangles where each triangle has at most the specified sidelength
   * 
   * @param p1 - The index of the first point of the triangle
   * @param p2 - The index of the second point of the triangle
   * @param p3 - The index of the third point of the triangle
   * @param stepSize - The maximum length each side should be after subdivision 
   * @param vertices - The array of vertices
   * @param divisionCache - A map of divisions of sides where new triangles have already been created
   * @returns An array of subdivided Triangle indices
   */
  private static divideTriangle(p1: number, p2: number, p3: number, stepSize: number, vertices: THREE.Vector2[], divisionCache: Map<string, number[]>): number[][] {

    // Start recursive division with unknown side divisions
    return this.divideTriangleRecursive(p1, undefined, p2, undefined, p3, undefined, stepSize, vertices, divisionCache);

  }

  /**
   * Divides a triangle recursively into an array of triangles where each triangle has at most the specified sidelength
   * 
   * @param p1 - The index of the first point of the triangle
   * @param p1p2 - A list of points to divide the line between p1 and p2 into if one is already known, undefined otherwise
   * @param p2 - The index of the second point of the triangle
   * @param p2p3 - A list of points to divide the line between p2 and p3 into if one is already known, undefined otherwise
   * @param p3 - The index of the third point of the triangle
   * @param p3p1 - A list of points to divide the line between p3 and p1 into if one is already known, undefined otherwise
   * @param stepSize - The maximum length each side should be after subdivision 
   * @param vertices - The array of vertices
   * @param divisionCache - A map of divisions of sides where new vertices have already been created
   * @returns An array of subdivided Triangle indices
   */
  private static divideTriangleRecursive(p1: number, p1p2: number[] | undefined, p2: number, p2p3: number[] | undefined, p3: number, p3p1: number[] | undefined, stepSize: number, vertices: THREE.Vector2[], divisionCache: Map<string, number[]>): number[][] {

    // Calculate divisions for sides with undifined side divisions
    if (p1p2 === undefined) {
      p1p2 = this.divideLine(p1, p2, stepSize, vertices, divisionCache);
    }
    if (p2p3 === undefined) {
      p2p3 = this.divideLine(p2, p3, stepSize, vertices, divisionCache);
    }
    if (p3p1 === undefined) {
      p3p1 = this.divideLine(p3, p1, stepSize, vertices, divisionCache);
    }

    if (p1p2.length === 0 && p2p3.length === 0 && p3p1.length === 0) {
      // We are done
      return [[p1, p2, p3]];
    }

    // Triangulate remaining Triangle if one or more sides are divided enough
    if (p1p2.length == 0) {
      return this.divideTriangleOneLineDone(p2, p2p3, p3, p3p1, p1);
    }
    if (p2p3.length == 0) {
      return this.divideTriangleOneLineDone(p3, p3p1, p1, p1p2, p2);
    }
    if (p3p1.length == 0) {
      return this.divideTriangleOneLineDone(p1, p1p2, p2, p2p3, p3);
    }

    // Get indices of center indices within each side
    let center1 = Math.floor(p1p2.length / 2);
    let center2 = Math.floor(p2p3.length / 2);
    let center3 = Math.floor(p3p1.length / 2);

    // Get indices of center points for each side
    let p12 = p1p2[center1];
    let p23 = p2p3[center2];
    let p31 = p3p1[center3];

    // Divide sides
    let p1p12 = p1p2.slice(0, center1);
    let p12p2 = p1p2.slice(center1 + 1);
    let p2p23 = p2p3.slice(0, center2);
    let p23p3 = p2p3.slice(center2 + 1);
    let p3p31 = p3p1.slice(0, center3);
    let p31p1 = p3p1.slice(center3 + 1);

    // Call division recursively
    let centerTri = this.divideTriangleRecursive(p12, undefined, p23, undefined, p31, undefined, stepSize, vertices, divisionCache);
    let cornerTri1 = this.divideTriangleRecursive(p1, p1p12, p12, undefined, p31, p31p1, stepSize, vertices, divisionCache);
    let cornerTri2 = this.divideTriangleRecursive(p2, p2p23, p23, undefined, p12, p12p2, stepSize, vertices, divisionCache);
    let cornerTri3 = this.divideTriangleRecursive(p3, p3p31, p31, undefined, p23, p23p3, stepSize, vertices, divisionCache);

    // return union of results
    return [...centerTri, ...cornerTri1, ...cornerTri2, ...cornerTri3];

  }

  /**
   * Divides a triangle where the side between p3 and p1 is already short enough
   * and the other sides already have divisions into an array of triangles
   * 
   * @param p1 - The index of the first point of the triangle
   * @param p1p2 - An array of indices of points between p1 and p2
   * @param p2 - The index of the second point of the triangle
   * @param p2p3 - An array of indices of points between p2 and p3
   * @param p3 - The index of the third point of the triangle
   * @returns An array of triangle indices
   */
  private static divideTriangleOneLineDone(p1: number, p1p2: number[], p2: number, p2p3: number[], p3: number): number[][] {

    let newTriangles: number[][] = [];

    // Shorten longer side until both sides have same number of divisions
    while (p1p2.length > p2p3.length) {
      let pNew = p1p2.shift();
      newTriangles.push([p1, pNew, p3]);
      p1 = pNew;
    }
    while (p2p3.length > p1p2.length) {
      let pNew = p2p3.pop();
      newTriangles.push([p1, pNew, p3]);
      p3 = pNew;
    }

    // For each pair add 2 Triangles then overwrite p1 and p3
    while (p1p2.length > 0) {
      let pNew1 = p1p2.shift();
      let pNew2 = p2p3.pop();

      newTriangles.push([p1, pNew2, p3]);
      newTriangles.push([p1, pNew1, pNew2]);

      p1 = pNew1;
      p3 = pNew2;
    }

    // Add final triangle
    newTriangles.push([p1, p2, p3]);

    return newTriangles;

  }

  /**
   * Divides a line by adding new vertices so that each line segment is shorter then the value given.
   * This will always look for already made vertices first via the division Cache
   * 
   * @param p1 - The index of the first point
   * @param p2 - The index of the second point
   * @param stepSize - The maximum length each line segment should be
   * @param vertices - An array of vertices. New vertices will be added to this
   * @param divisionCache - A map of divisions of sides where new vertices have already been created
   * @returns An array of indices of points between p1 and p2
   */
  private static divideLine(p1: number, p2: number, stepSize: number, vertices: THREE.Vector2[], divisionCache: Map<string, number[]>): number[] {

    let dist = vertices[p1].distanceTo(vertices[p2]);
    // Distance is smaller then stepSize => no need to divide
    if (dist < stepSize) {
      return [];
    }

    // If we have divided this line before return the saved division
    let division = divisionCache.get([p1, p2].toString());
    if (division !== undefined) {
      // return a copy since the cached version should never change
      return division.slice();
    }
    division = divisionCache.get([p2, p1].toString());
    if (division !== undefined) {
      // return a copy since the cached version should never change
      return division.reverse().slice();
    }

    let steps = Math.ceil(dist / stepSize);

    let newIndices: number[] = [];
    for (let step = 1; step < steps; step++) {
      let newVertex = new THREE.Vector2().lerpVectors(vertices[p1], vertices[p2], step / steps);
      newIndices.push(vertices.length);
      vertices.push(newVertex);
    }

    // Add new division to cache
    if (newIndices.length > 0) {
      divisionCache.set([p1, p2].toString(), newIndices);
    }

    // return a copy since the cached version should never change
    return newIndices.slice();

  }

}