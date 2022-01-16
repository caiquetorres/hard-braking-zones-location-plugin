import { WebPlugin } from '@capacitor/core';

import type { LocationPlugin } from './definitions';

export class LocationWeb extends WebPlugin implements LocationPlugin {
  /**
   * @inheritdoc
   */
  async init(): Promise<void> {
    throw new Error('Method not implemented.');
  }
}
