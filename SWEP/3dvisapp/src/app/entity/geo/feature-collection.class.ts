import { getPropertyValue } from "../conversion-helper";
import { Feature } from "./feature.class";
import { GeoDataType } from "./geo-data-type.enum";
import { GeoData } from "./geo-data.class";

/**
 * The GeoData type that represents a Feature collection
 */
export class FeatureCollection extends GeoData {

    type = GeoDataType.FeatureCollection;

    /**
     * The Features of this feature collection
     */
    public readonly features: Feature[];

    /**
     * Constructs a FeatureCollection object from an object
     * @param from - the object to create the FeatureCollection object from
     */
    constructor(from: object) {

        super();
        this.features = getPropertyValue("features", from, "array").map(obj => new Feature(obj));

    }

}