package tk.imdt.TelegramBot.TelegramBotInlineQuery;

import tk.imdt.TelegramBot.TelegramDataStruct.InlineQuery;

public abstract class InlineHandler {
	public InlineQuery element = null;
	public InlineHandler(InlineQuery element) {
		this.element = element;
		function();
	}
	protected abstract void function();
}
