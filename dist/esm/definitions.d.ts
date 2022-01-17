import type { ListenerCallback, PluginListenerHandle } from '@capacitor/core';
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
/**
 * Interface that represents an object that is reponsible for setting
 * up the plugin.
 */
export interface IInitOptions {
    /**
     * Defines a value in seconds that represents the interval that the
     * `location` event will be called with the user's current location.
     */
    interval: number;
}
export interface LocationPlugin {
    /**
     * Method that needs to be called before the plugin starts.
     */
    init(options: IInitOptions): Promise<void>;
    /**
     * Method that add some listener to listen for an event.
     *
     * @param eventName defines the event name.
     * @param listenerFunc defines the callback that will be called when
     * the event be fired.
     */
    addListener(eventName: string, listenerFunc: ListenerCallback): Promise<PluginListenerHandle>;
    /**
     * Method that add some listener to listen for an event.
     *
     * @param eventName defines the event name.
     * @param listenerFunc defines the callback that will be called when
     * the event be fired.
     */
    addListener(eventName: 'location', listenerFunc: (location: ILocation) => void): Promise<PluginListenerHandle>;
}
