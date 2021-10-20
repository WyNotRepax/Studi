import { TestBed } from "@angular/core/testing";

import { BasicObjectService } from "../basic-object/basic-object.service";

describe("BasicObjectService", () => {
  let service: BasicObjectService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BasicObjectService);
  });

  it("should be created", () => {
    expect(service).toBeTruthy();
  });
});
