package tk.imdt.TelegramBot.TelegramDataStruct;

import org.json.JSONObject;

public class GetUpdatesElement {
	public Updates update = null;
	protected JSONObject obj = null;
	public GetUpdatesElement(JSONObject obj) {
		this.obj = obj;
		if (this.obj.has("message")) {
			this.update = new Message(this.obj.getJSONObject("message"));
		} else if (this.obj.has("inline_query")) {
			this.update = new InlineQuery(this.obj);
		}
		
	}
	public long getOffset() {
		return this.obj.getLong("update_id");
	}

}
