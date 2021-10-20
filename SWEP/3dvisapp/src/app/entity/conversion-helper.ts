/**
 * @ignore
 */
function propertyWrongType(propertyName: string, type: string, expectedType: "string" | "number" | "boolean" | "object" | "array"): void {
    throw `Property ${propertyName} has wrong type. Expected "${expectedType}". Found "${type}"`;
}

/**
 * @ignore
 */
export function badEnumValue(propertyName: string, value: string, enumValues: string[]): void {
    throw `Property ${propertyName} has wrong enum type. Expected one of :"${enumValues.join(",")}". Found "${value}"`;
}

/**
 * @ignore
 */
export function getPropertyValue(propertyName: string, from: object, type: "string", optional?: boolean): string | null;
/**
 * @ignore
 */
export function getPropertyValue(propertyName: string, from: object, type: "number", optional?: boolean): number | null;
/**
 * @ignore
 */
export function getPropertyValue(propertyName: string, from: object, type: "boolean", optional?: boolean): boolean | null;
/**
 * @ignore
 */
export function getPropertyValue(propertyName: string, from: object, type: "object", optional?: boolean): object | null;
/**
 * @ignore
 */
export function getPropertyValue(propertyName: string, from: object, type: "array", optional?: boolean): Array<any> | null;
/**
 * @ignore
 */
export function getPropertyValue(propertyName: string, from: object, type: "string" | "number" | "boolean" | "object" | "array", optional: boolean = false): unknown {
    let value = from[propertyName];
    if (value === undefined && optional) {
        return null;
    }
    if (value !== null && (value === undefined || (typeof value !== type && !(typeof value === "object" && type === "array")))) {
        propertyWrongType(propertyName, typeof value, type);
    }
    switch (type) {
        case "string":
            return value as string;
        case "number":
            return value as number;
        case "boolean":
            return value as boolean;
        case "object":
            return value as object;
        case "array":
            return value as Array<any>;
        default:
            throw "Unreachable block reached";
    }
}

/**
 * @ignore
 */
export function getEnum<T>(propertyName: string, from: object, e: { [s: string]: T }): T {
    return parseEnum(propertyName, getPropertyValue(propertyName, from, "string"), e);
}
/**
 * @ignore
 */
export function parseEnum<T>(propertyName: string, value: string, e: { [s: string]: T }): T {
    for (let enumValue of Object.values(e)) {
        if (value === enumValue.toString()) {
            return enumValue as T;
        }
    }
    badEnumValue(propertyName, value, Object.values(e).map(v => v.toString()));
}

/**
 * @ignore
 */
export function getGPS(propertyName: string, from: object): number;
/**
 * @ignore
 */
export function getGPS(propertyName: string, from: object, optional: false): number;
/**
 * @ignore
 */
export function getGPS(propertyName: string, from: object, optional: true): number | undefined;
/**
 * @ignore
 */
export function getGPS(propertyName: string, from: object, optional: boolean = false): number | undefined {
    let value = getPropertyValue(propertyName, from, "string", optional);
    if (value === null) {
        return undefined;
    }
    let result = parseFloat(value);
    if (isNaN(result)) {
        throw `Could not parse property ${propertyName}:${value} parses to ${result}`;
    }
    return result;
}

/**
 * @ignore
 */
export function getColor(propertyName: string, from: object) {
    return parseInt(getPropertyValue(propertyName, from, "string"), 16);
}