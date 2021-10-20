import { getPropertyValue } from "../conversion-helper";
import { GeometryType } from "./geometry-type.enum";
import { Geometry } from "./gometry.class";
import { Polygon } from "./polygon.class";

/**
 * The Geometry type that represents a MultiPolygon
 */
export class MultiPolygon extends Geometry {

    type = GeometryType.MultiPolygon;

    /**
     * The Polygons of this Multipolygon
     */
    polygons: Polygon[];

    /**
     * Consturcts a MultiPolygon object from an object
     * @param from - the object to construct this MultiPolygon object from
     */
    constructor(from: object) {

        super();
        this.polygons = (getPropertyValue("coordinates", from, "array")).map(coordinates => new Polygon({ type: "Polygon", coordinates: coordinates }));

    }

}