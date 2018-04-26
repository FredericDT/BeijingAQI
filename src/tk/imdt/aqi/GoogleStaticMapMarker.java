package tk.imdt.aqi;

import tk.imdt.aqi.datastruct.AqiDataNode;

public class GoogleStaticMapMarker {
	public String marker = "";
	//&markers=color:blue%7Clabel:S%7C40.702147,-74.015794
	public GoogleStaticMapMarker build(String color, String label, double lat, double lon) {
		this.marker += "&markers=";
		this.marker += "color:" + color;
		this.marker += "%7C";
		//this.marker += "label:" + label;
		//this.marker += "%7C";
		this.marker += lat + "," + lon;
		return this;
	}
	
	public GoogleStaticMapMarker(String aqiString, double lat, double lon) {
		if (!aqiString.equals("-")) {
			parseAqi(aqiString, lat, lon);
		} else {
			build("0xaaaaaa", aqiString + "", lat, lon);
		}
	}
	
	protected void parseAqi(String aqiString, double lat, double lon) {
		int aqi = Integer.parseInt(aqiString);
		if (aqi > 300) {
			build("0x7e0023", aqi + "", lat, lon);
		} else if (aqi > 200) {
			build("0x660099", aqi + "", lat, lon);
		} else if (aqi > 150) {
			build("0xcc0033", aqi + "", lat, lon);
		} else if (aqi > 100) {
			build("0xff9933", aqi + "", lat, lon);
		} else if (aqi > 50) {
			build("0xffde33", aqi + "", lat, lon);
		} else {
			build("0x009966", aqi + "", lat, lon);
		}
	}
	public GoogleStaticMapMarker(AqiDataNode aqi) {
		this(aqi.getAqi(), aqi.getLat(), aqi.getLon());
	}
}
