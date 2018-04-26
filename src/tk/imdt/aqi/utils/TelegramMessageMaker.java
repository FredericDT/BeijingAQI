package tk.imdt.aqi.utils;

import tk.imdt.aqi.DataStorage;
import tk.imdt.aqi.datastruct.AqiDataNode;

public class TelegramMessageMaker {
	//public static String collectorMessage = "";
	public static String makeMessage() {
		String collectorMessage = "";
		collectorMessage += "*Update Time : *" + DataStorage.aqiData.data.get(0).getUTime() + "\n";
		collectorMessage += "*AQI Data : * \n";
		for (AqiDataNode node : DataStorage.aqiData.data) {
			collectorMessage += "#" + node.getCity() + " : _" + node.getAqi() + "_\n"; 
		}
		//collectorMessage += "[MAP](" + ShortenUrl.shortUrl(DataStorage.staticMap.getCurrentMap()) + ")";
		return collectorMessage;
	}
}
