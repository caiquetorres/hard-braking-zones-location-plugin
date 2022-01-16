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
export interface LocationPlugin {
    /**
     * Method that needs to be called before the plugin starts.
     */
    init(): Promise<void>;
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
