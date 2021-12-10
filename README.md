# @hard-braking-zones/location

Project developed for monitoring hard braking zones as a scientific initiation at Facens in the period 2021/2022.

## Install

```bash
npm install @hard-braking-zones/location
npx cap sync
```

## API

<docgen-index>

* [`getLocation()`](#getlocation)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### getLocation()

```typescript
getLocation() => any
```

Method that gets the current device location.

**Returns:** <code>any</code>

--------------------


### Interfaces


#### ILocation

Interface that represents the device location data.

| Prop            | Type                |
| --------------- | ------------------- |
| **`deviceId`**  | <code>string</code> |
| **`accuracy`**  | <code>number</code> |
| **`longitude`** | <code>string</code> |
| **`latitude`**  | <code>string</code> |
| **`speed`**     | <code>number</code> |

</docgen-api>
