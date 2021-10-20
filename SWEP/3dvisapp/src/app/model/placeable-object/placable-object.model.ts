import { ObjectDataItemType as ObjectDataType } from "@service/object-data/object-data.interface";
import * as THREE from "three";

export class PlacableObject {

    private type: ObjectDataType;
    private lat: number;
    private lon: number;
    private alt: number;
    private description: string;
    private mesh: THREE.Mesh;

    constructor(type: ObjectDataType, lat: number, lon: number, alt: number, description: string, mesh: THREE.Mesh) {

        this.type = type;
        this.lat = lat;
        this.lon = lon;
        this.alt = alt;
        this.description = description;
        this.mesh = mesh;

    }

    public getMesh(): THREE.Mesh {

        return this.mesh;

    }

    public getType(): ObjectDataType {

        return this.type;

    }

    public getDescription(): string {

        return this.description;

    }

    public getCoords(): THREE.Vector3 {

        return new THREE.Vector3(this.lat, this.lon, this.alt);

    }

}
