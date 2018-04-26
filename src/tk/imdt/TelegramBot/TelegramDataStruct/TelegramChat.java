package tk.imdt.TelegramBot.TelegramDataStruct;

import org.json.JSONObject;

public class TelegramChat {
	private JSONObject obj = null;

	public TelegramChat(JSONObject obj) {
		this.obj = obj;
	}

	public boolean isGroup() {
		return this.obj.getString("type").equals("group");
	}

	public long getId() {
		return this.obj.getLong("id");
	}
}
