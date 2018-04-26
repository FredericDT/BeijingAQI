package tk.imdt.TelegramBot.ReplyDataStruct.InlineQueryResult;

import org.json.JSONObject;

import tk.imdt.TelegramBot.ReplyDataStruct.InputMessageContent.InputMessageContent;

public class InlineQueryResultArticle extends InlineQueryResult {
	public String title = "";
	public String description = "";
	public InputMessageContent inputMessageContent = null;
	public InlineQueryResultArticle() {
		super("article");
	}
	@Override
	public JSONObject toJSONObject() {
		return super.toJSONObject().put("description", this.description).put("title", this.title).put("input_message_content", this.inputMessageContent.toJSONObject());
	}
	
}
