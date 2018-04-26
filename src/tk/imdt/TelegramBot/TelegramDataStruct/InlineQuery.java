package tk.imdt.TelegramBot.TelegramDataStruct;

import org.json.JSONObject;

public class InlineQuery extends Updates {
	/*
	 * {"update_id":384756567,
	 * "inline_query":
	 * 	{"id":"207070175064225441",
	 * 	"from":{"id":48212282,
	 * 	"first_name":"FredericDT",
	 * 	"username":"FredericDT"},
	 * "query":"h",
	 * "offset":""}
	 * }
	 */
	public long update_id = -1;
	public TelegramUser from = null;
	public String id = "";
	public String query = "";
	public String offset = "";
	
	public InlineQuery(JSONObject obj) {
		if(obj.has("inline_query")) {
			this.update_id = obj.getLong("update_id");
			this.from = new TelegramUser(obj.getJSONObject("inline_query").getJSONObject("from"));
			this.id = obj.getJSONObject("inline_query").getString("id");
			this.query = obj.getJSONObject("inline_query").getString("query");
			this.offset = obj.getJSONObject("inline_query").getString("offset");
		}
	}

}
