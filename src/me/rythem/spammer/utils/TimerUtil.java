package me.rythem.spammer.utils;

public class TimerUtil {
	
    public long currentMS = System.nanoTime() / 1000000;
    public long prevMS = 0;
    
    public boolean reachedMS(long time) {
        if (this.getTime() >= time) {
            return true;
        }
        return false;
    }

    public long getTime() {
        return System.nanoTime() / 1000000 - this.currentMS;
    }

    public void reset() {
        this.currentMS = System.nanoTime() / 1000000;
    }
	
}
