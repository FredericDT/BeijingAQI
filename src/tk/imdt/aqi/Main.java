package tk.imdt.aqi;

import java.util.Scanner;

import tk.imdt.TelegramBot.TelegramBotBody.TelegramBot;
import tk.imdt.aqi.utils.Logger;
import tk.imdt.aqi.utils.TelegramMessageMaker;

public class Main {
	public static boolean isUpdating = false;
	public static TelegramBot bot = new TelegramBot();
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		boolean quit = false;
		DataStorage.timeTool.start();
		Logger.log("input \"q\" for stop");
		while (!quit) {
			if (new Scanner(System.in).next().equals("q")) {
				quit = true;
			}
		}
		DataStorage.timeTool.stop();
	}
	public static void sendAqi() {
		if (!isUpdating) {
			isUpdating = true;
			sendFunction();
		}
	}
	protected static void sendFunction() {
		try {
			DataStorage.aqiData.updateData();
			bot.sendMessage(TelegramMessageMaker.makeMessage(), CustomData.telegramChannel);
			bot.sendPhoto(DataStorage.staticMap.getCurrentMap(), CustomData.telegramChannel);
			Logger.log("message sent");
			isUpdating = false;
		} catch (Exception e) {
			Logger.log("Send Aqi Thread Error");
			e.printStackTrace();
			sendFunction();
		}
	}
}
