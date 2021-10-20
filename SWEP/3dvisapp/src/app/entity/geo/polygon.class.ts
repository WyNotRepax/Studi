import { getPropertyValue } from "../conversion-helper";
import { Coordinate } from "./coordinate.class";
import { GeometryType } from "./geometry-type.enum";
import { Geometry } from "./gometry.class";

/**
 * The Geometry type that represents a Polygon
 */
export class Polygon extends Geometry {
    type = GeometryType.Polygon;

    /**
     * The coordinates of the border of this Polygon
     */
    public readonly coordinates: Coordinate[];

    /**
     * The coordinates of the holes in this Polygon
     */
    public readonly holes: Coordinate[];


    /**
     * Constructs a Polygon object from an object
     * @param from - the object to construct the Polygon object from
     */
    constructor(from: object) {
        super();
        let [coordinates, holes] = getPropertyValue("coordinates", from, "array");
        this.coordinates = coordinates.map(coordinate => new Coordinate(coordinate));
        if (holes) {
            this.holes = holes.map(coordinate => new Coordinate(coordinate));
        }
        else {
            this.holes = Array<Coordinate>();
        }
    }

}