/**
 * Interface that represents the device location data.
 */
export interface ILocation {
    deviceId: string;
    accuracy: number;
    longitude: string;
    latitude: string;
    speed: number;
}
export interface LocationPlugin {
    /**
     * Method that gets the current device location.
     *
     * @returns an object that represents the location data.
     */
    getLocation(): Promise<ILocation>;
}
