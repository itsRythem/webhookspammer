package me.rythem.spammer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import me.rythem.spammer.utils.LoggerUtil;
import me.rythem.spammer.utils.TimerUtil;

public class Main {

	public static ArrayList<WebhookThread> webhooks = new ArrayList<WebhookThread>();
	public static ArrayList<String> urls = new ArrayList<String>();
	public static String message = "";
	public static Long delay = 0l;
	public static int count = 0;
	public static Properties prop = new Properties();
	
    public static void main(String[] args) {
    	String fileName = "config.yml";
    	try (FileInputStream fis = new FileInputStream(fileName)) {
    	    prop.load(fis);
    		LoggerUtil.log("Config file loaded. Starting thread.", 0);
    	} catch (FileNotFoundException e) {
    		LoggerUtil.log("Config file not found. Please add 'config.yml' to directory.", 0);
    	} catch (IOException e1) {
    		LoggerUtil.log(e1.getMessage(), 2);
    	}

    	if(!prop.isEmpty()) {
    		for(int i = 0; i <= 100; i++) {
    			if(prop.contains("url" + i)) {
    				urls.add(prop.getProperty("url" + i));
    			}
    		}

    		if(prop.contains("message")) {
    			message = prop.getProperty("message");
    		}else {
    			message = "Default Webhook-Spammer Message.";
    		}
    	
    		if(prop.contains("count")) {
    			count = Integer.valueOf(prop.getProperty("count"));
    		}else {
    			count = 100;
    		}
    		
    		if(prop.contains("delay")) {
    			delay = Long.valueOf(prop.getProperty("delay"));
    		}else {
    			delay = 50l;
    		}
    	}else {
    		LoggerUtil.log("Config File Empty. Exiting!", 1);
    	}
    	
    	for(String url : urls) {
    		webhooks.add(new WebhookThread(url, message, delay));
    	}
    	
    	for(int i = 0; i <= count; i++) {
    		for(WebhookThread webhook : webhooks) {
    			if(webhook.canStart()) {
    	    		LoggerUtil.log("Attempting send!", 0);
    				webhook.start();
    			}else {
    	    		LoggerUtil.log("Thread Error. Exiting!", 2);
    			}
    		}
    	}
    }
}
