package tk.imdt.aqi;

import tk.imdt.aqi.datastruct.MapArea;
import tk.imdt.aqi.datastruct.Position;

public class DataStorage {
	public static final MapArea beijing = new MapArea(new Position(39.7515451, 116.1160148), new Position(40.0915172, 116.6854968));
	public static final AqiData aqiData = new AqiData();
	public static final GoogleStaticMap staticMap = new GoogleStaticMap();
	public static final TimeTool timeTool = new TimeTool(3600);
	//public static final ScheduledTask task = new ScheduledTask();
}
