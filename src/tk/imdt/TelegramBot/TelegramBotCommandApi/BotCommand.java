package tk.imdt.TelegramBot.TelegramBotCommandApi;

import tk.imdt.TelegramBot.TelegramDataStruct.Message;
import tk.imdt.aqi.CustomData;

public abstract class BotCommand {
	public String commandName = "";
	public Message element = null;
	public BotCommand(String commandName, Message element) {
		this.commandName = commandName;
		this.element = element;
		if (element.getText()[0].equalsIgnoreCase("/"+ this.commandName + CustomData.BOT_USERNAME) || element.getText()[0].equalsIgnoreCase("/" + this.commandName)) {
			function();
		}
	}
	protected abstract void function();
}
