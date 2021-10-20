import { BehaviorSubject, Observable } from "rxjs";
import { filter } from "rxjs/operators";
import { getPropertyValue } from "../conversion-helper";
import { ApiGeoData } from "./api-geo-data.class";
import { ApiObjectType } from "./api-object-type.enum";
import { ApiObject } from "./api-object.class";
import { GeoDataProvider } from "./geo-data-provider.interface";
import { MapOverlayGroupID } from "./map-overlay-group-id.enum";

/**
 * The ApiObject type that represents a Map Overlay
 */
export class MapOverlay extends ApiObject {

    type = ApiObjectType.MapOverlay

    /**
     * The id of the overlay
     */
    public readonly id: number;

    /**
     * The name of the overlay
     */
    public readonly name: string;

    /**
     * The groupId of the overlay
     */
    public readonly groupId: MapOverlayGroupID;

    /**
     * The color of the overlay as a html color
     */
    public readonly color: string;

    /**
     * The actual data of the overlay
     */
    private readonly data$: BehaviorSubject<ApiGeoData | null> = new BehaviorSubject<ApiGeoData | null>(null);

    /**
     * The external observable for the data of the overlay
     */
    public readonly data: Observable<ApiGeoData> = this.data$.asObservable().pipe(filter(value => value !== null));

    /**
     * weather or not load has already been called
     */
    private loadCalled = false;

    /**
     * The loader for the geodata
     */
    private loader: GeoDataProvider | null = null;

    /**
     * Creates a new MapOverlay object from an object
     * @param from - the object to create the overlay from
     */
    constructor(from: object) {

        super();
        this.id = getPropertyValue("ID", from, "number");
        this.name = getPropertyValue("name", from, "string");
        this.groupId = getPropertyValue("MapOverlayGroupID", from, "number");
        this.color = getPropertyValue("color", from, "string");

    }

    /**
     * Sets the loader for the object
     * @param loader - the loader
     */
    public setLoader(loader: GeoDataProvider) {

        this.loader = loader;

    }

    /**
     * Loads the data of the overlay if it hasn't already been loaded and this.loader is set
     */
    public loadData(): void {

        if (!this.loadCalled && this.loader !== null) {
            this.loadCalled = true;
            this.loader.getMapOverlayGeodata(this.id).then(
                (apiResponse) => {
                    this.data$.next(apiResponse.data as ApiGeoData);
                }
            );
        }

    }

}
