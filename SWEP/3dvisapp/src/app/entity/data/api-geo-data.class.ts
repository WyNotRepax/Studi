
import { getEnum, getPropertyValue } from "../conversion-helper";
import { FeatureCollection } from "../geo/feature-collection.class";
import { GeoDataType } from "../geo/geo-data-type.enum";
import { GeoData } from "../geo/geo-data.class";
import { ApiObjectType } from "./api-object-type.enum";
import { ApiObject } from "./api-object.class";

/**
 * The ApiObject type that represents Geo Data
 */
export class ApiGeoData extends ApiObject {

    type = ApiObjectType.GeoData;

    /**
     * The Geodata
     */
    public readonly geodata: GeoData;

    /**
     * Constructs a new ApiGeoData object from an object
     * @param from - the object to create the GeoData object from
     */
    constructor(from: object) {
        super();
        let geodata_raw = getPropertyValue("geodata", from, "string");
        this.geodata = object2GeoData(JSON.parse(geodata_raw));
    }
}

/**
 * Converts an object to a GeoData object
 * 
 * @param from 
 * @returns 
 */
function object2GeoData(from: object): GeoData {

    let type = getEnum("type", from, GeoDataType);
    switch (type) {
        case GeoDataType.FeatureCollection:
            return new FeatureCollection(from);
    }

}