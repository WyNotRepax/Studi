import { Uid } from "./uid.class";

/**
 * Interface for elements with a Uid
 */
export interface HasUid {

    /**
     * Gets the uid of the object
     */
    getUid(): Uid;

}
