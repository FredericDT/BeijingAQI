package tk.imdt.aqi;

import tk.imdt.aqi.datastruct.MapArea;

public class CustomData {
	
	public static final String googleMapKey = "googleMapKey";
	public static final String telegramBotToken = "telegramBotToken";
	public static final String telegramChannel = "@BeijingAQI";
	public static final String BOT_USERNAME = "@aBot";
	public static final String aqiQueryUrl = "https://wind.waqi.info/mapq/bounds/?bounds=";
	
	public static String getAqiQueryUrl(MapArea mapArea) {
		return aqiQueryUrl + mapArea.toString();
	}
}
