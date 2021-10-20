import { getPropertyValue } from "../conversion-helper";
/**
 * Represents the metadata for a response with a single data object
 */
export class MetaSingle {

    /**
     * Weather or not this is a listing
     */
    listing = false;

    /**
     * The status of the response
     */
    status: number;

    /**
     * Creates a MetaSingle object from an object
     * @param from - the object to create the MetaSingle object from
     */
    constructor(from: object) {
        this.status = getPropertyValue("status", from, "number");
    }
}

/**
 * Represents the metadata for a response with a list of data objects
 */
export class MetaListing {

    /**
     * Weather or not this is a listing
     */
    listing = true;

    /**
     * The Status code of the response
     */
    status: number;

    /**
     * The count of items in the response
     */
    count: number;

    /**
     * The count of total items available
     */
    countTotal: number;

    /**
     * The pageSize of the response
     */
    pageSize: number;

    /**
     * The index of the page of the response
     */
    page: number;

    /**
     * A list of tokens the response data is sorted by
     */
    sort: string[];

    /**
     * Creates a MetaListing object from an object
     * @param from - the object to create the MetaListing object from
     */
    constructor(from: object) {
        this.status = getPropertyValue("status", from, "number");
        this.count = getPropertyValue("count", from, "number");
        this.countTotal = getPropertyValue("countTotal", from, "number");
        this.pageSize = getPropertyValue("pageSize", from, "number");
        this.page = getPropertyValue("page", from, "number");
        this.sort = getPropertyValue("sort", from, "array");
    }

}