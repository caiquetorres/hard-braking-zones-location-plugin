import { WebPlugin } from '@capacitor/core';
import type { ILocation } from '.';
import type { LocationPlugin } from './definitions';
export declare class LocationWeb extends WebPlugin implements LocationPlugin {
    /**
     * @inheritdoc
     */
    getLocation(): Promise<ILocation>;
}
