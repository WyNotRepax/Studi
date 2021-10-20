import { ApiObjectType } from "./api-object-type.enum";

/**
 * The abstract supertype for all api data
 */
export abstract class ApiObject {

    /**
     * The type of ApiObject this is
     */
    public readonly type: ApiObjectType

}
