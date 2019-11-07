package dk.dtu.philipsclockradio;

import android.util.Log;

import java.util.Calendar;
import java.util.Date;

//
public class ContextClockradio {
    private State currentState;
    private Date mTime;
    private Alarm alarm1, alarm2;
    private String mDisplayText;
    private radioFrequency mradioFrequency, userPreset1, userPreset2, userPreset3, userPreset4, userPreset5;
    public boolean isClockRunning = false;

    public static MainUI ui;

    public ContextClockradio(MainUI context){
        ui = context;

        //Sætter tiden til 12.00, hvis tiden ikke er sat endnu
        if(mTime == null){
            Calendar date = Calendar.getInstance();
            date.set(2019, 1, 1, 12, 00);
            mTime = date.getTime();
        }

        mradioFrequency = new radioFrequency();
        initiatePresets();
        initiateAlarms();
        //Når app'en starter, så går vi ind i Standby State
        currentState = new StateStandby(mTime);
        currentState.onEnterState(this);
    }

    //setState er når vi skifter State
    void setState(final State newState) {
        currentState.onExitState(this);
        currentState = newState;
        currentState.onEnterState(this);
        System.out.println("Current state: "+ newState.getClass().getSimpleName());
    }

    //Opdaterer kontekst time state og UI
    void setTime(Date time){
        mTime = time;
        if(currentState.getClass().getSimpleName().equals("StateStandby")){
            updateDisplayTime();
        }
    }

    public Alarm getAlarm(int number) {
        if (number == 1) {
            return alarm1;
        } else {
            return alarm2;
        }
    }

    public void initiateAlarms(){
        Calendar date = Calendar.getInstance();
        date.set(2019, 1, 1, 00, 00);
        alarm1 = new Alarm(date.getTime(),true);
        alarm2 = new Alarm(date.getTime(),true);
    }

    public radioFrequency getUserPresets(int preset) {
        radioFrequency r = new radioFrequency();
        switch (preset) {
            case 1:
                r = userPreset1;
                break;
            case 2:
                r = userPreset2;
                break;
            case 3:
                r = userPreset3;
                break;
            case 4:
                r = userPreset4;
                break;
            case 5:
                r = userPreset5;
                break;
            default:
                break;
        }
        return r;
    }

    public void initiatePresets(){
        userPreset1 = new radioFrequency();
        userPreset2 = new radioFrequency();
        userPreset3 = new radioFrequency();
        userPreset4 = new radioFrequency();
        userPreset5 = new radioFrequency();
    }

    void updateDisplayTime(){
        mDisplayText = mTime.toString().substring(11,16);
        ui.setDisplayText(mDisplayText);
    }


    public Date getTime(){
        return mTime;
    }

    public radioFrequency getMradioFrequency() {
        return mradioFrequency;
    }

    //Disse metoder bliver kaldt fra UI tråden
    public void onClick_Hour() {
        currentState.onClick_Hour(this);
    }

    public void onClick_Min() {
        currentState.onClick_Min(this);
    }

    public void onClick_Preset() {
        currentState.onClick_Preset(this);
    }

    public void onClick_Power() {
        currentState.onClick_Power(this);
    }

    public void onClick_Sleep() {
        currentState.onClick_Sleep(this);
    }

    public void onClick_AL1() {
        currentState.onClick_AL1(this);
    }

    public void onClick_AL2() {
        currentState.onClick_AL2(this);
    }

    public void onClick_Snooze() {
        currentState.onClick_Snooze(this);
    }

    public void onLongClick_Hour(){
        currentState.onLongClick_Hour(this);
    }

    public void onLongClick_Min(){
        currentState.onLongClick_Min(this);
    }

    public void onLongClick_Preset(){
        currentState.onLongClick_Preset(this);
    }

    public void onLongClick_Power(){
        currentState.onLongClick_Power(this);
    }

    public void onLongClick_Sleep(){
        currentState.onLongClick_Sleep(this);
    }

    public void onLongClick_AL1(){
        currentState.onLongClick_AL1(this);
    }

    public void onLongClick_AL2(){
        currentState.onLongClick_AL2(this);
    }

    public void onLongClick_Snooze(){
        currentState.onLongClick_Snooze(this);
    }
}