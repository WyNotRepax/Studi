import { Injectable } from "@angular/core";
import { ConfigData } from "@service/configuration/config-data.class";

/**
 * Service to load configuration files
 */
@Injectable({
  providedIn: "root"
})
export class ConfigurationService {

  /**
   * Default constructor
   */
  constructor() { }

  /**
   * Loads the config file in the assets/config folder
   * 
   * @returns - rejected or resolved promise with the loaded config-data
   */
  public async loadControlConfig(): Promise<ConfigData> {

    return new Promise((resolve, reject) => {
      fetch("assets/config/config.json").then(
        result => result.json().then(
          data => {
            let configData = data;
            if (configData === undefined) {
              reject();
            }
            else {
              resolve(configData);
            }
          }
        )
      );
    });

  }

}