package me.rythem.spammer.utils;

public class LoggerUtil {

	public static void log(String message, int code) {
		switch(code) {
		case 0:
			System.out.println(message);
			break;
		case 1:
			System.out.println("WARNING:" + " " + message);
			break;
		case 2:
			System.out.println("ERROR:" + " " + message);
			break;
		}
	}
	
}
