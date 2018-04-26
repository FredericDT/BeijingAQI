package tk.imdt.aqi.datastruct;

import java.util.Map;

import org.json.JSONObject;

public class AqiDataNode {
	public final Map<?, ?> dataMap;
	public AqiDataNode(JSONObject obj) {
		this.dataMap = obj.toMap();
	}
	
	public double getLat() {
		return (double) this.dataMap.get("lat");
	}
	public double getLon() {
		return (double) this.dataMap.get("lon");
	}
	public String getAqi() {
		return (String) this.dataMap.get("aqi");
	}
	public String getUTime() {
		return (String) this.dataMap.get("utime");
	}
	public String getCity() {
		return (String) this.dataMap.get("city");
	}
}
