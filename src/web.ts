import { WebPlugin } from '@capacitor/core';

import type { LocationPlugin } from './definitions';

export class LocationWeb extends WebPlugin implements LocationPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
