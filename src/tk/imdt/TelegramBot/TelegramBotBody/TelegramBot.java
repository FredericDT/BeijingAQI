package tk.imdt.TelegramBot.TelegramBotBody;

import java.io.IOException;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;

import tk.imdt.TelegramBot.TelegramDataStruct.GetUpdatesElement;
import tk.imdt.TelegramBot.TelegramDataStruct.GetUpdatesReply;
import tk.imdt.TelegramBot.TelegramDataStruct.InlineQuery;
import tk.imdt.TelegramBot.TelegramDataStruct.Message;
import tk.imdt.Utils.HttpUtil;
import tk.imdt.Utils.HttpUtil.method;
import tk.imdt.aqi.CustomData;
import tk.imdt.aqi.utils.Logger;

public class TelegramBot {
	private GetUpdatesReply getupdates;
	public JSONObject reply;
	public static long offset = 0;

	// public TelegramBot() {}
	public void getUpdates() {
		try {
			postCallMethod("getUpdates", "offset=" + offset);
			this.getupdates = new GetUpdatesReply(this.reply);
			handleMessages();
		} catch (IOException e) {
		}
	}

	private void handleMessages() {

		for (GetUpdatesElement element : this.getupdates.reply) {
			if (element.update instanceof InlineQuery) {
				Logger.log("Inline find from: " + ((InlineQuery) element.update).from.getUsername());
				Logger.log("query: " + ((InlineQuery) element.update).query);
				//handle inline
			}
			//boolean successfullyInvited = false;
			//boolean successfullyRemoved = false;
			if (element.update instanceof Message && ((Message) element.update).isCommand()) {
				
				/*
				new BotInviteCommand(element);
				new BotRemoveCommand(element);
				new BotLinkCommand(element);
				new BotUpdateCommand(element);
				new BotWhoisCommand(element);
				*/
				/*
				if (element.getText()[0].equalsIgnoreCase("/invite" + BotConfig.BOT_USERNAME) || element.getText()[0].equalsIgnoreCase("/invite")) {
					if (element.telegramChat.getId() == BotConfig.GROUP_ID || element.telegramUser.getId() == 48212282) {
						if (element.getText().length == 2) {
							if (new InviteCommand().invite(element.getText()[1])) {
								// System.out.println("Invite " +
								// element.getText()[1]);
								sendMessage("Successfully Invited *" + element.getText()[1] + "*", element.getMessageId(),
										element.telegramChat.getId());
								// Main.plugin.getLogger().log(Level.INFO,
								// "Invited " + element.getText()[1] + " via
								// Telegram");
								successfullyInvited = true;
							}
						}
						if (!successfullyInvited) {
							sendMessage("Invitation err please check", element.getMessageId(),
									element.telegramChat.getId());
							// error
						}
					}
				}
				if (element.getText()[0].equalsIgnoreCase("/remove" + BotConfig.BOT_USERNAME) || element.getText()[0].equalsIgnoreCase("/remove")) {
					if (element.telegramChat.getId() == BotConfig.GROUP_ID || element.telegramUser.getId() == 48212282) {
						if (element.getText().length == 2) {
							if (new RemoveCommand().remove(element.getText()[1])) {
								// System.out.println("Invite " +
								// element.getText()[1]);
								sendMessage("Successfully Remove *" + element.getText()[1] + "*", element.getMessageId(),
										element.telegramChat.getId());
								// Main.plugin.getLogger().log(Level.INFO,
								// "Invited " + element.getText()[1] + " via
								// Telegram");
								successfullyRemoved = true;
							}
						}
						if (!successfullyRemoved) {
							sendMessage("Remove err please check", element.getMessageId(),
									element.telegramChat.getId());
							// error
						}
					}
				}
				if (element.getText()[0].equalsIgnoreCase("/link" + BotConfig.BOT_USERNAME) || element.getText()[0].equalsIgnoreCase("/link")) {
					if (element.telegramChat.getId() > 0) {
						if (!element.telegramUser.getUsername().equalsIgnoreCase("-NODATA-")) {
							if (element.getText().length == 2) {
								boolean linkSuccess = false;
								for (PendingLinkToken tmpToken : Main.pendingLinkToken) {
									if (tmpToken.token.equals(element.getText()[1])) {
										for (WhiteListElement tmpWhiteList : Main.whitelist) {
											if (tmpWhiteList.uuid.equals(tmpToken.minecraftUUID)) {
												tmpWhiteList.telegramUserId = element.telegramUser.getId() + "";
												tmpWhiteList.telegramUsername = "@" + element.telegramUser.getUsername();
												Main.pendingLinkToken.remove(tmpToken);
												linkSuccess = true;
												break;
											}
										}
										break;
									}
								}
								if (linkSuccess) {
									String minecraftUsername = "";
									for (WhiteListElement tmpWhiteListElement : Main.whitelist) {
										if(tmpWhiteListElement.telegramUserId.equals(element.telegramUser.getId() + "")) {
											minecraftUsername = tmpWhiteListElement.username;
										}
									}
									sendMessage("Successfully linked your Minecraft Account\nYou are known as *" + minecraftUsername + "* !", element.getMessageId(), element.telegramChat.getId());
								} else {
									sendMessage("Link could not be established. Pleas check your spell!", element.getMessageId(), element.telegramChat.getId());
								}
							} else if (element.getText().length == 1) {
								boolean hasPendingToken = false;
								String cachedToken = "";
								for (PendingLinkToken tmpPendingToken : Main.pendingLinkToken) {
									if(tmpPendingToken.telegramUserId.equals(element.telegramUser.getId() + "")) {
										cachedToken = tmpPendingToken.token;
										hasPendingToken = true;
										break;
									}
								}
								if (hasPendingToken) {
									sendMessage("Please send this command\n\"/link *" + cachedToken + "*\"\nin Minecraft.", element.getMessageId(), element.telegramChat.getId());
								} else {
									String generatedToken = GenerateToken.RandomString();
									Main.pendingLinkToken.add(new PendingLinkToken(element.telegramUser.getId() + "", null, generatedToken, "@" + element.telegramUser.getUsername()));
									sendMessage("Please send this command\n\"/link *" + generatedToken + "*\"\nin Minecraft within 15 minutes.", element.getMessageId(), element.telegramChat.getId());
								}
							}
						} else {
							sendMessage("Please set your *Telegram username* firstly.", element.getMessageId(), element.telegramChat.getId());
						}
					} else {
						sendMessage("Please send this command in a *private* chat with this bot.", element.getMessageId(), element.telegramChat.getId());
					}
				}
				if (element.getText()[0].equalsIgnoreCase("/whois" + BotConfig.BOT_USERNAME) || element.getText()[0].equalsIgnoreCase("/whois")) {
					if (element.telegramChat.getId() == BotConfig.GROUP_ID || element.telegramUser.getId() == 48212282 || element.telegramChat.getId() > 0) {
						if (element.getText().length == 2) {
							boolean isComplete = false;
							if (!element.getText()[1].equalsIgnoreCase("-NODATA-")) {
								for (WhiteListElement tmpWhiteList : Main.whitelist) {
									if (tmpWhiteList.telegramUsername.equalsIgnoreCase(element.getText()[1]) || tmpWhiteList.telegramUsername.equalsIgnoreCase("@" + element.getText()[1])) {
										if (!tmpWhiteList.username.isEmpty()) {
											sendMessage("He/She is known as *" + tmpWhiteList.username + "* in Minecraft.", element.getMessageId(), element.telegramChat.getId());
											isComplete = true;
											break;
										}
									}
								}
							}
							if (!isComplete) {
								sendMessage("I do not know him/her. Please notice him/her to link Minecraft account.", element.getMessageId(), element.telegramChat.getId());
							}
						} else {
							sendMessage("Please use this command followed by a Telegram username", element.getMessageId(), element.telegramChat.getId());
						}
					} else {
						sendMessage("Please send this command in a *private* chat or in the specified chatgroup", element.getMessageId(), element.telegramChat.getId());
					}
				}
				if (element.getText()[0].equalsIgnoreCase("/update" + BotConfig.BOT_USERNAME) || element.getText()[0].equalsIgnoreCase("/update")) {
					if (element.telegramChat.getId() == BotConfig.GROUP_ID || element.telegramUser.getId() == 48212282 || element.telegramChat.getId() > 0) {
						boolean isUpdated = false;
						for (WhiteListElement tmpWhiteList : Main.whitelist) {
							if (tmpWhiteList.telegramUserId.equals(element.telegramUser.getId() + "")) {
								tmpWhiteList.telegramUsername = "@" + element.telegramUser.getUsername();
								sendMessage("Successfully updated!", element.getMessageId(), element.telegramChat.getId());
								isUpdated = true;
								break;
							}
						}
						if (!isUpdated) {
							sendMessage("Unable to update.", element.getMessageId(), element.telegramChat.getId());
						}
					}
				}
				*/
			}
			offset = element.getOffset() + 1;
		}
	}

	public void sendMessage(String msg, long replyMessageId, String chatId) {
		try {
			postCallMethod("sendMessage",
					"reply_to_message_id=" + replyMessageId + "&text=" + msg + "&chat_id=" + chatId + "&parse_mode=MARKDOWN");
		} catch (IOException e) {
		}
	}

	private void postCallMethod(String botMethod, String params) throws IOException {
		this.reply = new JSONObject(new HttpUtil("https://api.telegram.org/bot" + CustomData.telegramBotToken + "/" + botMethod,
				method.POST, params).postCall());
	}
	
	public void sendPhoto(String url, String chatId) {
		try {
			//String s = new JSONObject().put("chat_id", chatId).put("photo", url).toString();
			Logger.log(URLEncoder.encode(url, "UTF-8"));
			//Logger.log(s);
			postCallMethod("sendPhoto", 
					"chat_id=" + chatId + "&photo=" + URLEncoder.encode(url,"UTF-8")
					//s
					);
		} catch (IOException e) {
			Logger.log(this.reply.toString());
			e.printStackTrace();
		}
	}
	public void sendMessage(String msg, String chatId) {
		try {
			postCallMethod("sendMessage",
					"&text=" + msg + "&chat_id=" + chatId + "&parse_mode=MARKDOWN");
		} catch (IOException e) {
			Logger.log("Error when sending message");
			e.printStackTrace();
		}
	}
	public void editMessageText(String msg, String chatId, long messageId) {
		try {
			postCallMethod("editMessageText", "&text=" + msg + "&chat_id=" + chatId + "&message_id=" + messageId + "&parse_mode=MARKDOWN");
		} catch (Exception e) {
			Logger.log("error when editing message");
			Logger.log("messageId : " + messageId);
			e.printStackTrace();
		}
	}
	public void answerInlineQuery(String inlineQueryId, JSONArray results) {
		try {
			postCallMethod("answerInlineQuery", "inline_query_id=" + inlineQueryId + "&results=" + results.toString());
		} catch (Exception e) {
			Logger.log("error when answering inline query");
			Logger.log("inlineQueryId : " + inlineQueryId);
			e.printStackTrace();
		}
	}
	public void answerInlineQuery(InlineQuery inlineQuery, JSONArray results) {
		answerInlineQuery(inlineQuery.id, results);
	}

}
