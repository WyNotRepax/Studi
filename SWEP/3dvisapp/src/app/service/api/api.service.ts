import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { ApiResponse } from "src/app/entity/data/api-response.class";
import { GeoDataProvider } from "src/app/entity/data/geo-data-provider.interface";
import { MapOverlay } from "src/app/entity/data/map-overlay.class";

/**
 * Service to load data from the api-interface
 */
@Injectable({
  providedIn: "root"
})
export class ApiService implements GeoDataProvider {

  /**
   * Base URL of the API
   */
  public static readonly BASE_URL = "";//"/satconnect_demo_develop_backend";
  /**
   * API version number
   */
  public static readonly VERSION = "v1";
  /**
   * API endpoint for satellites
   */
  public static SATELLITE_ENDPOINT = "obj/ScSatellite";
  /**
   * API endpoint for terminals
   */
  public static TERMINAL_ENDPOINT = "obj/Terminal";
  /**
   * API endpoint for terminal live data
   */
  public static TERMINALLIVEDATA_ENDPOINT = "obj/TerminalLiveData";
  /**
   * API endpoint for the map ovelays
   */
  public static MAPOVERLAYDATA_ENDPOINT = "obj/MapOverlay"

  /**
   * Constructor injects Http client
   * 
   * @param http - injected http client
   */
  constructor(private http: HttpClient) {}

  /**
   * Loads data for satellite objects from the api-interface via HttpClient
   * 
   * @returns - rejected or resolved promise with the apiResponse
   */
  async getSatellites(): Promise<ApiResponse> {
    return new Promise<ApiResponse>((resolve, reject) => {
      this.http.get(`${ApiService.BASE_URL}/api/${ApiService.VERSION}/${ApiService.SATELLITE_ENDPOINT}`).toPromise()
        .then(object => {
          let apiResponse = new ApiResponse(object);
          if (apiResponse.meta.listing == false) {
            reject();
          } else {
            resolve(apiResponse);
          }
        })
        .catch(reason => reject(reason ?? "Unknown reason"));
    });
  }

  /**
   * Loads data for terminal objects from the api-interface via HttpClient
   * 
   * @returns - rejected or resolved promise with the apiResponse
   */
  async getTerminals(): Promise<ApiResponse> {
    return new Promise<ApiResponse>((resolve, reject) => {
      this.http.get(`${ApiService.BASE_URL}/api/${ApiService.VERSION}/${ApiService.TERMINAL_ENDPOINT}`).toPromise()
        .then(object => {
          let apiResponse = new ApiResponse(object);
          if (apiResponse.meta.listing == false) {
            reject();
          } else {
            resolve(apiResponse);
          }
        })
        .catch(reason => reject(reason ?? "Unknown reason"));
    });
  }

  /**
   * Loads terminal-live-data from the api-interface via HttpClient
   * 
   * @returns - rejected or resolved promise with the apiResponse
   */
  async getTerminalLiveData(): Promise<ApiResponse> {
    return new Promise<ApiResponse>((resolve, reject) => {
      this.http.get(`${ApiService.BASE_URL}/api/${ApiService.VERSION}/${ApiService.TERMINALLIVEDATA_ENDPOINT}`).toPromise()
        .then(object => {
          let apiResponse = new ApiResponse(object);
          if (apiResponse.meta.listing == false) {
            reject();
          } else {
            resolve(apiResponse);
          }
        })
        .catch(reason => reject(reason ?? "Unknown reason"));
    });
  }

  /**
   * Loads data for mapoverlays from the api-interface via HttpClient
   * 
   * @returns - rejected or resolved promise with the apiResponse
   */
  async getMapOverlayData(): Promise<ApiResponse> {
    return new Promise<ApiResponse>((resolve, reject) => {
      this.http.get(`${ApiService.BASE_URL}/api/${ApiService.VERSION}/${ApiService.MAPOVERLAYDATA_ENDPOINT}`).toPromise()
        .then(object => {
          let apiResponse = new ApiResponse(object);
          if (apiResponse.meta.listing == false) {
            reject();
          } else {
            (apiResponse.data as MapOverlay[]).forEach(mapOverlay => mapOverlay.setLoader(this));
            resolve(apiResponse);
          }
        })
        .catch(reason => reject(reason ?? "Unknown reason"));
    });
  }

  /**
   * Loads geo-data for mapoverlays from the api-interface via HttpClient
   * 
   * @returns - rejected or resolved promise with the apiResponse
   */
  async getMapOverlayGeodata(id: number): Promise<ApiResponse> {
    return new Promise<ApiResponse>((resolve, reject) => {
      this.http.get(`${ApiService.BASE_URL}/api/${ApiService.VERSION}/${ApiService.MAPOVERLAYDATA_ENDPOINT}/${id}/geodata`).toPromise()
        .then(object => {
          let apiResponse = new ApiResponse(object, true);
          if (apiResponse.meta.listing === true) {
            reject();
          } else {
            resolve(apiResponse);
          }
        })
        .catch(reason => reject(reason ?? "Unknown reason"));
    });
  }
}
