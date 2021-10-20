import { ApiObjectType } from "./api-object-type.enum";


/**
 * Represents a Unique Identifier for an object
 */
export class Uid {

    /**
     * Constructs a new Uid from a type and an id
     * @param type - the type of the object
     * @param id - the id of the object
     */
    constructor(
        public readonly type: ApiObjectType,
        public readonly id: number
    ) { }

    /**
     * Checks weather or not to Uids are value equal
     * @param uid1 - the first Uid
     * @param uid2 - the second Uid
     * @returns - true if the Uids are equal false otherwise
     */
    public static equal(uid1: Uid | null, uid2: Uid | null) {

        if (uid1 === null || uid2 === null) {
            return uid2 === null && uid1 === null;
        }
        return uid1.type === uid2.type && uid1.id === uid2.id;

    }

    /**
     * Checks if this Uid is equal to a given Uid
     * @param uid - the othe Uid
     * @returns - true if this Uid is equal the given Uid, false otherwise
     */
    public equals(uid: Uid) {

        return Uid.equal(this, uid);

    }

}

