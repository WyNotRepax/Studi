import { Injectable } from "@angular/core";
import * as THREE from "three";
import { BehaviorSubject, Observable } from "rxjs";
import { distinctUntilChanged, filter } from "rxjs/operators";
import { MeshControllerService } from "@service/mesh-controller/mesh-controller.service";
import { DataService } from "@service/data/data.service";
import { Uid } from "src/app/entity/data/uid.class";


/**
 * Service for determining the hovered object
 */
@Injectable({
  providedIn: "root"
})

export class UiService {

  /**
   * Raycaster variable for calculating raycasts
   */
  private raycaster: THREE.Raycaster;
  /**
   * Behaviour subject for the ID of the hovered object
   */
  private hoveredObjectId$: BehaviorSubject<Uid | null> = new BehaviorSubject<Uid | null>(null);
  /**
   * Observable for the ID of the hovered object
   */
  public readonly hoveredObjectId: Observable<Uid | null> = this.hoveredObjectId$.asObservable().pipe(distinctUntilChanged(Uid.equal));
  /**
   * Behaviour subject for the ID of the clicked object
   */
  private clickedObjectId$: BehaviorSubject<Uid | null> = new BehaviorSubject<Uid | null>(null);
  /**
   * Obervable for the ID of the clicked object
   */
  public readonly clickedObjectId: Observable<Uid | null> = this.clickedObjectId$.asObservable().pipe(distinctUntilChanged(Uid.equal));
  /**
   * Behaviour subject for the hover position of the client
   */
  private hoverPosition$: BehaviorSubject<{ x: number, y: number }> = new BehaviorSubject<{ x: number, y: number } | null>(null);
  /**
   * Observable for the hover position of the client
   */
  public readonly hoverPosition: Observable<{ x: number, y: number }> = this.hoverPosition$.asObservable().pipe(filter(pos => pos !== null), distinctUntilChanged((pos1, pos2) => pos1.x === pos2.x && pos1.y === pos2.y));
  /**
   * Trigger for open detail window
   */
  public detailsOpen:boolean;

  /**
   * Constructor initializes the raycaster
   * 
   * @param meshControllerService - injected meshControlerService
   * @param dataService - injected dataService
   */
  constructor(private meshControllerService: MeshControllerService, private dataService: DataService) {

    this.raycaster = new THREE.Raycaster();

  }

  // =======================================================
  // ================= PUBLIC FUNCTIONS ====================
  // =======================================================

  /**
   * Deterimines the object where the mouse is hovering over
   * 
   * @param mousePos - mousePosition in the scene
   * @param camera - camera of the scene
   */
  public hoverObject(mousePos: { screenspace: THREE.Vector2, absolute: THREE.Vector2 }, camera: THREE.Camera): void {

    this.hoverPosition$.next({ x: mousePos.absolute.x, y: mousePos.absolute.y });
    this.raycaster.setFromCamera(mousePos.screenspace, camera);
    let intersects = this.raycaster.intersectObjects(this.meshControllerService.getHoverGroup().children, true);

    if (intersects.length == 0) {
      this.hoveredObjectId$.next(null);
      document.onmousedown = null;
    } else {
      var that = this;
      document.onmousedown = function(){
	      that.detailsOpen = true;
        that.clickedObjectId$.next(intersects[0].object.userData as Uid);     
      };

      this.hoveredObjectId$.next(intersects[0].object.userData as Uid);
    }
    
  }

}
