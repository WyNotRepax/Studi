import { ApiResponse } from "./api-response.class";

/**
 * interface for loading MapOverlayData
 */
export interface GeoDataProvider {

    /**
     * A Promise of the map overlay data to be loaded
     * @param id - The id of the object to get the map overlay geodata for
     */
    getMapOverlayGeodata(id: number): Promise<ApiResponse>;

}