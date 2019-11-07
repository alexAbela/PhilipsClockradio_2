package dk.dtu.philipsclockradio;

import android.os.Handler;

import static dk.dtu.philipsclockradio.ContextClockradio.ui;

public class StateSleep extends StateAdapter {
    int sleepTime;
    int click;
    private static Handler handler = new Handler();
    private ContextClockradio mContext;


    @Override
    public void onEnterState(ContextClockradio context) {
        mContext = context;
        ui.turnOnLED(3);
        sleepTime = 120;
        click = 1;
        ui.setDisplayText(String.valueOf(sleepTime));
        handler.postDelayed(backToStandby,5000);
        handler.postDelayed(sleepTimer,12000);

    }

    Runnable backToStandby = new Runnable() {
        @Override
        public void run() {
            mContext.setState(new StateStandby(mContext.getTime()));
        }
    };

    Runnable sleepTimer = new Runnable() {
        @Override
        public void run() {
            ui.turnOffLED(3);
        }
    };

    @Override
    public void onExitState(ContextClockradio context) {
    }

    @Override
    public void onClick_Sleep(ContextClockradio context) {
        switch (click){
            case 1: sleepTime = 90;
                handler.removeCallbacks(backToStandby);
                handler.postDelayed(sleepTimer,9000);
                handler.postDelayed(backToStandby,5000);

            break;
            case 2: sleepTime = 60;
                handler.removeCallbacks(backToStandby);
                handler.removeCallbacks(sleepTimer);
                handler.postDelayed(backToStandby,5000);
                handler.postDelayed(sleepTimer,6000);
            break;
            case 3: sleepTime = 30;
                handler.removeCallbacks(backToStandby);
                handler.removeCallbacks(sleepTimer);
                handler.postDelayed(backToStandby,5000);
                handler.postDelayed(sleepTimer,3000);
            break;
            case 4: sleepTime = 15;
                handler.removeCallbacks(backToStandby);
                handler.removeCallbacks(sleepTimer);
                handler.postDelayed(backToStandby,5000);
                handler.postDelayed(sleepTimer,1500);
            break;
            case 5:
                handler.removeCallbacks(backToStandby);
                handler.removeCallbacks(sleepTimer);
                ui.turnOffLED(3);
                handler.post(backToStandby);
                break;
            default:
                break;
        }
        ui.setDisplayText(String.valueOf(sleepTime));
        click++;
    }
}

