package tk.imdt.aqi;

import tk.imdt.TelegramBot.TelegramBotBody.TelegramBot;

public class ScheduledTask extends Thread{
	@Override
	public void run() {
		//Logger.log(DataStorage.timeTool.nextTime + "");
		Main.sendAqi();
		//bot.getUpdates();
	}
}
