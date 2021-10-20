import { TestBed } from "@angular/core/testing";

import { MeshControllerService } from "@service/mesh-controller/mesh-controller.service";

describe("MeshControllerService", () => {
  let service: MeshControllerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MeshControllerService);
  });

  it("should be created", () => {
    expect(service).toBeTruthy();
  });
});
