package dk.dtu.philipsclockradio;

public class StateSetPresets extends StateAdapter {

    int LEDnumber;

    @Override
    public void onEnterState(ContextClockradio context) {
        LEDnumber = 1;
        context.ui.turnOnLED(LEDnumber);
    }

    @Override
    public void onExitState(ContextClockradio context) {
        context.ui.turnOffLED(LEDnumber);
    }

    @Override
    public void onClick_Preset(ContextClockradio context) {
        LEDnumber++;
        if(LEDnumber==6){
            LEDnumber = 1;
        }
        context.ui.turnOnLED(LEDnumber);
        if(LEDnumber == 1) {
            context.ui.turnOffLED(5);
        } else {
            context.ui.turnOffLED(LEDnumber-1);
        }
    }

    @Override
    public void onLongClick_Preset(ContextClockradio context) {
        if(!context.getUserPresets(LEDnumber).getModulation().equals(context.getMradioFrequency().getModulation())){
            context.getUserPresets(LEDnumber).changeModulation();
        }
        context.getUserPresets(LEDnumber).setFrequency(context.getMradioFrequency().getFrequency());
        context.setState(new StateSetPlayRadio());
    }

    @Override
    public void onClick_Power(ContextClockradio context) {
        context.setState(new StateSetPlayRadio());
    }
}
