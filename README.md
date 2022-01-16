# @hard-braking-zones/location

Project developed for monitoring hard braking zones as a scientific initiation at Facens in the period 2021/2022.

## Install

```bash
npm install @hard-braking-zones/location
npx cap sync
```

## API

<docgen-index>

* [`init()`](#init)
* [`addListener(...)`](#addlistener)
* [`addListener(...)`](#addlistener)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### init()

```typescript
init() => any
```

Method that needs to be called before the plugin starts.

**Returns:** <code>any</code>

--------------------


### addListener(...)

```typescript
addListener(eventName: string, listenerFunc: ListenerCallback) => any
```

Method that add some listener to listen for an event.

| Param              | Type                                            | Description                                                       |
| ------------------ | ----------------------------------------------- | ----------------------------------------------------------------- |
| **`eventName`**    | <code>string</code>                             | defines the event name.                                           |
| **`listenerFunc`** | <code>(err: any, ...args: {}) =&gt; void</code> | defines the callback that will be called when the event be fired. |

**Returns:** <code>any</code>

--------------------


### addListener(...)

```typescript
addListener(eventName: 'location', listenerFunc: (location: ILocation) => void) => any
```

Method that add some listener to listen for an event.

| Param              | Type                                                                   | Description                                                       |
| ------------------ | ---------------------------------------------------------------------- | ----------------------------------------------------------------- |
| **`eventName`**    | <code>"location"</code>                                                | defines the event name.                                           |
| **`listenerFunc`** | <code>(location: <a href="#ilocation">ILocation</a>) =&gt; void</code> | defines the callback that will be called when the event be fired. |

**Returns:** <code>any</code>

--------------------


### Interfaces


#### PluginListenerHandle

| Prop         | Type                      |
| ------------ | ------------------------- |
| **`remove`** | <code>() =&gt; any</code> |


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
