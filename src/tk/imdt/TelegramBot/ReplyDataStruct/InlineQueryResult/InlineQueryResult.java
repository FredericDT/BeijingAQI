package tk.imdt.TelegramBot.ReplyDataStruct.InlineQueryResult;

import org.json.JSONObject;

import tk.imdt.Utils.GenerateToken;

public abstract class InlineQueryResult {
	public String type = "";
	public String id = GenerateToken.RandomString(10);
	public InlineQueryResult(String type) {
		this.type = type;
	}
	public JSONObject toJSONObject() {
		return new JSONObject().put("type", this.type).put("id", this.id);
	}
}
