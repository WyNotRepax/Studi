import * as THREE from "three";

export class Earth {

    private meshGroup:THREE.Group;
    private surfaceMesh:THREE.Mesh;
    private skyMesh:THREE.Mesh;

    constructor(surfaceMesh:THREE.Mesh, skyMesh:THREE.Mesh) {

        this.meshGroup = new THREE.Group;
        this.surfaceMesh = surfaceMesh;
        this.skyMesh = skyMesh;

        this.generateGroup();

    }

    private generateGroup(): void {

        this.meshGroup.add(this.surfaceMesh, this.skyMesh);

    }

    public getGetMeshGroup(): THREE.Group {

        return this.meshGroup;

    }

    public getSurfaceMesh(): THREE.Mesh {

        return this.surfaceMesh;

    }

    public getSkyMesh(): THREE.Mesh {

        return this.skyMesh;

    }

    //UPDATE FUNCTION IMPL HERE IF NECESSARY!

}
