package tk.imdt.aqi;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import tk.imdt.aqi.datastruct.AqiDataNode;
import tk.imdt.aqi.utils.Logger;

public class AqiData {
	private final SSLHttp httputil = new SSLHttp(CustomData.getAqiQueryUrl(DataStorage.beijing));
	private JSONArray originalData = null;
	public List<AqiDataNode> data = new ArrayList<>();
	public AqiData updateData() {
		this.originalData = null;
		this.data.clear();
		try {
			this.originalData = new JSONArray(this.httputil.get(""));
			for (Object perObj : this.originalData) {
				if (perObj instanceof JSONObject) {
					this.data.add(new AqiDataNode((JSONObject) perObj));
				}
			}
		} catch (Exception e) {
			Logger.log("Error when update original data");
			e.printStackTrace();
		}
		return this;
	}
}
