package java_miner_package.controller;

import javax.swing.*;
import java.awt.event.ActionListener;

public class GameTimer {
    private int minutes;
    private int seconds;
    private final Timer timer;

    public GameTimer(ActionListener actionListener) {
        this.timer = new Timer(1000, actionListener);
        this.timer.start();
    }

    public void timerStop() {
        this.timer.stop();
    }

    public void timerPlusSecond() {
        this.setSeconds(this.getSeconds()+1);
    }

    private void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    private void setSeconds(int seconds) {
        if(seconds > 59) {
            this.seconds = 0;
            this.setMinutes(this.getMinutes()+1);
        } else
            this.seconds = seconds;
    }

    private int getMinutes() {
        return minutes;
    }

    private int getSeconds() {
        return seconds;
    }

    public String getTime() {
        String formatSeconds = String.format("%02d", this.seconds); // if 0 .. 9 seconds -> 00 .. 09 seconds returns
        return this.getMinutes() + ":" + formatSeconds;
    }
}
