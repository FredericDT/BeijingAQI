# BeijingAQI

## Example
* [@BeijingAQI](https://t.me/BeijingAQI)

## dependency

* [JSON-java](https://github.com/stleary/JSON-java)

## Customize

* edit those fields at tk.imdt.aqi.CustomData

```
public static final String googleMapKey = "googleMapKey";
public static final String telegramBotToken = "telegramBotToken";
public static final String telegramChannel = "@BeijingAQI";
public static final String BOT_USERNAME = "@aBot";
```

* edit map area at tk.imdt.aqi.DataStorage

```
public static final MapArea beijing = new MapArea(new Position(39.7515451, 116.1160148), new Position(40.0915172, 116.6854968));
```


