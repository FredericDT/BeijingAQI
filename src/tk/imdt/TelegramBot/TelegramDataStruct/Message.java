package tk.imdt.TelegramBot.TelegramDataStruct;

import org.json.JSONArray;
import org.json.JSONObject;

public class Message extends Updates {
	private JSONObject obj = null;
	public TelegramUser telegramUser = null;
	public TelegramChat telegramChat = null;
	public Message replyToMessage = null;
	public TelegramUser forwardFrom = null;
	public Message(JSONObject obj) {
		this.obj = obj;
		if (this.obj.has("from")) {
			this.telegramUser = new TelegramUser(this.obj.getJSONObject("from"));
		}
		if (this.obj.has("chat")) {
			this.telegramChat = new TelegramChat(this.obj.getJSONObject("chat"));
		}
		if (this.obj.has("reply_to_message")) {
			this.replyToMessage = new Message(this.obj.getJSONObject("reply_to_message"));
		}
		if (this.obj.has("forward_from")) {
			this.forwardFrom = new TelegramUser(this.obj.getJSONObject("forward_from"));
		}
	}
	public long getMessageId() {
		return this.obj.getLong("message_id");
	}

	public String[] getText() {
		if (this.obj.has("text")) {
			return this.obj.getString("text").split(" ");
		}
		return null;
	}

	public boolean isCommand() {
		if (this.obj.has("entities")) {
			JSONArray entities = this.obj.getJSONArray("entities");
			for (int i = 0; i < entities.length(); i++) {
				JSONObject entity = entities.getJSONObject(i);
				if (entity.has("type")) {
					if (entity.getString("type").equals("bot_command")) {
						return true;
					}
				} else {
					return false;
				}
			}
		} else {
			return false;
		}
		return false;
	}

}
