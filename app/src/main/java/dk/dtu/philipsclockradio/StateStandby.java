package dk.dtu.philipsclockradio;

import android.os.Handler;
import java.util.Date;

import static dk.dtu.philipsclockradio.ContextClockradio.ui;

public class StateStandby extends StateAdapter {

    private Date mTime;
    private static Handler mHandler = new Handler();
    private ContextClockradio mContext;

    StateStandby(Date time){
        mTime = time;
    }

    //Opdaterer hvert 60. sekund med + 1 min til tiden
    Runnable mSetTime = new Runnable() {

        @Override
        public void run() {
            try {
                long currentTime = mTime.getTime();
                mTime.setTime(currentTime + 60000);
                mContext.setTime(mTime);
            } finally {
                mHandler.postDelayed(mSetTime, 1000);
                mHandler.postDelayed(mCheckForAlarm,1000);
            }
        }
    };

    Runnable mCheckForAlarm = new Runnable() {
        @Override
        public void run() {
            for (int i = 1; i < 3; i++) {
                if (mContext.getAlarm(i).isActive()) {
                    if (mContext.getAlarm(i).getAlarmTime() == mTime.getTime()) {
                    alarmRingingState(mContext,i);
                    }
                }
            }
        }
    };

    public void alarmRingingState(ContextClockradio context, int alarmNumber){
        context.setState(new StateAlarmRinging(alarmNumber,context.getCurrentState()));
    }

    public void alarmLights(ContextClockradio context){
        for(int i = 1; i < 3; i++){
            if(context.getAlarm(i).isActive() && context.getAlarm(i).isSoundIsAlarm()){
                ui.turnOnLED(i*i+1);
            } else if (context.getAlarm(i).isActive() && !context.getAlarm(i).isSoundIsAlarm()) {
                ui.turnOnLED(i*i);
            }
        }
    }


    void startClock() {
        mSetTime.run();
        mContext.isClockRunning = true;
    }

    void stopClock() {
        mHandler.removeCallbacks(mSetTime);
        mContext.isClockRunning = false;
    }

    @Override
    public void onEnterState(ContextClockradio context) {
        //Lokal context oprettet for at Runnable kan få adgang
        mContext = context;
        alarmLights(context);
        ui.toggleRadioPlaying("OFF");
        ui.toggleAlarmPlaying("OFF");
        context.updateDisplayTime();
        if(!context.isClockRunning){
            startClock();
        }
    }

    @Override
    public void onLongClick_Preset(ContextClockradio context) {
        stopClock();
        context.setState(new StateSetTime());
    }

    @Override
    public void onClick_Power(ContextClockradio context) {
        context.setState(new StateSetPlayRadio());
    }

    @Override
    public void onClick_Sleep(ContextClockradio context) {
        context.setState(new StateSleepOn());
    }

    @Override
    public void onClick_AL1(ContextClockradio context) {
    }

    @Override
    public void onLongClick_AL1(ContextClockradio context) {
        //context.setState(new StateSetAlarm1());
        context.setState(new StateSetAlarm(1,context.getCurrentState()));
    }

    @Override
    public void onLongClick_AL2(ContextClockradio context) {
        context.setState(new StateSetAlarm(2,context.getCurrentState()));
    }
}
