import { getPropertyValue, getEnum } from "../conversion-helper";
import { ApiGeoData } from "./api-geo-data.class";
import { ApiObjectType } from "./api-object-type.enum";
import { ApiObject } from "./api-object.class";
import { MapOverlay } from "./map-overlay.class";
import { MetaSingle, MetaListing } from "./meta.class";
import { Satellite } from "./satellite.class";
import { TerminalLiveData } from "./terminal-live-data.class";
import { Terminal } from "./terminal.class";

/**
 * Class Representing a Response from the Api
 */
export class ApiResponse {

    /**
     * The metadata of this response
     */
    public meta: MetaSingle | MetaListing;

    /**
     * The data of this response
     */
    public data: Array<ApiObject> | ApiObject;

    /**
     * The errors of this response
     */
    public errors: Array<any>;

    /**
     * Create a response from an object
     * @param from - the object to create the response from
     * @param isGeoData - weather or not the response is a Geodate response
     */
    constructor(from: object, isGeoData: boolean = false) {

        this.initMeta(from, isGeoData);
        this.initData(from, isGeoData);
        this.errors = getPropertyValue("errors", from, "array");

    }

    /**
     * Initializes the metadata for a response from an object
     * 
     * @param from - the object to initialize the metadata from
     * @param isGeoData - weather or not the response is a Geodate response
     */
    private initMeta(from: object, isGeoData: boolean): void {

        let meta = getPropertyValue("meta", from, "object");
        if (isGeoData) {
            this.meta = new MetaSingle(meta);
            return;
        }
        let isListing: boolean = getPropertyValue("listing", meta, "boolean");
        if (isListing) {
            this.meta = new MetaListing(meta);
        } else {
            this.meta = new MetaSingle(meta);
        }

    }

    /**
     * Initializes the data for a response from an object
     * 
     * @param from - the object to initialize the data from
     * @param isGeoData - weather or not the response is a Geodate response
     */
    private initData(from: object, isGeoData: boolean): void {

        if (this.meta.listing) {
            let data = getPropertyValue("data", from, "array");
            this.data = data.map(value => object2ApiObject(value, isGeoData));
        } else {
            let data = getPropertyValue("data", from, "object");
            this.data = object2ApiObject(data, isGeoData);
        }

    }

}

/**
 * Converts an object to the corresponding subclass of ApiObject
 * 
 * @param from - the object to create the ApiObject from
 * @param isGeoData - weather or not the response is a Geodate response 
 * @returns - the ApiObject representing the object
 */
function object2ApiObject(from: object, isGeoData: boolean = false): ApiObject {

    if (isGeoData) {
        return new ApiGeoData(from);
    }
    let type = getEnum("_type", from, ApiObjectType);
    switch (type) {
        case ApiObjectType.Terminal:
            return new Terminal(from);
        case ApiObjectType.TerminalLiveData:
            return new TerminalLiveData(from);
        case ApiObjectType.Satellite:
            return new Satellite(from);
        case ApiObjectType.MapOverlay:
            return new MapOverlay(from);
    }

}
