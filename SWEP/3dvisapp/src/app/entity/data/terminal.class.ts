import { getEnum, getPropertyValue } from "../conversion-helper";
import { ApiObjectType } from "./api-object-type.enum";
import { ApiObject } from "./api-object.class";
import { HasUid } from "./has-uid.interface";
import { MobilityType } from "./mobility-type.enum";
import { Uid } from "./uid.class";

/**
 * The ApiObject type that represents Terminal
 */
export class Terminal extends ApiObject implements HasUid {

    type = ApiObjectType.Terminal;

    /**
     * The id of the terminal
     */
    public readonly id: number;

    /**
     * The name of the terminal
     */
    public readonly name: string;

    /**
     * The mobility type if the object
     */
    public mobilityType: MobilityType;

    /**
     * Constructs a Terminal object from an object
     * @param from - the object to create the Terminal object from
     */
    constructor(from: object) {
        super();
        this.id = getPropertyValue("ID", from, "number");
        this.name = getPropertyValue("name", from, "string");
        this.mobilityType = getEnum("mobilityType", from, MobilityType);
    }

    /**
     * Getter for the Uid
     * @returns - The Uid of the terminal
     */
    getUid(): Uid {
        return new Uid(this.type, this.id);
    }

}
