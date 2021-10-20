
import { getGPS, getPropertyValue } from "../conversion-helper";
import { ApiObjectType } from "./api-object-type.enum";
import { ApiObject } from "./api-object.class";
import { HasUid } from "./has-uid.interface";
import { Uid } from "./uid.class";

/**
 * The ApiObject type that represents a Satellite
 */
export class Satellite extends ApiObject implements HasUid {

    public readonly type = ApiObjectType.Satellite;

    /**
     * The id of the satellite
     */
    public readonly id: number;

    /**
     * The name of the satellite
     */
    public readonly name: string;

    /**
     * The longitude of the satellite
     */
    public readonly longitude: number;

    /**
     * Constructs a Satellite object from an object
     * @param from - the object to create the Satellite object from
     */
    constructor(from: object) {
        super();
        this.id = getPropertyValue("ID", from, "number");
        this.name = getPropertyValue("name", from, "string");
        this.longitude = getGPS("longitude", from);
    }

    /**
     * Getter for the Uid
     * @returns - the Uid of the Satellite
     */
    public getUid(): Uid {
        return new Uid(this.type, this.id);
    }

}
