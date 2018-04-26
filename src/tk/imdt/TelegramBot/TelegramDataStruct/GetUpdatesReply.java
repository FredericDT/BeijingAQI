package tk.imdt.TelegramBot.TelegramDataStruct;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class GetUpdatesReply {
	private JSONObject obj = null;
	private JSONArray result = null;
	public List<GetUpdatesElement> reply = new ArrayList<>();
	private int resultSize = 0;

	public GetUpdatesReply(JSONObject obj) {
		this.obj = obj;
		if (isOK()) {
			this.result = this.obj.getJSONArray("result");
			this.resultSize = this.result.length();
		}
		for (int i = 0; i < this.resultSize; i++) {
			reply.add(new GetUpdatesElement(this.result.getJSONObject(i)));
		}
	}

	public boolean isOK() {
		return this.obj.has("ok");
	}
}
