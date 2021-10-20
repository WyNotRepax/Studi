const PROXY_CONFIG = [
    {
        context: ["/api/v1/obj/[A-Za-z]+"],
        target: "http://localhost:4200",
        pathRewrite: function (path, req) {
            return path.replace(/^\/api\/v1\/obj\//, "/assets/testdaten/Listing-").replace(/$/, ".json");
        },
        logLevel: "debug"
    },
    {
        context: ["/api/v1/obj/MapOverlay/[0-9]+/geodata"],
        target: "http://localhost:4200",
        pathRewrite: function (path, req) {

            return path.replace(/\/api\/v1\/obj\/MapOverlay\//, "/assets/testdaten/MapOverlay/geodata-").replace(/\/geodata$/, ".json");
        },
        logLevel: "debug"
    }
];

module.exports = PROXY_CONFIG;
