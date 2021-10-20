
/**
 * 2d Coordinate
 */
export class Coordinate {

    /**
     * The x coordinate
     */
    public readonly x: number

    /**
     * The y coordinate
     */
    public readonly y: number

    /**
     * Creates a Coordinates object from an array of two numbers
     * @param coordinates - the coordinates
     */
    constructor(coordinates: { 0: number, 1: number } | number[]) {

        if (typeof coordinates[0] !== "number" || typeof coordinates[1] !== "number") {
            throw `Expected { 0: number, 1: number } got { 0: ${typeof coordinates[0]}, 1: ${typeof coordinates[0]} }`;
        }
        this.x = coordinates[1];
        this.y = coordinates[0];

    }

}