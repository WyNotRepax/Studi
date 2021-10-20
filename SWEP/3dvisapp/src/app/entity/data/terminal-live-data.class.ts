import { getEnum, getGPS, getPropertyValue } from "../conversion-helper";
import { AggregatedStatus } from "./aggregated-status.enum";
import { ApiObjectType } from "./api-object-type.enum";
import { ApiObject } from "./api-object.class";
import { HasUid } from "./has-uid.interface";
import { Uid } from "./uid.class";

/**
 * The ApiObject type that represents TerminalLiveData
 */
export class TerminalLiveData extends ApiObject implements HasUid {

    public readonly type = ApiObjectType.TerminalLiveData;

    /**
     * The id of the terminal
     */
    public readonly id: number;

    /**
     * The aggregated status of the terminal
     */
    public readonly aggregatedStatus: AggregatedStatus;

    /**
     * The latitude of the terminal
     */
    public readonly latitude: number;

    /**
     * The longitude of the terminal
     */
    public readonly longitude: number;

    /**
     * The altitude of the object
     */
    public readonly altitude?: number;

    /**
     * Constructs a TerminalLiveData object from an object
     * @param from - the object to create the TerminalLiveData object from
     */
    constructor(from: object) {
        super();
        this.id = getPropertyValue("ID", from, "number");
        this.aggregatedStatus = getEnum("aggregatedStatus", from, AggregatedStatus);
        this.latitude = getGPS("latitude", from);
        this.longitude = getGPS("longitude", from);
        this.altitude = getGPS("altitude", from, true);
    }

    /**
     * 
     * @returns - the Uid of the object
     */
    getUid(): Uid {
        return new Uid(this.type, this.id);
    }

}

