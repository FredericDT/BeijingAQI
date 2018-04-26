package tk.imdt.TelegramBot.TelegramDataStruct;

import org.json.JSONObject;

public class TelegramUser {
	private JSONObject obj = null;
	
	public TelegramUser(JSONObject obj) {
		this.obj = obj;
	}

	public long getId() {
		return this.obj.getLong("id");
	}

	public String getName() {
		return this.obj.getString("first_name");
	}
	public String getUsername() {
		if (this.obj.has("username")) {
			return this.obj.getString("username");
		} else {
			return "-NODATA-";
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof TelegramUser) {
			if (((TelegramUser) obj).getId() == this.getId()) {
				return true;
			}
		}
		return false;
	}
}
