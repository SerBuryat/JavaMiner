package java_miner_package.view.game_panel;

import javax.swing.*;
import java.awt.event.ActionListener;

class GameTimer {
    private int minutes;
    private int seconds;
    private final Timer timer;

    GameTimer(ActionListener actionListener) {
        this.timer = new Timer(1000, actionListener);
        this.timer.start();
    }

    public void timerStop() {
        this.timer.stop();
    }

    void timerPlusSecond() {
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

    String getTime() {
        String formatSeconds = String.format("%02d", this.seconds); // if 0 .. 9 seconds -> 00 .. 09 seconds returns
        return this.getMinutes() + ":" + formatSeconds;
    }
}
