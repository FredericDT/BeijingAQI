package tk.imdt.aqi;

import tk.imdt.aqi.datastruct.AqiDataNode;

public class GoogleStaticMap {
	private AqiData aqiData = DataStorage.aqiData;
	public static final String mapHeader = "https://maps.googleapis.com/maps/api/staticmap?size=600x600";
	public String getCurrentMap() {
		String result = "&key=" + CustomData.googleMapKey;
		for (AqiDataNode dataNode : aqiData.data) {
			result += new GoogleStaticMapMarker(dataNode).marker;
		}
		return mapHeader + result;
	}
}
