import { GeoDataType } from "./geo-data-type.enum";

/**
 * The abstract superclass for all GeoData
 */
export abstract class GeoData {

    /**
     * The type of GeoData
     */
    public readonly type: GeoDataType

}