package me.rythem.spammer;

import me.rythem.spammer.utils.LoggerUtil;
import me.rythem.spammer.utils.TimerUtil;
import me.rythem.spammer.utils.WebhookUtil;

public class WebhookThread {

	public String url, message;
	public Long delay;
	public TimerUtil timer = new TimerUtil();
	
	public WebhookThread(String url, String message, Long delay) {
		this.url = url;
		this.message = message;
		this.delay = delay;
	}
	
	public void start() {
		try {
			WebhookUtil.sendMessageToWebhook(url, message);
			LoggerUtil.log("Sent message " + message + " to webhook!", 0);
		} catch (Exception e) {
			LoggerUtil.log(e.getMessage(), 2);
		}
		
		timer.reset();
	}
	
	public boolean canStart() {
		return timer.reachedMS(delay);
	}
	
}
