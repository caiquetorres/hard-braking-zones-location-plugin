import { WebPlugin } from '@capacitor/core';

import type { ILocation } from '.';
import type { LocationPlugin } from './definitions';

export class LocationWeb extends WebPlugin implements LocationPlugin {
  /**
   * @inheritdoc
   */
  async getLocation(): Promise<ILocation> {
    throw new Error('Method not implemented.');
  }
}
