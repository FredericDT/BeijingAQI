package tk.imdt.TelegramBot.ReplyDataStruct.InputMessageContent;

import org.json.JSONObject;

public class InputTextMessageContent extends InputMessageContent {
	public String messageText = "";
	public InputTextMessageContent(String messageText) {
		this.messageText = messageText;
	}
	@Override
	public JSONObject toJSONObject() {
		return new JSONObject().put("parse_mode", "MARKDOWN").put("message_text", this.messageText);
	}
}
