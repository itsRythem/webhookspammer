package me.rythem.spammer.utils;

import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;

public class WebhookUtil {

	public static void sendMessageToWebhook(String url, String message) throws Exception {
		HttpsURLConnection connection = (HttpsURLConnection) new URL(url).openConnection();
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; U; Linux i686) Gecko/20071127 Firefox/2.0.0.11");
		connection.setDoOutput(true);
		try (final OutputStream outputStream = connection.getOutputStream()) {
			String preparedCommand = message.replaceAll("\\\\", "");
			if (preparedCommand.endsWith(" *")) preparedCommand = preparedCommand.substring(0, preparedCommand.length() - 2) + "*";
			
			outputStream.write(("{\"content\":\"" + preparedCommand + "\"}").getBytes(StandardCharsets.UTF_8));
		}
		connection.getInputStream();
	}
	
}
