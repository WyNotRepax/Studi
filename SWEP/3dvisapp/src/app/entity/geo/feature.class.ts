import { getEnum, getPropertyValue } from "../conversion-helper";
import { GeometryType } from "./geometry-type.enum";
import { Geometry } from "./gometry.class";
import { MultiPolygon } from "./multi-polygon.class";
import { Polygon } from "./polygon.class";

/**
 * The class that represents a Feature
 */
export class Feature {
    /**
     * @ignore
     */
    public readonly type: "Feature" = "Feature";

    /**
     * The Geometry of this Feature
     */
    public readonly geometry: Geometry;

    /**
     * Constructs a new Feature object from an object
     * @param from - the object to create this Feature object from
     */
    constructor(from: object) {

        this.geometry = object2Geometry(getPropertyValue("geometry", from, "object"));

    }

}

/**
 * Creates a Geomtery from an object
 * @param from - the object to create the Geometry from
 * @returns - the Geometry representing the object
 */
function object2Geometry(from: object): Geometry {

    let type = getEnum("type", from, GeometryType);
    switch (type) {
        case GeometryType.Polygon:
            return new Polygon(from);
        case GeometryType.MultiPolygon:
            return new MultiPolygon(from);
    }

}